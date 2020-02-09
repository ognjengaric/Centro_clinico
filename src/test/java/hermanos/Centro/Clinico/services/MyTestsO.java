package hermanos.Centro.Clinico.services;

import hermanos.Centro.Clinico.model.CheckupType;
import hermanos.Centro.Clinico.model.ClinicAdministrator;
import hermanos.Centro.Clinico.model.Person;
import hermanos.Centro.Clinico.repository.CheckupTypeRepository;
import hermanos.Centro.Clinico.repository.ClinicAdministratorRepository;
import hermanos.Centro.Clinico.repository.DoctorRepository;
import hermanos.Centro.Clinico.repository.PersonRepository;
import hermanos.Centro.Clinico.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static hermanos.Centro.Clinico.constants.ConstantsO.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Ognjen Garic RA156/2016
 **/

@RunWith(SpringRunner.class)
@DataJpaTest
public class MyTestsO {

    @Mock
    PersonRepository personRepositoryMock;

    @Mock
    Person personMock;

    @InjectMocks
    PersonService personService;

    // Following tests are for method PersonService.findByEmail(email)


    @Test
    public void testFindByEmailService() {

        when(personRepositoryMock.findByEmail(DB_EMAIL_PERSON)).thenReturn(personMock);
        Person dbPerson = personService.findByEmail(DB_EMAIL_PERSON);
        assertEquals(DB_EMAIL_PERSON, dbPerson.getEmail());

        verify(personRepositoryMock, times(1)).findByEmail(DB_EMAIL_PERSON);
        verifyNoMoreInteractions(personRepositoryMock);
    }


    @Autowired
    private PersonRepository personRepository;

    // PersonRepository.findByEmail
    @Test
    public void testFindByEmailPersonRepository() throws Exception {
        Person person = personRepository.findByEmail(DB_EMAIL_PERSON);
        assertEquals(person.getEmail(), DB_EMAIL_PERSON);
    }

    //DoctorRepository.findByEmail

    @Autowired
    DoctorRepository doctorRepository;

    @Test
    public void testFindByEmailDoctorRepository() throws Exception {
        Person person = doctorRepository.findByEmail(DB_EMAIL_DOCTOR);
        assertEquals(person.getEmail(), DB_EMAIL_DOCTOR);
    }

    //CheckupTypeRepository.findAll
    @Autowired
    CheckupTypeRepository checkupTypeRepository;

    @Test
    public void testFindAllCheckupTypesRepository() throws Exception {
        List<CheckupType> types = checkupTypeRepository.findAll();
        assertEquals(types.size(), DB_CHECKUP_TYPES_COUNT);
    }

    //ClinicAdminstratorRepository.findByEmail
    @Autowired
    ClinicAdministratorRepository clinicAdministratorRepository;

    @Test
    public void testFindByEmailCARepository() throws Exception {
        ClinicAdministrator clinicAdministrator = clinicAdministratorRepository.findByEmail(DB_EMAIL_CA);
        assertEquals(clinicAdministrator.getEmail(), DB_EMAIL_CA);
    }


}