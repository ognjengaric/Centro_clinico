package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.model.Prescription;
import hermanos.Centro.Clinico.repository.PrescriptionRepository;
import hermanos.Centro.Clinico.service.interfaces.PrescriptionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService implements PrescriptionServiceInterface {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public Prescription findById(long id){
        return  prescriptionRepository.findById(id);
    }

    @Override
    public Prescription save(Prescription prescription){
        return prescriptionRepository.save(prescription);
    }

    public List<Prescription> findAll() {
        List<Prescription> result = prescriptionRepository.findAll();
        return result;
    }
}
