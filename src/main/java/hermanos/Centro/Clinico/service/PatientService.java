package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.model.Patient;
import hermanos.Centro.Clinico.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient findBySocialSecurityNumber(String socialSecurityNumber){
        return  patientRepository.findBySocialSecurityNumber(socialSecurityNumber);
    }

    public Patient save(Patient patient){
        return patientRepository.save(patient);
    }

}
