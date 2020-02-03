package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    MedicalRecord findById(long id);
}
