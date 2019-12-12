package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.PatientRequest;

import java.util.List;

public interface PatientRequestServiceInterface {

    public PatientRequest findById(long id);
    public PatientRequest findByEmail(String email);
    public PatientRequest findBySocialSecurityNumber(String socialSecurityNumber);
    public PatientRequest save(PatientRequest patientRequest);
    public List<PatientRequest> findAll();
    public void remove(String socialSecurityNumber);
}
