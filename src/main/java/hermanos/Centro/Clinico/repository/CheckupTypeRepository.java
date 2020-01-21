package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.CheckupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface CheckupTypeRepository extends JpaRepository<CheckupType, Long> {

        CheckupType findByName(String name);

        List<CheckupType> findAllByOrderByNameAsc();

        @Modifying
        void deleteById(long id);

}
