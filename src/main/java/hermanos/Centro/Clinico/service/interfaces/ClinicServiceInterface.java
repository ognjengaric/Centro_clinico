package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.service.ClinicService;

public interface ClinicServiceInterface {

    public Clinic findById(long id);
    public Clinic save(Clinic patientRequest);
}
