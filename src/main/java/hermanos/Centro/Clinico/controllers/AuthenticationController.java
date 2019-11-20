package hermanos.Centro.Clinico.controllers;

import hermanos.Centro.Clinico.model.PatientRequest;
import hermanos.Centro.Clinico.service.interfaces.PatientRequestServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    PatientRequestServiceInterface patientRequestService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/register")
    public ResponseEntity<?> createRegisterRequest(@RequestBody PatientRequest patientRequest){
        //dodaj proveru vrednosti polja
        patientRequestService.save(patientRequest);

        return ResponseEntity.ok().build();
    }

}
