package hermanos.Centro.Clinico.controllers;


import hermanos.Centro.Clinico.dto.CheckupDoctorDTO;
import hermanos.Centro.Clinico.dto.MedicalRecordDTO;
import hermanos.Centro.Clinico.dto.PatientDTO;
import hermanos.Centro.Clinico.exception.ResourceConflictException;
import hermanos.Centro.Clinico.model.*;
import hermanos.Centro.Clinico.service.AuthorityService;
import hermanos.Centro.Clinico.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/clinicalCenterAdministrator")
public class ClinicalCenterAdministratorController {

    @Autowired
    DoctorServiceInterface doctorService;
    @Autowired
    NurseServiceInterface nurseService;
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
    MedicalRecordServiceInterface medicalRecordService;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    ReportServiceInterface reportService;
    @Autowired
    PrescriptionServiceInterface prescriptionService;
    @Autowired
    AuthorityService authorityService;
    @Autowired
    CheckupServiceInterface checkupService;


    static boolean hasPrescription = false;

    @PreAuthorize("hasAuthority('CLINIC_CENTER_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/accept")
    public ResponseEntity requestAccepted(@RequestBody Patient patient){

        //Patient p = (Patient)patientRequestService.findBySocialSecurityNumber(socialSecurityNumber);

        List<Authority> authorities = authorityService.findByName("PATIENT");
        patient.setAuthorities(authorities);

        MedicalRecord mr = new MedicalRecord();
        patient.setMedicalRecord(mr);
        medicalRecordService.save(mr);
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
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/decline/{description}")
    public ResponseEntity requestDeclined(@RequestBody String mail, @PathVariable("description") String description){

        String[] part = mail.split(":");
        String email = part[1].substring(1, part[1].length() - 2);
        PatientRequest patient = patientRequestService.findByEmail(email);
        patientRequestService.remove(patient.getSocialSecurityNumber());
        sendDeclineEmail(patient.getEmail(), description, patient.getName(), patient.getSurname());
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
    //ovo sve ide doktoru
    @PreAuthorize("hasAuthority('DOCTOR')")
    @RequestMapping(method = RequestMethod.GET, consumes = "application/json", path = "/getDiagnosis")
    public ResponseEntity<?> getAllDiagnosis(){

        List<Diagnosis> pr = diagnosisService.findAll();

        return ResponseEntity.ok(pr);
    }

    @PreAuthorize("hasAuthority('NURSE')")
    @RequestMapping(method = RequestMethod.GET, consumes = "application/json", path = "/getPatients")
    public ResponseEntity<?> getAllPatients(){

        List<Person> patientList =  personService.findAll();
        List<PatientDTO> patientDTOList = new ArrayList<>();
        List<Authority> authorities = authorityService.findByName("PATIENT");
        for(Person p : patientList) {
            List<Authority> authoritiesP = (List<Authority>) p.getAuthorities();
            if(authoritiesP.get(0).equals(authorities.get(0))) {
                PatientDTO patientDTO = new PatientDTO((Patient) p);
                patientDTOList.add(patientDTO);
            }
        }
        return ResponseEntity.ok(patientDTOList);

    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @RequestMapping(method = RequestMethod.GET, consumes = "application/json", path = "/getMedicines")
    public ResponseEntity<?> getAllMedicines(){

        List<Medicine> pr = medicineService.findAll();

        return ResponseEntity.ok(pr);
    }

    @PreAuthorize("hasAuthority('NURSE')")
    @RequestMapping(method = RequestMethod.GET, consumes = "application/json", path = "/getPrescriptions")
    public ResponseEntity<?> getAllPrescriptions(){

        List<Prescription> pr = prescriptionService.findAll();
        List<Prescription> ret = new ArrayList<>();

        for(Prescription p : pr){
            if(!p.isCertified()){
                ret.add(p);
            }
        }

        return ResponseEntity.ok(ret);
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @RequestMapping(method = RequestMethod.GET, path = "/getMedical/{id}")
    public MedicalRecordDTO getMedicalRecordDoctor(@PathVariable String id){

        Checkup ch = checkupService.findById(Integer.parseInt(id));
        Patient p = ch.getPatient();

        MedicalRecord md = p.getMedicalRecord();
        MedicalRecordDTO mdDTO = new MedicalRecordDTO(md);
        return mdDTO;
    }

    @PreAuthorize("hasAuthority('DOCTOR') or hasAuthority('NURSE')")
    @RequestMapping(method = RequestMethod.GET, path = "/getCheckups")
    public List<CheckupDoctorDTO> getCheckups(Principal p){
        long id = personService.findByEmail(p.getName()).getId();
        List<Checkup> checkupList = new ArrayList<Checkup>();
        if(doctorService.findById(id) != null){
            checkupList = doctorService.findById(id).getClinic().getCheckups();
        }else{
            checkupList = nurseService.findById(id).getClinic().getCheckups();
        }

        List<CheckupDoctorDTO> list = new ArrayList<>();
        for(Checkup tch : checkupList){
            if(tch.isApproved()) {
                Checkup ch = checkupService.findById(tch.getId());
                CheckupDoctorDTO dt0 = new CheckupDoctorDTO(ch.getId(), ch.getDate(), ch.getStartEnd().getStartTime(), ch.getStartEnd().getEndTime(), ch.getDoctor().getId(), ch.getDoctor().getName(), ch.getPatient().getId(), ch.getPatient().getName(), ch.getRoom().getId());
                list.add(dt0);
            }
        }

        return list;
    }

    @PreAuthorize("hasAuthority('DOCTOR') or hasAuthority('NURSE')")
    @RequestMapping(method = RequestMethod.GET, path = "/getCheckupInfo/{id}")
    public CheckupDoctorDTO getCheckupInfo(@PathVariable String id){

        Checkup ch = checkupService.findById(Integer.parseInt(id));
        CheckupDoctorDTO dt0 = new CheckupDoctorDTO(ch.getId(), ch.getDate(), ch.getStartEnd().getStartTime(), ch.getStartEnd().getEndTime(), ch.getDoctor().getId(), ch.getDoctor().getName(), ch.getPatient().getId(), ch.getPatient().getName(), ch.getRoom().getId());

        return dt0;
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @RequestMapping(method = RequestMethod.POST, path = "/setMedical/{id}")
    public ResponseEntity<?> setMedicalRecordDoctor(@RequestBody MedicalRecord medicalRecord, @PathVariable String id){
        Checkup ch = checkupService.findById(Integer.parseInt(id));
        Patient p = ch.getPatient();

        p.getMedicalRecord().setAge(medicalRecord.getAge());
        p.getMedicalRecord().setAllergies(medicalRecord.getAllergies());
        p.getMedicalRecord().setBloodType(medicalRecord.getBloodType());
        p.getMedicalRecord().setDiopter(medicalRecord.getDiopter());
        p.getMedicalRecord().setWeight(medicalRecord.getWeight());
        p.getMedicalRecord().setHeight(medicalRecord.getHeight());
        personService.save(p);

        return ResponseEntity.ok().build();
    }

//    @PreAuthorize("hasAuthority('CLINIC_CENTER_ADMIN')")
//    @RequestMapping(method = RequestMethod.GET, path = "/getMedical")
//    public MedicalRecordDTO getMedicalRecordDoctor(){
//
//        List<Person> p_list = personService.findAll();
//        Patient p = (Patient) p_list.get(p_list.size()-1);
//        MedicalRecord md = p.getMedicalRecord();
//        MedicalRecordDTO mdDTO = new MedicalRecordDTO(md);
//        return mdDTO;
//    }


//    @PreAuthorize("hasAuthority('CLINIC_CENTER_ADMIN')")
//    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/doCheckup")
//    public ResponseEntity doCheckup(@RequestBody Report report){
//
//        if(hasPrescription){
//            List<Prescription> p_list = prescriptionService.findAll();
//            Prescription prescription = p_list.get(p_list.size() - 1);
//            report.setPrescription(prescription);
//        }
//        hasPrescription = false;
//        reportService.save(report);
//
//        return ResponseEntity.ok().build();
//    }


    //RADI ZA MAIL
//    @PreAuthorize("hasAuthority('CLINIC_CENTER_ADMIN')")
//    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/doCheckup/{id}")
//    public ResponseEntity doCheckup(@RequestBody Report report, @PathVariable("id") String id){
//
//        if(hasPrescription){
//            List<Prescription> p_list = prescriptionService.findAll();
//            Prescription prescription = p_list.get(p_list.size() - 1);
//            report.setPrescription(prescription);
//        }
//        hasPrescription = false;
//        id += ".com";
//        Patient pat = (Patient) personService.findByEmail(id);
//        pat.getMedicalRecord().setReport(report);
//        personService.save(pat);
//        reportService.save(report);
//
//        return ResponseEntity.ok().build();
//    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/doCheckup/{id}")
    public ResponseEntity doCheckup(@RequestBody Report report, @PathVariable("id") String id){

        if(hasPrescription){
            List<Prescription> p_list = prescriptionService.findAll();
            Prescription prescription = p_list.get(p_list.size() - 1);
            report.setPrescription(prescription);
        }
        hasPrescription = false;
        Checkup ch = checkupService.findById(Integer.parseInt(id));
        Patient pat = ch.getPatient();
        pat.getMedicalRecord().setReport(report);
        ch.setReport(report);
        reportService.save(report);
        checkupService.save(ch);
        personService.save(pat);


        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('NURSE')")
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/certify")
    public ResponseEntity certifyCheckup(Principal p,  @RequestBody Long id){

        ClinicalCenterAdministrator admin = clinicalCenterAdministratorService.findByEmail(p.getName());

        Prescription pre = prescriptionService.findById(id);
        pre.setCertified(true);
        pre.setByWho(admin);
        prescriptionService.save(pre);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/makePrescription")
    public ResponseEntity makePrescription(@RequestBody Prescription prescription){


        prescriptionService.save(prescription);

        hasPrescription = true;
        return ResponseEntity.ok().build();
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
    public ResponseEntity activateAcc(@PathVariable("mail") String mail) throws URISyntaxException {
        Patient patient = (Patient)personService.findByEmail(mail);
        patient.setActivated(true);
        patientService.save(patient);

        URI loginURI = new URI("http://localhost:3000/activated");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(loginURI);

        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    void sendAcceptEmail(String sendTo, String firstName, String lastName) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(sendTo);

        helper.setSubject("Centro Clinico account registration");
        String text = "Dear sir/madam, " + '\n';
        text += "your account request has been reviewed and accepted by our administrator staff. \n Please follow the link below to activate your account.";
        text += "http://localhost:8080/clinicalCenterAdministrator/activate/" + sendTo + "\n\n\n" + "Sincerely, Centro Clinico support team.";
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

    void sendDeclineEmail(String sendTo, String description, String firstName, String lastName) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(sendTo);

        msg.setSubject("Centro Clinico account registration");
        String text = "Dear sir/madam, " + '\n';
        text += "your account request has been reviewed. Unfortunately, it has been declined, with an administrator message attached:";
        text += "\n\n\n" + description;
        text += "\n\n\n" + "Sincerely, Centro Clinico support team.";
        msg.setText(text);

        javaMailSender.send(msg);

    }
}
