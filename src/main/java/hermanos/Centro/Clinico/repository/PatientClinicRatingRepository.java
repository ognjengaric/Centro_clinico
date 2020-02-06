package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.model.Patient;
import hermanos.Centro.Clinico.model.PatientClinicRating;
import hermanos.Centro.Clinico.model.PatientClinicRatingID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientClinicRatingRepository extends JpaRepository<PatientClinicRating, PatientClinicRatingID> {
    List<PatientClinicRating> findAllByPatient(Patient patient);
    List<PatientClinicRating> findAllByClinic(Clinic clinic);
}
