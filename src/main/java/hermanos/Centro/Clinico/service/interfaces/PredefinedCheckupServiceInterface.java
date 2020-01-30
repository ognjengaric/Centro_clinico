package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.PredefinedCheckup;

public interface PredefinedCheckupServiceInterface {

    PredefinedCheckup findById(long id);
    PredefinedCheckup save(PredefinedCheckup predefinedCheckup);
    void deleteById(long id);

    boolean isValid(PredefinedCheckup checkup);
}
