package hermanos.Centro.Clinico.services;

import hermanos.Centro.Clinico.constants.PersonConstant;
import hermanos.Centro.Clinico.model.Person;
import hermanos.Centro.Clinico.repository.DoctorRepository;
import hermanos.Centro.Clinico.repository.PersonRepository;
import hermanos.Centro.Clinico.service.PersonService;
import hermanos.Centro.Clinico.service.interfaces.PersonServiceInterface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static hermanos.Centro.Clinico.constants.PersonConstant.DB_EMAIL;
//import static hermanos.Centro.Clinico.constants.PersonConstant.DB_PASSWORD;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Ognjen Garic RA156/2016
 **/

@RunWith(SpringRunner.class)
@DataJpaTest
public class MyServicesTestO {

    @Mock
    PersonRepository personRepositoryMock;

    @Mock
    Person personMock;

    @InjectMocks
    PersonService personService;

    // Following tests are for method PersonService.findByEmail(email)


    @Test
    public void testFindByEmailService(){

        when(personRepositoryMock.findByEmail(DB_EMAIL)).thenReturn(personMock);
        Person dbPerson = personService.findByEmail(DB_EMAIL);
        assertEquals(DB_EMAIL, dbPerson.getEmail());

        verify(personRepositoryMock, times(1)).findByEmail(DB_EMAIL);
        verifyNoMoreInteractions(personRepositoryMock);
    }


    @Autowired
    private PersonRepository personRepository;

    // PersonRepository.findByEmail
    @Test
    public void testFindByEmailPersonRepository() throws Exception{
        Person person = personRepository.findByEmail(DB_EMAIL);
        assertEquals(person.getEmail(), DB_EMAIL);
    }

    //DoctorRepository.findByEmail

    @Autowired
    DoctorRepository doctorRepository;

    @Test
    public void testFindByEmailDoctorRepository() throws Exception{
        Person person = doctorRepository.findByEmail(DB_EMAIL);
        assertEquals(person.getEmail(), DB_EMAIL);
    }
}




