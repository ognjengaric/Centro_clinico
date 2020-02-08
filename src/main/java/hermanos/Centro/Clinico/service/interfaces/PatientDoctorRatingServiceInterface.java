package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Doctor;
import hermanos.Centro.Clinico.model.Patient;
import hermanos.Centro.Clinico.model.PatientDoctorRating;

import java.util.List;

public interface PatientDoctorRatingServiceInterface {
    List<PatientDoctorRating> findAll();
    List<PatientDoctorRating> findAllByPatient(Patient patient);
    List<PatientDoctorRating> findAllByDoctor(Doctor doctor);
    void save(PatientDoctorRating patientDoctorRating);
}
