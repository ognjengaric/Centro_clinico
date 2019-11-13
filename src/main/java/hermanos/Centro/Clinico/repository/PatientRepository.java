package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

        Patient findBySocialSecurityNumber(String socialSecurityNumber);

}
