package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    Clinic findById(long id);

}
