package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    Clinic findById(long id);

    @Modifying
    Clinic save(Clinic clinic);

}
