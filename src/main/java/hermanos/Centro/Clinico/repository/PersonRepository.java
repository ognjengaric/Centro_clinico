package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

        Person findByEmail(String email);   
}
