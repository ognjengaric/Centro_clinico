package hermanos.Centro.Clinico.controllers;


import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.model.Person;
import hermanos.Centro.Clinico.repository.ClinicRepository;
import hermanos.Centro.Clinico.repository.PersonRepository;
import hermanos.Centro.Clinico.service.interfaces.ClinicServiceInterface;
import hermanos.Centro.Clinico.service.interfaces.PersonServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clinicalCenterAdministrator")
public class ClinicalCenterAdministratorController {

    @Autowired
    PersonServiceInterface personService;
    @Autowired
    ClinicServiceInterface clinicService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/accept")
    public ResponseEntity requestAccepted(@RequestBody Person person){

        personService.save(person);
        //Polsati generisn mail

        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/decline")
    public ResponseEntity requestDeclined(@RequestBody Person person){

        //Polsati generisn mail

        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/registerClinic")
    public ResponseEntity requestAccepted(@RequestBody Clinic clinic){

        clinicService.save(clinic);

        return ResponseEntity.ok().build();
    }
}
