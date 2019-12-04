package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.ClinicAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicAdministratorRepository extends JpaRepository<ClinicAdministrator, Long> {

    ClinicAdministrator findById(long id);
    ClinicAdministrator findByEmail(String email);

}
