package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Diagnosis;

public interface DiagnosisServiceInterface {

    public Diagnosis findById(long id);
    public Diagnosis save(Diagnosis medicine);
}
