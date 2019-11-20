package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.PatientRequest;

public interface PatientRequestServiceInterface {

    public PatientRequest findById(long id);
    public PatientRequest findByEmail(String email);
    public PatientRequest save(PatientRequest patientRequest);
}
