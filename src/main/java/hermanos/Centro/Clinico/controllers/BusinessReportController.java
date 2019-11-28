package hermanos.Centro.Clinico.controllers;


import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.model.ClinicAdministrator;
import hermanos.Centro.Clinico.model.Person;
import hermanos.Centro.Clinico.service.interfaces.ClinicAdministratorServiceInterface;
import hermanos.Centro.Clinico.service.interfaces.ClinicServiceInterface;
import hermanos.Centro.Clinico.service.interfaces.PersonServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "")
public class BusinessReportController {
    @Autowired
    ClinicAdministratorServiceInterface clinicAdminService;

    @Autowired
    ClinicServiceInterface clinicService;

    @RequestMapping(method = RequestMethod.GET,path = "/viewBusinessReport")
    public ArrayList<?> businessReport(@RequestBody Person person){

        ClinicAdministrator admin = clinicAdminService.findByiD(person.getId());

        Clinic clinic = clinicService.findById(admin.getClinicId());

        ArrayList<String> data = new ArrayList<>();

        // data = clinic.getBusinessReport();

        return data;

    }
}
