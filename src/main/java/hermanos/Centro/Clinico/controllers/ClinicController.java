package hermanos.Centro.Clinico.controllers;


import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.model.ClinicAdministrator;
import hermanos.Centro.Clinico.model.Person;
import hermanos.Centro.Clinico.service.interfaces.ClinicAdministratorServiceInterface;
import hermanos.Centro.Clinico.service.interfaces.ClinicServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/editClinicProfile")
public class ClinicController {
    @Autowired
    ClinicAdministratorServiceInterface clinicAdminService;

    @Autowired
    ClinicServiceInterface clinicService;

    @RequestMapping(method = RequestMethod.GET,path = "/{id}")
    public Clinic clinicInfo(@PathVariable("id") long id){
        Clinic clinic = clinicService.findById(id);

        return clinic;

    }
/*
    @RequestMapping(method = RequestMethod.POST, path = "/addCheckupType")
    public ResponseEntity<?> newCheckupType(@RequestBody String ct){
        ClinicAdministrator admin = clinicAdminService.findByiD(person.getId());

        clinicService.findById(admin.getClinicId()).addCheckupType(ct);


    }*/
}
