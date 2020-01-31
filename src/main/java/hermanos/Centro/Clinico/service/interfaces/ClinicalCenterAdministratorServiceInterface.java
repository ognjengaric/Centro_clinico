package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.controllers.ClinicController;
import hermanos.Centro.Clinico.model.ClinicalCenterAdministrator;

public interface ClinicalCenterAdministratorServiceInterface {

    public ClinicalCenterAdministrator findByiD(long id);
    public ClinicalCenterAdministrator save(ClinicalCenterAdministrator clinicalCenterAdministrator);
    public ClinicalCenterAdministrator findByEmail(String email);
}
