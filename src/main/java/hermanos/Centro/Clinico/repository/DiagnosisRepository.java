package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

    Diagnosis findById(long id);
}
