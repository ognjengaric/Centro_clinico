package hermanos.Centro.Clinico.controllers;

import hermanos.Centro.Clinico.dto.DoctorDTO;
import hermanos.Centro.Clinico.dto.PatientDTO;
import hermanos.Centro.Clinico.exception.ResourceConflictException;
import hermanos.Centro.Clinico.model.Address;
import hermanos.Centro.Clinico.model.Doctor;
import hermanos.Centro.Clinico.model.Patient;
import hermanos.Centro.Clinico.security.TokenUtils;
import hermanos.Centro.Clinico.service.CustomUserDetailsService;
import hermanos.Centro.Clinico.service.DoctorService;
import hermanos.Centro.Clinico.service.PatientService;
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
@RequestMapping(value = "/doctor")
public class DoctorController {

    @Autowired
    PersonService personService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    TokenUtils tokenUtils;

    static class PasswordChanger {
        public String oldPassword = "";
        public String newPassword = "";
    }

    @RequestMapping(method = RequestMethod.GET, consumes = "application/json")
    @PreAuthorize("hasAuthority('DOCTOR')")
    public ResponseEntity<?> getDoctorData(Principal p) {

        Doctor doctor = (Doctor) personService.findByEmail(p.getName());

        if(doctor == null){
            throw new ResourceConflictException("Doctor not found.");
        }

        DoctorDTO doctorDTO = new DoctorDTO(doctor);

        return ResponseEntity.ok(doctorDTO);
    }
  
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @PreAuthorize("hasAuthority('DOCTOR')")
    public ResponseEntity<?> editDoctorData(@RequestBody DoctorDTO doctorDTO, Principal p) {
        Doctor doctor = (Doctor) personService.findByEmail(p.getName());

        if (doctor == null) {
            throw new ResourceConflictException("Doctor not found.");
        }

        doctor.setName(doctorDTO.getName());
        doctor.setSurname(doctorDTO.getSurname());
        doctor.setPhoneNumber(doctorDTO.getPhoneNumber());
        if((doctor.getAddress().getCity() != doctorDTO.getCity()) || (doctor.getAddress().getCountry() != doctorDTO.getCountry()) ||
            (doctor.getAddress().getPostcode() != doctorDTO.getPostcode()) || (doctor.getAddress().getStreet() != doctorDTO.getStreet()) ||
            (doctor.getAddress().getNumber() != doctorDTO.getStreetNumber())){

            doctor.setAddress(new Address());
            doctor.getAddress().setNumber(doctorDTO.getStreetNumber());
            doctor.getAddress().setCity(doctorDTO.getCity());
            doctor.getAddress().setCountry(doctorDTO.getCountry());
            doctor.getAddress().setPostcode(doctorDTO.getPostcode());
            doctor.getAddress().setStreet(doctorDTO.getStreet());
        }


        doctorService.save(doctor);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "change-password")
    @PreAuthorize("hasAuthority('DOCTOR')")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger, HttpServletRequest request)
            throws ServletException {

        userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

        request.logout();

        return ResponseEntity.ok().build();
    }
}