package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.model.Doctor;
import hermanos.Centro.Clinico.model.Patient;
import hermanos.Centro.Clinico.model.PatientDoctorRating;
import hermanos.Centro.Clinico.repository.PatientDoctorRatingRepository;
import hermanos.Centro.Clinico.service.interfaces.PatientDoctorRatingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientDoctorRatingService implements PatientDoctorRatingServiceInterface {

    @Autowired
    PatientDoctorRatingRepository patientDoctorRatingRepository;

    @Override
    public List<PatientDoctorRating> findAll() {
        return patientDoctorRatingRepository.findAll();
    }

    @Override
    public List<PatientDoctorRating> findAllByPatient(Patient patient) {
        return patientDoctorRatingRepository.findAllByPatient(patient);
    }

    @Override
    public List<PatientDoctorRating> findAllByDoctor(Doctor doctor) {
        return patientDoctorRatingRepository.findAllByDoctor(doctor);
    }

    @Override
    public void save(PatientDoctorRating patientDoctorRating) {
        patientDoctorRatingRepository.save(patientDoctorRating);
    }
}
