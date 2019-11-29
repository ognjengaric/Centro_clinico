package hermanos.Centro.Clinico.controllers;

import hermanos.Centro.Clinico.exception.ResourceConflictException;
import hermanos.Centro.Clinico.model.Patient;
import hermanos.Centro.Clinico.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @RequestMapping(method = RequestMethod.GET, consumes = "application/json", path = "/{socialSecurityNumber}")
    public ResponseEntity<?> createRegisterRequest(@PathVariable("socialSecurityNumber") String ssNumber) {

        Patient p = patientService.findBySocialSecurityNumber(ssNumber);

        if (p == null) {
            throw new ResourceConflictException("Patient with this social security number does not exist.");
        }

        return ResponseEntity.ok(p);
    }
    
}