package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Checkup;

public interface CheckupServiceInterface {
    Checkup findById(long id);
    Checkup save(Checkup checkup);
    boolean isValid(Checkup checkup);
    void deleteById(long id);
}
