package hermanos.Centro.Clinico.controllers;


import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.service.interfaces.ClinicServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/viewBusinessReport")
public class BusinessReportController {
    @Autowired
    ClinicServiceInterface clinicService;

    @RequestMapping(method = RequestMethod.GET,path = "/{id}")
    public Clinic businessReport(@PathVariable("id") long id){
        Clinic clinic = clinicService.findById(id);
        return clinic;
    }
}
