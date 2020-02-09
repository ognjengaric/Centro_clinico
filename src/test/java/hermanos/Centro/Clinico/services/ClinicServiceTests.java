package hermanos.Centro.Clinico.services;

import hermanos.Centro.Clinico.dto.ScheduleFilterDTO;
import hermanos.Centro.Clinico.model.Address;
import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.repository.ClinicRepository;
import hermanos.Centro.Clinico.service.ClinicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ClinicServiceTests {
    public static final long CLINIC_ID_1 = 0L;

    @Mock
    private JavaMailSender javaMailSenderMock;

    @MockBean
    private ClinicRepository clinicRepositoryMock;

    @Mock
    private Clinic clinicMock = new Clinic();

    @Mock
    private List<Clinic> clinicListMock;

    @Autowired
    private ClinicService clinicService;

    @Test
    public void findById(){
        when(clinicRepositoryMock.findById(CLINIC_ID_1)).thenReturn(clinicMock);
        Clinic clinic = clinicService.findById(CLINIC_ID_1);
        assertEquals(clinicMock, clinic);
        verify(clinicRepositoryMock, times(1)).findById(CLINIC_ID_1);
        verifyNoMoreInteractions(clinicRepositoryMock);

    }

    @Test
    public void findAll(){
        when(clinicRepositoryMock.findAll()).thenReturn(Arrays.asList(new Clinic()));
        List<Clinic> clinics = clinicService.findAll();
        assertThat(clinics).hasSize(1);
    }
    @Test
    public void save(){
        when(clinicRepositoryMock.findAll()).thenReturn(Arrays.asList(new Clinic()));
        Clinic c = new Clinic();
        c.setName("TEST");
        c.setId(123);
        c.setDescription("TEST1123");
        when(clinicRepositoryMock.save(c)).thenReturn(c);
        int prevSize = clinicService.findAll().size();
        Clinic dbc = clinicService.save(c);
        assertThat(dbc).isNotNull();

        when(clinicRepositoryMock.findAll()).thenReturn(Arrays.asList(new Clinic(), c));
        List<Clinic> clinics = clinicService.findAll();
        assertThat(clinics).hasSize(prevSize+1);
        verify(clinicRepositoryMock, times(2)).findAll();
        verify(clinicRepositoryMock, times(1)).save(c);
        verifyNoMoreInteractions(clinicRepositoryMock);
    }

}
