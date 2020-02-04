package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.dto.ScheduleFilterDTO;
import hermanos.Centro.Clinico.model.AbsenceRequest;
import hermanos.Centro.Clinico.model.Checkup;
import hermanos.Centro.Clinico.model.Patient;

import java.util.List;

public interface AbsenceRequestServiceInterface {
    AbsenceRequest findById(long id);
    AbsenceRequest save(AbsenceRequest absenceRequest);
    List<AbsenceRequest> findAll();
}
