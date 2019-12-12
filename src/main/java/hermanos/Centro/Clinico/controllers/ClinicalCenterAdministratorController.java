package hermanos.Centro.Clinico.controllers;


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

        personService.save(person);
        //Polsati generisan mail

        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/decline")
    public ResponseEntity requestDeclined(@RequestBody Person person){

        //Polsati generisan mail

        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/registerClinic")
    public ResponseEntity requestAccepted(@RequestBody Clinic clinic){


        clinicService.save(clinic);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, consumes = "application/json", path = "/getPendingRequests")
    public List<PatientRequest> getAllPendingRequests(){

        //patientRequestService.findAll();

        return patientRequestService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/registerClinicCentAdmin")
    public ResponseEntity registerClinicCentAdmin(@RequestBody ClinicalCenterAdministrator clinicalCenterAdministrator){


        clinicalCenterAdministratorService.save(clinicalCenterAdministrator);

        return ResponseEntity.ok().build();
    }
}
