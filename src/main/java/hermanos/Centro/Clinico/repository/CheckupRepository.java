package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.Checkup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckupRepository extends JpaRepository<Checkup, Long> {
    Checkup findById(long id);
}
