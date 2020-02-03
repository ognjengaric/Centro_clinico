package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.dto.ScheduleFilterDTO;
import hermanos.Centro.Clinico.model.Checkup;
import hermanos.Centro.Clinico.model.Patient;

public interface CheckupServiceInterface {
    Checkup findById(long id);
    Checkup save(Checkup checkup);
    boolean isValid(Checkup checkup);
    void deleteById(long id);
    Checkup makeNewCheckup(ScheduleFilterDTO filterDTO, Patient patient, boolean isPredefined);
}
