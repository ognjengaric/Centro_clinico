package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.MedicalRecord;

import java.util.List;

public interface MedicalRecordServiceInterface {

    public MedicalRecord findById(long id);
    public MedicalRecord save(MedicalRecord medicine);
    public List<MedicalRecord> findAll();
}
