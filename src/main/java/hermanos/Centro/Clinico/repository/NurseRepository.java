package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface NurseRepository extends JpaRepository<Nurse, Long> {

    Nurse findById(long id);

    @Modifying
    void deleteById(long id);
}
