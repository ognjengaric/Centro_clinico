package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Diagnosis;

import java.util.List;

public interface DiagnosisServiceInterface {

    public Diagnosis findById(long id);
    public Diagnosis save(Diagnosis diagnosis);
    public List<Diagnosis> findAll();
}
