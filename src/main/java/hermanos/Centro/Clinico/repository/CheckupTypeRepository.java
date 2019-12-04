package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.CheckupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface CheckupTypeRepository extends JpaRepository<CheckupType, Long> {

        CheckupType findByName(String name);

        @Modifying
        void deleteById(long id);

}
