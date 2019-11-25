package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Patient;

public interface PatientServiceInterface {

    public Patient findBySocialSecurityNumber(String socialSecurityNumber);
    public Patient save(Patient patient);
}
