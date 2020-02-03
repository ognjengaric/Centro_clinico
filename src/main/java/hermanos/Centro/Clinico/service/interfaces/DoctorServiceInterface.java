package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Doctor;

public interface DoctorServiceInterface {

    public Doctor findById(long id);
    public Doctor findByEmail(String email);
    public Doctor save(Doctor doctor);
    public void deleteById(long id);
}
