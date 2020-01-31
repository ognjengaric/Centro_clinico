package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    Prescription findById(long id);

    //@Modifying
    //Prescription save(Prescription prescription);
}
