package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

        Doctor findById(long id);

        @Modifying
        void deleteById(long id);
}
