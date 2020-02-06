package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.Doctor;
import hermanos.Centro.Clinico.model.Patient;
import hermanos.Centro.Clinico.model.PatientDoctorRating;
import hermanos.Centro.Clinico.model.PatientDoctorRatingID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientDoctorRatingRepository extends JpaRepository<PatientDoctorRating, PatientDoctorRatingID> {
    List<PatientDoctorRating> findAllByPatient(Patient patient);
    List<PatientDoctorRating> findAllByDoctor(Doctor doctor);
}
