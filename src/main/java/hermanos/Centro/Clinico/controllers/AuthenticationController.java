package hermanos.Centro.Clinico.controllers;

import hermanos.Centro.Clinico.exception.ResourceConflictException;
import hermanos.Centro.Clinico.model.PatientRequest;
import hermanos.Centro.Clinico.model.PersonTokenState;
import hermanos.Centro.Clinico.security.TokenUtils;
import hermanos.Centro.Clinico.security.auth.JwtAuthenticationRequest;
import hermanos.Centro.Clinico.service.CustomUserDetailsService;
import hermanos.Centro.Clinico.service.PatientService;
import hermanos.Centro.Clinico.service.interfaces.PatientRequestServiceInterface;
import hermanos.Centro.Clinico.service.interfaces.PersonServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PatientRequestServiceInterface patientRequestService;

    @Autowired
    private PersonServiceInterface personService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private CustomUserDetailsService userDetailsService;


    /** Function that creates patient registration request which will be later approved or declined
     * @param patientRequest
     * @return
     */

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/register")
    public ResponseEntity<?> createRegisterRequest(@RequestBody PatientRequest patientRequest){

        //check for all active users in system
        //check for all requests

        if(personService.findByEmail(patientRequest.getEmail()) != null ||
            patientRequestService.findByEmail(patientRequest.getEmail()) != null){

            throw new ResourceConflictException("This email is already in use.");
        }

        if(patientService.findBySocialSecurityNumber(patientRequest.getSocialSecurityNumber()) != null ||
            patientRequestService.findBySocialSecurityNumber(patientRequest.getSocialSecurityNumber()) != null){

            throw new ResourceConflictException("This social security number is already is already in use.");
        }

        patientRequestService.save(patientRequest);

        return ResponseEntity.ok().build();
    }

    /** Function that checks credentials and creates token
     * @param authenticationRequest
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest){

        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails details = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        //username = email in this case
        String jwt = tokenUtils.generateToken(details.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(new PersonTokenState(jwt, expiresIn));
    }

}
