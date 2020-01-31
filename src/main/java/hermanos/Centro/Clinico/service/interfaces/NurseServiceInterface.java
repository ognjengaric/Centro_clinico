package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Nurse;

public interface NurseServiceInterface {

    public Nurse findById(long id);
    public Nurse save(Nurse nurse);
    public void deleteById(long id);
}
