package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

        Doctor findById(long id);
}
