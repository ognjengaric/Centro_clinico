package hermanos.Centro.Clinico.controllers;

import hermanos.Centro.Clinico.exception.ResourceConflictException;
import hermanos.Centro.Clinico.model.Patient;
import hermanos.Centro.Clinico.security.TokenUtils;
import hermanos.Centro.Clinico.service.PatientService;
import hermanos.Centro.Clinico.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@RestController
@RequestMapping(value = "/patient")
public class PatientController {

    @Autowired
    PersonService personService;

    @Autowired
    TokenUtils tokenUtils;

    @RequestMapping(method = RequestMethod.GET, consumes = "application/json")
    @PreAuthorize("hasAuthority('PATIENT')")
    public ResponseEntity<?> getPatientData(Principal p) {

        Patient patient = (Patient) personService.findByEmail(p.getName());

        if(p == null){
            throw new ResourceConflictException("Patient not found.");
        }

        return ResponseEntity.ok(patient);
    }

}