package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Doctor;

public interface DoctorServiceInterface {

    public Doctor findBySocialSecurityNumber(String socialSecurityNumber);
    public Doctor save(Doctor doctor);
}
