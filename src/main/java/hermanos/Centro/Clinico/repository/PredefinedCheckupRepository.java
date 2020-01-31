package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.PredefinedCheckup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface PredefinedCheckupRepository extends JpaRepository<PredefinedCheckup, Long> {

        PredefinedCheckup findById(long id);

        @Modifying
        void deleteById(long id);

}
