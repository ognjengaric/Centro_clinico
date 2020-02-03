package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.dto.PatientDTO;
import hermanos.Centro.Clinico.model.Patient;
import hermanos.Centro.Clinico.repository.PatientRepository;
import hermanos.Centro.Clinico.service.interfaces.PatientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService implements PatientServiceInterface {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PatientRepository patientRepository;

    public Patient findBySocialSecurityNumber(String socialSecurityNumber){
        return  patientRepository.findBySocialSecurityNumber(socialSecurityNumber);
    }

    public Patient save(Patient patient){
        return patientRepository.save(patient);
    }

    public void editPatientFields(PatientDTO p, Patient patient){

        patient.setName(p.getName());
        patient.setSurname(p.getSurname());
        patient.setPhoneNumber(p.getPhoneNumber());

        patient.getAddress().setStreet(p.getStreet());
        patient.getAddress().setNumber(p.getStreetNumber());
        patient.getAddress().setPostcode(p.getPostcode());
        patient.getAddress().setCity(p.getCity());
        patient.getAddress().setCountry(p.getCountry());
    }
    public List<Patient> findAll() {
        List<Patient> result = patientRepository.findAll();
        return result;
    }
    }
