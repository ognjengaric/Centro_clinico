package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.ClinicalCenterAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicalCenterAdministratorRepository extends JpaRepository<ClinicalCenterAdministrator, Long> {

    ClinicalCenterAdministrator findById(long id);
    ClinicalCenterAdministrator findByEmail(String email);

}
