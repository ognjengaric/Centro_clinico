package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.model.Patient;
import hermanos.Centro.Clinico.model.PatientRequest;
import hermanos.Centro.Clinico.repository.PatientRequestRepository;
import hermanos.Centro.Clinico.service.interfaces.PatientRequestServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientRequestService implements PatientRequestServiceInterface {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    PatientRequestRepository patientRequestRepository;

    @Override
    public PatientRequest findById(long id) {
        return patientRequestRepository.findById(id);
    }

    @Override
    public PatientRequest findByEmail(String email) {
        return patientRequestRepository.findByEmail(email);
    }

    @Override
    public PatientRequest findBySocialSecurityNumber(String socialSecurityNumber){
        return patientRequestRepository.findBySocialSecurityNumber(socialSecurityNumber);
    }

    @Override
    public PatientRequest save(PatientRequest patientRequest) {
        patientRequest.setPassword(passwordEncoder.encode(patientRequest.getPassword()));
        return patientRequestRepository.save(patientRequest);
    }

    @Override
    public List<PatientRequest> findAll(){
        List<PatientRequest> result = patientRequestRepository.findAll();
        return result;
    }
}
