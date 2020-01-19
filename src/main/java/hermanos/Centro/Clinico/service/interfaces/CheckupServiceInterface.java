package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Checkup;

public interface CheckupServiceInterface {
    public Checkup findById(long id);
    public Checkup save(Checkup checkup);
}
