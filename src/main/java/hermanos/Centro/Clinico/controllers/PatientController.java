package hermanos.Centro.Clinico.controllers;

import hermanos.Centro.Clinico.dto.PatientDTO;
import hermanos.Centro.Clinico.model.Patient;
import hermanos.Centro.Clinico.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "patient")
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity registerPatient(@RequestBody Patient patient){
        //dodaj proveru vrednosti polja

        patientRepository.save(patient);

        return ResponseEntity.ok().build();
    }
}
