package hermanos.Centro.Clinico.controllers;

import hermanos.Centro.Clinico.dto.MedicalRecordDTO;
import hermanos.Centro.Clinico.dto.PatientDTO;
import hermanos.Centro.Clinico.dto.RatingDTO;
import hermanos.Centro.Clinico.dto.RatingsDTO;
import hermanos.Centro.Clinico.exception.ResourceConflictException;
import hermanos.Centro.Clinico.model.*;
import hermanos.Centro.Clinico.security.TokenUtils;
import hermanos.Centro.Clinico.service.CustomUserDetailsService;
import hermanos.Centro.Clinico.service.PatientService;
import hermanos.Centro.Clinico.service.PersonService;
import hermanos.Centro.Clinico.service.interfaces.ClinicServiceInterface;
import hermanos.Centro.Clinico.service.interfaces.DoctorServiceInterface;
import hermanos.Centro.Clinico.service.interfaces.PatientClinicRatingServiceInterface;
import hermanos.Centro.Clinico.service.interfaces.PatientDoctorRatingServiceInterface;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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

    @Autowired
    PatientDoctorRatingServiceInterface patientDoctorRatingService;

    @Autowired
    PatientClinicRatingServiceInterface patientClinicRatingService;

    @Autowired
    DoctorServiceInterface doctorService;

    @Autowired
    ClinicServiceInterface clinicService;

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

    @PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.GET, consumes = "application/json", path = "/getMedicalRecord")
    public MedicalRecordDTO getMedicalRecordPatient(Principal principal){
        Patient patient = (Patient) userDetailsService.loadUserByUsername(principal.getName());
        MedicalRecord medicalRecord = patient.getMedicalRecord();

        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO(medicalRecord);

        return medicalRecordDTO;
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.GET, consumes = "application/json", path = "/getRatingInfo")
    public RatingsDTO getDoctorsAndClinicsForRating(Principal principal){
        RatingsDTO ratings = new RatingsDTO();

        //lists for tracking duplicates
        List<Long> availableDoctorsID = new ArrayList<>();
        List<Long> availableClinicsID = new ArrayList<>();

        Patient patient = (Patient) userDetailsService.loadUserByUsername(principal.getName());
        List<PatientDoctorRating> alreadyRatedDoctors = patientDoctorRatingService.findAllByPatient(patient);
        List<PatientClinicRating> alreadyRatedClinics = patientClinicRatingService.findAllByPatient(patient);

        //already rated doctors
        for(PatientDoctorRating rating : alreadyRatedDoctors){
            ratings.getDoctorRatings().add(new RatingDTO(rating.getDoctor().getName() + " " + rating.getDoctor().getSurname(),
                    rating.getRating(), Long.toString(rating.getDoctor().getId())));
        }

        //already rated clinics
        for(PatientClinicRating rating: alreadyRatedClinics){
            ratings.getClinicRatings().add(new RatingDTO(rating.getClinic().getName(), rating.getRating(), Long.toString(rating.getClinic().getId())));
        }

        //doctors available for rating
        for(Checkup checkup: patient.getCheckups()){
            if(checkup.isEnded() && !availableDoctorsID.contains(checkup.getDoctor().getId())){
                List<PatientDoctorRating> doctorRatings = patientDoctorRatingService.findAllByDoctor(checkup.getDoctor());

                if(doctorRatings.stream().filter(pdr -> pdr.getPatient().getId().equals(patient.getId())).collect(Collectors.toList()).isEmpty()){
                    ratings.getDoctorRatings().add(new RatingDTO(checkup.getDoctor().getName() + " " + checkup.getDoctor().getSurname(), 0, Long.toString(checkup.getDoctor().getId())));
                    availableDoctorsID.add(checkup.getDoctor().getId());
                }
            }
        }

        for(Checkup checkup: patient.getCheckups()){
            if(checkup.isEnded() && !availableClinicsID.contains(checkup.getClinic().getId())){
                List<PatientClinicRating> clinicRatings = patientClinicRatingService.findAllByClinic(checkup.getClinic());

                if(clinicRatings.stream().filter(pcr -> pcr.getPatient().getId().equals(patient.getId())).collect(Collectors.toList()).isEmpty()){
                    ratings.getClinicRatings().add(new RatingDTO(checkup.getClinic().getName(), 0, Long.toString(checkup.getClinic().getId())));
                    availableClinicsID.add(checkup.getClinic().getId());
                }
            }
        }

        return ratings;
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/rateDoctor")
    public ResponseEntity<?> rateDoctor(Principal principal, @RequestBody RatingDTO ratingDTO){
        Patient patient = (Patient) userDetailsService.loadUserByUsername(principal.getName());
        Doctor doctor = doctorService.findById(Long.parseLong(ratingDTO.getId()));

        PatientDoctorRating patientDoctorRating = new PatientDoctorRating(patient, doctor, ratingDTO.getRating());

        patientDoctorRatingService.save(patientDoctorRating);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", path = "/rateClinic")
    public ResponseEntity<?> rateClinic(Principal principal, @RequestBody RatingDTO ratingDTO){
        Patient patient = (Patient) userDetailsService.loadUserByUsername(principal.getName());
        Clinic clinic = clinicService.findById(Long.parseLong(ratingDTO.getId()));

        PatientClinicRating patientClinicRating = new PatientClinicRating(patient, clinic, ratingDTO.getRating());

        patientClinicRatingService.save(patientClinicRating);;

        return ResponseEntity.ok().build();
    }
}