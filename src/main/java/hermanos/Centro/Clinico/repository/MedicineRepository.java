package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Medicine findById(long id);
}
