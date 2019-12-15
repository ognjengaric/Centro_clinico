package hermanos.Centro.Clinico.controllers;


import hermanos.Centro.Clinico.exception.ResourceConflictException;
import hermanos.Centro.Clinico.model.*;
import hermanos.Centro.Clinico.service.AuthorityService;
import hermanos.Centro.Clinico.service.PatientService;
import hermanos.Centro.Clinico.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping(value = "/clinicalCenterAdministrator")
public class ClinicalCenterAdministratorController {

    @Autowired
    PersonServiceInterface personService;
    @Autowired
    ClinicServiceInterface clinicService;
    @Autowired
    PatientRequestServiceInterface patientRequestService;
    @Autowired
    ClinicalCenterAdministratorServiceInterface clinicalCenterAdministratorService;
    @Autowired
    DiagnosisServiceInterface diagnosisService;
    @Autowired
    MedicineServiceInterface medicineService;
    @Autowired
    PatientServiceInterface patientService;
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    AuthorityService authorityService;

    @PreAuthorize("hasAuthority('CLINIC_CENTER_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/accept")
    public ResponseEntity requestAccepted(@RequestBody Patient patient){

        //Patient p = (Patient)patientRequestService.findBySocialSecurityNumber(socialSecurityNumber);

        List<Authority> authorities = authorityService.findByName("PATIENT");
        patient.setAuthorities(authorities);

        personService.save(patient);
        patientRequestService.remove(patient.getSocialSecurityNumber());
        try {
            sendAcceptEmail(patient.getEmail(), patient.getName(), patient.getSurname());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully sent email.");

        //Polsati generisan mail

        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasAuthority('CLINIC_CENTER_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/decline")
    public ResponseEntity requestDeclined(@RequestBody Patient patient){

        //Polsati generisan mail
        patientRequestService.remove(patient.getSocialSecurityNumber());
        sendDeclineEmail(patient.getEmail(), patient.getName(), patient.getSurname());
        System.out.println("Declination mail successfully sent.");
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasAuthority('CLINIC_CENTER_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/registerClinic")
    public ResponseEntity registerClinic(@RequestBody Clinic clinic){


        clinicService.save(clinic);

        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasAuthority('CLINIC_CENTER_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, consumes = "application/json", path = "/getPendingRequests")
    public ResponseEntity<?> getAllPendingRequests(){

        List<PatientRequest> pr = patientRequestService.findAll();

        return ResponseEntity.ok(pr);
    }

    @PreAuthorize("hasAuthority('CLINIC_CENTER_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, consumes = "application/json", path = "/getDiagnosis")
    public ResponseEntity<?> getAllDiagnosis(){

        List<Diagnosis> pr = diagnosisService.findAll();

        return ResponseEntity.ok(pr);
    }

    @PreAuthorize("hasAuthority('CLINIC_CENTER_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/registerClinicCentAdmin")
    public ResponseEntity registerClinicCentAdmin(@RequestBody ClinicalCenterAdministrator clinicalCenterAdministrator){


        if(personService.findByEmail(clinicalCenterAdministrator.getEmail()) != null){

            throw new ResourceConflictException("This email is already in use.");
        }


        List<Authority> authorities = authorityService.findByName("CLINIC_CENTER_ADMIN");
        clinicalCenterAdministrator.setAuthorities(authorities);

        clinicalCenterAdministratorService.save(clinicalCenterAdministrator);

        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasAuthority('CLINIC_CENTER_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/addDiagnosis")
    public ResponseEntity addDiagnosis(@RequestBody Diagnosis diagnosis){

        for( Diagnosis d : diagnosisService.findAll()){
            if(d.getCode().equals(diagnosis.getCode())){
                throw new ResourceConflictException("Diagnosis with this code already exists.");
            }
        }

        diagnosisService.save(diagnosis);

        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasAuthority('CLINIC_CENTER_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/addMedicine")
    public ResponseEntity addMedicine(@RequestBody Medicine medicine){

        for( Medicine m : medicineService.findAll()){
            if(m.getCode().equals(medicine.getCode())){
                throw new ResourceConflictException("Medicine with this code already exists.");
            }
        }

        medicineService.save(medicine);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/activate/{mail:.+}")
    public ResponseEntity activateAcc(@PathVariable("mail") String mail){
        Patient patient = (Patient)personService.findByEmail(mail);
        patient.setActivated(true);
        patientService.save(patient);

        return ResponseEntity.ok().build();
    }

    void sendAcceptEmail(String sendTo, String firstName, String lastName) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(sendTo);

        helper.setSubject("DrHelp account registration");
        String text = "Dear sir/madam, " + '\n';
        text += "your account request has been reviewed and accepted by our administrator staff. \n Please follow the link below to activate your account.";
        text += "http://localhost:8080/clinicalCenterAdministrator/activate/" + sendTo + "\n\n\n" + "Forever helping, drHelp.";
        helper.setText(text);

        javaMailSender.send(msg);

    }


//    void sendDeclineEmail(String sendTo, String description, String firstName, String lastName) {
//
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo(sendTo);
//
//        msg.setSubject("DrHelp account registration");
//        String text = "Dear sir/madam, " + '\n';
//        text += "your account request has been reviewed. Unfortunately, it has been declined, with an administrator message attached:";
//        text += "\n\n\n" + description;
//        text += "\n\n\n" + "Forever helping, drHelp.";
//        msg.setText(text);
//
//        javaMailSender.send(msg);
//
//    }

    void sendDeclineEmail(String sendTo, String firstName, String lastName) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(sendTo);

        msg.setSubject("DrHelp account registration");
        String text = "Dear sir/madam, " + '\n';
        text += "your account request has been reviewed. Unfortunately, it has been declined, with an administrator message attached:";
        text += "\n\n\n";
        text += "\n\n\n" + "Forever helping, drHelp.";
        msg.setText(text);

        javaMailSender.send(msg);

    }
}
