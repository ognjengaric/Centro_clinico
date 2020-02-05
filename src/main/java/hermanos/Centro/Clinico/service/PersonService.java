package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.model.Patient;
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
        return personRepository.findById(id);
    }

    @Override
    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public List<Person> findAll() {
        List<Person> result = personRepository.findAll();
        return result;
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }
}
