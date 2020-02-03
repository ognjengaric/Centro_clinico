package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.model.MedicalRecord;
import hermanos.Centro.Clinico.repository.MedicalRecordRepository;
import hermanos.Centro.Clinico.service.interfaces.MedicalRecordServiceInterface;
import hermanos.Centro.Clinico.service.interfaces.MedicineServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService implements MedicalRecordServiceInterface {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecord findById(long id){
        return  medicalRecordRepository.findById(id);
    }

    @Override
    public MedicalRecord save(MedicalRecord medicalRecord){
        return medicalRecordRepository.save(medicalRecord);
    }

    public List<MedicalRecord> findAll() {
        List<MedicalRecord> result = medicalRecordRepository.findAll();
        return result;
    }
}
