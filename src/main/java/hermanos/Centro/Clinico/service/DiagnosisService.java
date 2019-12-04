package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.model.Diagnosis;
import hermanos.Centro.Clinico.repository.DiagnosisRepository;
import hermanos.Centro.Clinico.service.interfaces.DiagnosisServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService implements DiagnosisServiceInterface {

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Override
    public Diagnosis findById(long id){
        return  diagnosisRepository.findById(id);
    }

    @Override
    public Diagnosis save(Diagnosis diagnosis){
        return diagnosisRepository.save(diagnosis);
    }

    public List<Diagnosis> findAll() {
        List<Diagnosis> result = diagnosisRepository.findAll();
        return result;
    }
}
