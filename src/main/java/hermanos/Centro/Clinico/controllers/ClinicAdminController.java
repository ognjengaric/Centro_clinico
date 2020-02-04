package hermanos.Centro.Clinico.controllers;

import hermanos.Centro.Clinico.dto.ClinicAdminDTO;
import hermanos.Centro.Clinico.dto.DoctorDTO;
import hermanos.Centro.Clinico.exception.ResourceConflictException;
import hermanos.Centro.Clinico.model.Address;
import hermanos.Centro.Clinico.model.ClinicAdministrator;
import hermanos.Centro.Clinico.model.Doctor;
import hermanos.Centro.Clinico.security.TokenUtils;
import hermanos.Centro.Clinico.service.ClinicAdministratorService;
import hermanos.Centro.Clinico.service.CustomUserDetailsService;
import hermanos.Centro.Clinico.service.DoctorService;
import hermanos.Centro.Clinico.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@RestController
@RequestMapping(value = "/clinicAdmin")
public class ClinicAdminController {

    @Autowired
    PersonService personService;

    @Autowired
    ClinicAdministratorService clinicAdminService;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    TokenUtils tokenUtils;

    static class PasswordChanger {
        public String oldPassword = "";
        public String newPassword = "";
    }

    @RequestMapping(method = RequestMethod.GET, consumes = "application/json")
    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    public ResponseEntity<?> getClinicAdminData(Principal p) {

        ClinicAdministrator clinicAdministrator = (ClinicAdministrator) personService.findByEmail(p.getName());

        if(clinicAdministrator == null){
            throw new ResourceConflictException("Clinic administrator not found.");
        }

        ClinicAdminDTO clinicAdminDTO = new ClinicAdminDTO(clinicAdministrator);

        return ResponseEntity.ok(clinicAdminDTO);
    }
  
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    public ResponseEntity<?> editClinicAdminData(@RequestBody ClinicAdminDTO clinicAdminDTO, Principal p) {
        ClinicAdministrator clinicAdministrator = (ClinicAdministrator) personService.findByEmail(p.getName());

        if (clinicAdministrator == null) {
            throw new ResourceConflictException("Clinic administrator not found.");
        }

        clinicAdministrator.setName(clinicAdminDTO.getName());
        clinicAdministrator.setSurname(clinicAdminDTO.getSurname());
        clinicAdministrator.setPhoneNumber(clinicAdminDTO.getPhoneNumber());
        if((clinicAdministrator.getAddress().getCity() != clinicAdminDTO.getCity()) || (clinicAdministrator.getAddress().getCountry() != clinicAdminDTO.getCountry()) ||
            (clinicAdministrator.getAddress().getPostcode() != clinicAdminDTO.getPostcode()) || (clinicAdministrator.getAddress().getStreet() != clinicAdminDTO.getStreet()) ||
            (clinicAdministrator.getAddress().getNumber() != clinicAdminDTO.getStreetNumber())){

            clinicAdministrator.setAddress(new Address());
            clinicAdministrator.getAddress().setNumber(clinicAdminDTO.getStreetNumber());
            clinicAdministrator.getAddress().setCity(clinicAdminDTO.getCity());
            clinicAdministrator.getAddress().setCountry(clinicAdminDTO.getCountry());
            clinicAdministrator.getAddress().setPostcode(clinicAdminDTO.getPostcode());
            clinicAdministrator.getAddress().setStreet(clinicAdminDTO.getStreet());
        }


        clinicAdminService.save(clinicAdministrator);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "change-password")
    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger, HttpServletRequest request)
            throws ServletException {

        userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

        request.logout();

        return ResponseEntity.ok().build();
    }
}