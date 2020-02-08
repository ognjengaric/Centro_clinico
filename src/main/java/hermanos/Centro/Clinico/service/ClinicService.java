package hermanos.Centro.Clinico.service;


import hermanos.Centro.Clinico.model.Checkup;
import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.model.PredefinedCheckup;
import hermanos.Centro.Clinico.model.StartEndTime;
import hermanos.Centro.Clinico.repository.ClinicRepository;
import hermanos.Centro.Clinico.service.interfaces.ClinicServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClinicService implements ClinicServiceInterface {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${back-uri}")
    private String uri;

    @Override
    public Clinic findById(long id){
        return  clinicRepository.findById(id);
    }

    @Override
    public Clinic save(Clinic clinic){
        return clinicRepository.save(clinic);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public void deleteExpiredPredefinedCheckups(Clinic clinic) {
        List<PredefinedCheckup> temp = new ArrayList<>(clinic.getPredefinedCheckups());

        for(PredefinedCheckup checkup : temp){
            if(checkup.getDate().compareTo(LocalDate.now()) <= 0 && checkup.getStartEnd().getStartTime().
                    compareTo(LocalTime.now()) <= 0){
                clinic.getPredefinedCheckups().remove(checkup);
            }
        }
        clinicRepository.save(clinic);
    }

    public List<Clinic> findAll() {
        List<Clinic> result = clinicRepository.findAll();
        return result;
    }

    @Override
    public void mailAppointment(Checkup checkup, boolean isAccepted) throws MessagingException {

        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(checkup.getPatient().getEmail());

        if(isAccepted)
            helper.setSubject("Appointment approved");
        else
            helper.setSubject("Appointment denied");

        String content = "Dear " + checkup.getPatient().getName() + ", \n";
        if(isAccepted)
            content += "Your appointment with following information has been approved by our administrator staff.\n\n";
        else
            content += "Your appointment with following information has been rejected by our administrator staff.\n\n";

        content += "Checkup type: " + checkup.getType().getName();
        content += "\nDate: " + checkup.getDate().toString();
        content += "\nStart time: " + checkup.getStartEnd().getStartTime().toString();
        content += "\nEnd time: " + checkup.getStartEnd().getEndTime().toString();
        content += "\nClinic name: " + checkup.getClinic().getName();
        content += "\nDoctor: " + checkup.getDoctor().getName() + " " + checkup.getDoctor().getSurname();
        content += "\n\nSincerely, Centro Clinico support team.";

        helper.setText(content);

        javaMailSender.send(msg);
    }

    @Override
    public void mailAppointmentUpdated(Checkup checkup) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(checkup.getPatient().getEmail());
        String accept =  uri + "/checkup/acceptUpdatedAppointment/" + checkup.getId();
        String decline = uri + "/checkup/declineUpdatedAppointment/" + checkup.getId();
        helper.setSubject("Appointment updated");

        String content = "Dear " + checkup.getPatient().getName() + ", <br/>";

        content += "Your appointment with following information has been updated by our administrator staff.<br/><br/>";
        content += "Checkup type: " + checkup.getType().getName();
        content += "<br/>" + "<b>New date: " + checkup.getDate().toString();
        content += "</b><br/><b>New start time: " + checkup.getStartEnd().getStartTime().toString();
        content += "</b><br/> <b>New end time: " + checkup.getStartEnd().getEndTime().toString();
        content += "</b><br/>Clinic name: " + checkup.getClinic().getName();
        content += "<br/>Doctor: " + checkup.getDoctor().getName() + " " + checkup.getDoctor().getSurname();
        content += "<br/><br/>Do you accept new appointment time and date?";
        content += "<br/><br/><a href='"+accept+"'>Yes</a>";
        content += "<br/><br/><a href='"+decline+"'>No</a>";
        content += "<br/><br/>Sincerely, Centro Clinico support team.";

        helper.setText(content, true);

        javaMailSender.send(msg);
    }
}

