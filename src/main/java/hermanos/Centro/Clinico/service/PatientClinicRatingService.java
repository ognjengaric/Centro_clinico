package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.model.Patient;
import hermanos.Centro.Clinico.model.PatientClinicRating;
import hermanos.Centro.Clinico.repository.PatientClinicRatingRepository;
import hermanos.Centro.Clinico.service.interfaces.PatientClinicRatingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientClinicRatingService implements PatientClinicRatingServiceInterface {

    @Autowired
    PatientClinicRatingRepository patientClinicRatingRepository;

    @Override
    public List<PatientClinicRating> findAll() {
        return patientClinicRatingRepository.findAll();
    }

    @Override
    public List<PatientClinicRating> findAllByPatient(Patient patient) {
        return patientClinicRatingRepository.findAllByPatient(patient);
    }

    @Override
    public List<PatientClinicRating> findAllByClinic(Clinic clinic) {
        return patientClinicRatingRepository.findAllByClinic(clinic);
    }

    @Override
    public void save(PatientClinicRating patientClinicRating) {
        patientClinicRatingRepository.save(patientClinicRating);
    }
}
