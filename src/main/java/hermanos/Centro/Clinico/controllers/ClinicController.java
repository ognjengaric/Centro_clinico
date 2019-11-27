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
@RequestMapping(value = "/clinic")
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

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/addCheckupType")
    public ResponseEntity<?> newCheckupType(@PathVariable("id") long id, @RequestBody String ct) {

        clinicService.findById(id).addCheckupType(ct);

        return ResponseEntity.ok().build();
    }

}
