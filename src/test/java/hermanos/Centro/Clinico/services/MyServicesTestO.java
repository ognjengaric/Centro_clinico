package hermanos.Centro.Clinico.services;

import hermanos.Centro.Clinico.model.Person;
import hermanos.Centro.Clinico.repository.PersonRepository;
import hermanos.Centro.Clinico.service.interfaces.PersonServiceInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static hermanos.Centro.Clinico.constants.PersonConstant.DB_EMAIL;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Ognjen Garic RA156/2016
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyServicesTestO {

    @Mock
    PersonRepository personRepositoryMock;

    @Mock
    Person personMock;

    @InjectMocks
    PersonServiceInterface personService;

    // Following tests are for method PersonService.findByEmail(email)

    @Test
    public void testFindByEmail(){

        when(personRepositoryMock.findByEmail(DB_EMAIL)).thenReturn(personMock);
        Person dbPerson = personService.findByEmail(DB_EMAIL);

        assertEquals(DB_EMAIL, dbPerson.getEmail());

        verify(personRepositoryMock, times(1)).findByEmail(DB_EMAIL);
        verifyNoMoreInteractions(personRepositoryMock);
    }
}
