package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.CheckupDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface CheckupDateRepository extends JpaRepository<CheckupDate, Long> {

        CheckupDate findById(long id);

        @Modifying
        void deleteById(long id);

}
