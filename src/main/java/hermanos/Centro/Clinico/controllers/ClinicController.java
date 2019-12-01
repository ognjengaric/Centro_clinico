package hermanos.Centro.Clinico.controllers;


import hermanos.Centro.Clinico.model.*;
import hermanos.Centro.Clinico.service.interfaces.ClinicAdministratorServiceInterface;
import hermanos.Centro.Clinico.service.interfaces.ClinicServiceInterface;
import hermanos.Centro.Clinico.service.interfaces.CheckupDateServiceInterface;
import hermanos.Centro.Clinico.service.interfaces.DoctorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/clinic")
public class ClinicController {
    @Autowired
    ClinicAdministratorServiceInterface clinicAdminService;

    @Autowired
    ClinicServiceInterface clinicService;

    @Autowired
    DoctorServiceInterface doctorService;

    @Autowired
    CheckupDateServiceInterface checkupDateService;

    @RequestMapping(method = RequestMethod.GET,path = "/{id}")
    public Clinic clinicInfo(@PathVariable("id") long id){
        Clinic clinic = clinicService.findById(id);

        return clinic;

    }

    @RequestMapping(method = RequestMethod.POST, path = "/addCheckupType/{id}")
    public ResponseEntity<?> newCheckupType(@PathVariable("id") long id, @RequestBody String ct) {

   //     clinicService.findById(id).addCheckupType(ct);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/addCheckupDate/{id}")
    public ResponseEntity newCheckupDate(@PathVariable("id") long id, @RequestBody String date){
        String[] temp = date.split(":");
        String datatemp = temp[1].substring(1, temp[1].length()-2);
        CheckupDate cd = new CheckupDate();
        cd.setClinic(clinicService.findById(id));
        cd.setDate(datatemp);

        checkupDateService.save(cd);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getCheckupDates/{id}")
    public @ResponseBody List<CheckupDate> getCheckupDates(@PathVariable("id") long id){
        return clinicService.findById(id).getCheckupDates();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/addDoctor/{id}")
    public ResponseEntity newDoctor(@PathVariable("id") long id, @RequestBody PatientRequest pr){
        Address adr = new Address();
        adr.setCity(pr.getCity());
        adr.setCountry(pr.getCountry());
        adr.setNumber(pr.getPhoneNumber());
        adr.setPostcode(pr.getPostcode());
        adr.setStreet(pr.getStreet());


        Doctor doctor = new Doctor();
        doctor.setAddress(adr);
        doctor.setEmail(pr.getEmail());
        doctor.setAvgrating("0");
        doctor.setClinic(clinicService.findById(id));
        doctor.setPassword(pr.getPassword());
        doctor.setName(pr.getName());
        doctor.setSurname(pr.getSurname());
        doctor.setPhoneNumber(pr.getPhoneNumber());


        doctor.setClinic(clinicService.findById(id));
        doctor.setAvgrating("0");


        doctorService.save(doctor);

        return ResponseEntity.ok().build();
    }

}
