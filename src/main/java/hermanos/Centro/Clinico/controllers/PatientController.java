package hermanos.Centro.Clinico.controllers;

import hermanos.Centro.Clinico.dto.PatientDTO;
import hermanos.Centro.Clinico.exception.ResourceConflictException;
import hermanos.Centro.Clinico.model.Patient;
import hermanos.Centro.Clinico.security.TokenUtils;
import hermanos.Centro.Clinico.service.CustomUserDetailsService;
import hermanos.Centro.Clinico.service.PatientService;
import hermanos.Centro.Clinico.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@RestController
@RequestMapping(value = "/patient")
public class PatientController {

    @Autowired
    PersonService personService;

    @Autowired
    PatientService patientService;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    TokenUtils tokenUtils;

    static class PasswordChanger {
        public String oldPassword = "";
        public String newPassword = "";
    }

    @RequestMapping(method = RequestMethod.GET, consumes = "application/json")
    @PreAuthorize("hasAuthority('PATIENT')")
    public ResponseEntity<?> getPatientData(Principal p) {

        Patient patient = (Patient) personService.findByEmail(p.getName());

        if(patient == null){
            throw new ResourceConflictException("Patient not found.");
        }

        PatientDTO patientDTO = new PatientDTO(patient);

        return ResponseEntity.ok(patientDTO);
    }
  
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @PreAuthorize("hasAuthority('PATIENT')")
    public ResponseEntity<?> editPatientData(@RequestBody PatientDTO patientDTO, Principal p) {
        Patient patient = (Patient) personService.findByEmail(p.getName());

        if (patient == null) {
            throw new ResourceConflictException("Patient not found.");
        }

        patientService.editPatientFields(patientDTO, patient);

        personService.save(patient);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "change-password")
    @PreAuthorize("hasAuthority('PATIENT')")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger, HttpServletRequest request)
            throws ServletException {

        userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

        request.logout();

        return ResponseEntity.ok().build();
    }
}