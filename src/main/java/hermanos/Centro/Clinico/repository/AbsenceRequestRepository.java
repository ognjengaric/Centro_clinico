package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.AbsenceRequest;
import hermanos.Centro.Clinico.model.Checkup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceRequestRepository extends JpaRepository<AbsenceRequest, Long> {
    AbsenceRequest findById(long id);
}
