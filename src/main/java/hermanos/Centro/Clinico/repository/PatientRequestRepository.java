package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.PatientRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRequestRepository extends JpaRepository<PatientRequest, Long> {

        PatientRequest findByEmail(String email);
        PatientRequest findById(long id);
        PatientRequest findBySocialSecurityNumber(String socialSecurityNumber);
}
