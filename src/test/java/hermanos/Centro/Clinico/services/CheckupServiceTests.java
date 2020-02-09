package hermanos.Centro.Clinico.services;

import hermanos.Centro.Clinico.dto.ScheduleFilterDTO;
import hermanos.Centro.Clinico.model.*;
import hermanos.Centro.Clinico.repository.CheckupRepository;
import hermanos.Centro.Clinico.service.CheckupService;
import hermanos.Centro.Clinico.service.CheckupTypeService;
import hermanos.Centro.Clinico.service.ClinicService;
import hermanos.Centro.Clinico.service.DoctorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.filter;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckupServiceTests {

    @MockBean
    private CheckupRepository checkupRepositoryMock;

    @MockBean
    private CheckupTypeService checkupTypeServiceMock;
    @MockBean
    private DoctorService doctorServiceMock;
    @MockBean
    private ClinicService clinicServiceMock;

    @Mock
    private Checkup checkupMock;

    @Mock
    private List<Checkup> checkupListMock;

    @Autowired
    private CheckupService checkupService;

    @Test
    public void findById(){
        when(checkupRepositoryMock.findById(0)).thenReturn(checkupMock);
        Checkup checkup = checkupService.findById(0);
        assertEquals(checkupMock, checkup);
        verify(checkupRepositoryMock, times(1)).findById(0);
        verifyNoMoreInteractions(checkupRepositoryMock);

    }

    @Test
    public void findAll(){
        when(checkupRepositoryMock.findAll()).thenReturn(Arrays.asList(new Checkup()));
        List<Checkup> checkups = checkupService.findAll();
        assertThat(checkups).hasSize(1);
    }
    @Test
    public void save(){
        when(checkupRepositoryMock.findAll()).thenReturn(Arrays.asList(new Checkup()));
        Checkup c = new Checkup();
        c.setType(new CheckupType());
        c.setStartEnd(new StartEndTime());
        c.setId(12);
        c.setOperation(true);
        c.setDate(LocalDate.of(1,1,1));
        c.setDoctor(new Doctor());
        c.setClinic(new Clinic());
        when(checkupRepositoryMock.save(c)).thenReturn(c);
        int prevSize = checkupService.findAll().size();
        Checkup dbc = checkupService.save(c);
        assertThat(dbc).isNotNull();

        when(checkupRepositoryMock.findAll()).thenReturn(Arrays.asList(new Checkup(), c));
        List<Checkup> checkups = checkupService.findAll();
        assertThat(checkups).hasSize(prevSize+1);
        verify(checkupRepositoryMock, times(2)).findAll();
        verify(checkupRepositoryMock, times(1)).save(c);
        verifyNoMoreInteractions(checkupRepositoryMock);
    }

    @Test
    public void isValid(){
        when(checkupRepositoryMock.findAll()).thenReturn(new ArrayList<Checkup>());
        Checkup c = new Checkup();
        c.setType(new CheckupType());
        c.setStartEnd(new StartEndTime(LocalTime.of(7,30), LocalTime.of(11,30)));
        c.setId(12);
        c.setOperation(true);
        c.setDate(LocalDate.of(1,1,1));
        c.setDoctor(new Doctor());
        c.setClinic(new Clinic());
        boolean good = checkupService.isValid(c);
        assertThat(good).isEqualTo(true);

        List<Checkup> cs = new ArrayList<Checkup>();
        cs.add(c);
        when(checkupRepositoryMock.findAll()).thenReturn(cs);
        good = checkupService.isValid(c);
        assertThat(good).isEqualTo(false);

        verify(checkupRepositoryMock, times(2)).findAll();
        verifyNoMoreInteractions(checkupRepositoryMock);
    }

    @Test
    public void makeNewCheckup(){
        ScheduleFilterDTO filterDTO = new ScheduleFilterDTO();
        filterDTO.setCheckupDate("2020-03-05");
        filterDTO.setCheckupTime("07:30");
        filterDTO.setCheckupType("0");
        filterDTO.setDoctorId("0");

        Clinic clinic = new Clinic();
        clinic.setId(1L);
        clinic.setAddress(new Address());
        clinic.setName("a");
        clinic.setDescription("as");


        Patient p = new Patient();

        boolean predef = false;

        CheckupType ct = new CheckupType();
        ct.setClinic(clinic);
        ct.setId(1L);
        ct.setPrice(33);
        ct.setName("0");
        ct.setDuration(30L);

        Doctor doc = new Doctor();
        doc.setId(0L);
        doc.setClinic(clinic);
        when(checkupTypeServiceMock.findByName("0")).thenReturn(ct);
        when(doctorServiceMock.findById(0L)).thenReturn(doc);
        when(clinicServiceMock.findById(1L)).thenReturn(clinic);
        when(clinicServiceMock.save(new Clinic())).thenReturn(clinic);

        Checkup checkup = checkupService.makeNewCheckup(filterDTO,p,predef);

        assertThat(checkup.getType().getId()).isEqualTo(1);
        assertThat(checkup.getDate()).isEqualTo(LocalDate.parse(filterDTO.getCheckupDate()));

        verify(checkupTypeServiceMock, times(1)).findByName("0");
        verify(doctorServiceMock, times(1)).findById(0L);
        verify(clinicServiceMock, times(1)).findById(1L);
        verifyNoMoreInteractions(checkupRepositoryMock);
    }
}
