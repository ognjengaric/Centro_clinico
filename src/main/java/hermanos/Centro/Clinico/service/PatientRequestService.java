package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.model.PatientRequest;
import hermanos.Centro.Clinico.repository.PatientRequestRepository;
import hermanos.Centro.Clinico.service.interfaces.PatientRequestServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientRequestService implements PatientRequestServiceInterface {
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
    public PatientRequest save(PatientRequest patientRequest) {
        return patientRequestRepository.save(patientRequest);
    }

    public List<PatientRequest> findAll(){
        List<PatientRequest> result = patientRequestRepository.findAll();
        return result;
    }
}
