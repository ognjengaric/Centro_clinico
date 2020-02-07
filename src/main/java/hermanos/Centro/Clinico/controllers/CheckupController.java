package hermanos.Centro.Clinico.controllers;

import hermanos.Centro.Clinico.model.Checkup;
import hermanos.Centro.Clinico.service.interfaces.CheckupServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/checkup")
public class CheckupController {

    @Autowired
    CheckupServiceInterface checkupService;

    @RequestMapping(method = RequestMethod.GET, path = "/acceptUpdatedAppointment/{id}")
    public ResponseEntity acceptUpdatedAppointment(@PathVariable("id") String id) throws URISyntaxException {
        Checkup checkup = checkupService.findById(Long.parseLong(id));
        checkup.setPendingPatient(false);
        checkup.setApproved(true);
        checkupService.save(checkup);

        URI loginURI = new URI("http://localhost:3000");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(loginURI);

        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/declineUpdatedAppointment/{id}")
    public ResponseEntity declineUpdatedAppointment(@PathVariable("id") String id) throws URISyntaxException {
        checkupService.deleteById(Long.parseLong(id));

        URI loginURI = new URI("http://localhost:3000");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(loginURI);

        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

}
