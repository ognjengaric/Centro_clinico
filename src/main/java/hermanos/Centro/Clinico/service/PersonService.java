package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.model.Person;
import hermanos.Centro.Clinico.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person findById(long id){
        return personRepository.findById(id);
    }

    public Person findByEmail(String email){
        return personRepository.findByEmail(email);
    }

    public Person save(Person person){
        return personRepository.save(person);
    }

}
