package hermanos.Centro.Clinico.controllers;


import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.model.ClinicAdministrator;
import hermanos.Centro.Clinico.model.Person;
import hermanos.Centro.Clinico.service.interfaces.ClinicAdministratorServiceInterface;
import hermanos.Centro.Clinico.service.interfaces.ClinicServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/editClinicProfile")
public class ClinicController {
    @Autowired
    ClinicAdministratorServiceInterface clinicAdminService;

    @Autowired
    ClinicServiceInterface clinicService;

    @RequestMapping(method = RequestMethod.GET,path = "")
    public Clinic clinicInfo(@RequestBody Person person){

        ClinicAdministrator admin = clinicAdminService.findByiD(person.getId());

        Clinic clinic = clinicService.findById(admin.getClinicId());

        return clinic;

    }
/*
    @RequestMapping(method = RequestMethod.POST, path = "/addCheckupType")
    public ResponseEntity<?> newCheckupType(@RequestBody String ct){
        ClinicAdministrator admin = clinicAdminService.findByiD(person.getId());

        clinicService.findById(admin.getClinicId()).addCheckupType(ct);


    }*/
}
