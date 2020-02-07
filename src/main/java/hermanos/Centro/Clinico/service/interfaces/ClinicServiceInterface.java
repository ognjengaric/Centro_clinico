package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Checkup;
import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.model.StartEndTime;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ClinicServiceInterface {

    Clinic findById(long id);
    Clinic save(Clinic clinic);
    void deleteExpiredPredefinedCheckups(Clinic clinic);
    List<Clinic> findAll();
    void mailAppointment(Checkup checkup, boolean isAccepted) throws MessagingException;
    void mailAppointmentUpdated(Checkup checkup) throws MessagingException;
}
