package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.CheckupType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckupTypeRepository extends JpaRepository<CheckupType, Long> {

        CheckupType findByName(String name);

}
