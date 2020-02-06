package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.model.Patient;
import hermanos.Centro.Clinico.model.PatientClinicRating;

import java.util.List;

public interface PatientClinicRatingServiceInterface {
    List<PatientClinicRating> findAll();
    List<PatientClinicRating> findAllByPatient(Patient patient);
    List<PatientClinicRating> findAllByClinic(Clinic clinic);
    void save(PatientClinicRating patientClinicRating);
}
