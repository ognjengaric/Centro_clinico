package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.ClinicAdministrator;

public interface ClinicAdministratorServiceInterface {

    public ClinicAdministrator findByiD(long id);
    public ClinicAdministrator save(ClinicAdministrator clinicAdministrator);
    public ClinicAdministrator findByEmail(String email);
}
