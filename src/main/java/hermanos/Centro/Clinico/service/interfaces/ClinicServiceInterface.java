package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Clinic;

public interface ClinicServiceInterface {

    public Clinic findById(long id);
    public Clinic save(Clinic clinic);
}
