package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.model.Person;
import hermanos.Centro.Clinico.repository.PersonRepository;
import hermanos.Centro.Clinico.service.interfaces.PersonServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements PersonServiceInterface {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person findById(long id) {
        Person person = personRepository.findById(id);
        return person;
    }

    @Override
    public Person findByEmail(String email) {
        Person person = personRepository.findByEmail(email);
        return person;
    }

    public List<Person> findAll() {
        List<Person> result = personRepository.findAll();
        return result;
    }

    @Override
    public Person save(Person person) {
        return null;
    }
}
