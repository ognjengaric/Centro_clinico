package hermanos.Centro.Clinico.services;



import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThat;
//import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.annotation.Rollback;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;


import hermanos.Centro.Clinico.model.ClinicAdministrator;
import hermanos.Centro.Clinico.model.Person;
import hermanos.Centro.Clinico.repository.ClinicAdministratorRepository;
import hermanos.Centro.Clinico.repository.PersonRepository;
import hermanos.Centro.Clinico.service.ClinicAdministratorService;
import hermanos.Centro.Clinico.service.PersonService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static hermanos.Centro.Clinico.constants.PersonConstant.DB_A_ID;
//import java.util.Arrays;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.verifyNoMoreInteractions;
//import static org.mockito.Mockito.when;
//
import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ClinicAdminServiceTest {

    @MockBean
    private ClinicAdministratorRepository clinicAdministratorRepositoryMock;

    @MockBean
    private PersonRepository personRepositoryMock;

    @Mock
    private ClinicAdministrator clinicAdministratorMock = new ClinicAdministrator();
    @Mock
    private Person personMock = new Person();

    @Autowired
    private ClinicAdministratorService clinicAdministratorService;

    @Autowired
    private PersonService personService;

     @Test
    public void testFindById() {
         when(clinicAdministratorRepositoryMock.findById(DB_A_ID)).thenReturn(clinicAdministratorMock);
         ClinicAdministrator admin = clinicAdministratorService.findByiD(DB_A_ID);
         List<Person> p = personService.findAll();
         assertThat(admin.getId()).isEqualTo(DB_A_ID);
        verify(clinicAdministratorRepositoryMock, times(1)).findById(DB_A_ID);
        verifyNoMoreInteractions(clinicAdministratorRepositoryMock);



    }

    
}

