package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Clinic;

import java.util.List;

public interface ClinicServiceInterface {

    Clinic findById(long id);
    Clinic save(Clinic clinic);
    void deleteExpiredPredefinedCheckups(Clinic clinic);
    List<Clinic> findAll();
}
