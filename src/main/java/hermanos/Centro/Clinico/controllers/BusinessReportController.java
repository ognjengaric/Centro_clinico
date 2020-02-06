package hermanos.Centro.Clinico.controllers;


import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.model.Doctor;
import hermanos.Centro.Clinico.service.ClinicAdministratorService;
import hermanos.Centro.Clinico.service.interfaces.ClinicServiceInterface;
import hermanos.Centro.Clinico.service.interfaces.DoctorServiceInterface;
import hermanos.Centro.Clinico.dto.DocRatingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/viewBusinessReport")
public class BusinessReportController {
    @Autowired
    ClinicServiceInterface clinicService;

    @Autowired
    ClinicAdministratorService clinicAdminService;

    @Autowired
    DoctorServiceInterface doctorService;

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET,path = "")
    public List<DocRatingDTO> businessReport(Principal p){
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        Clinic clinic = clinicService.findById(id);
        List<Doctor> drlist = new ArrayList<>();
        drlist = clinic.getDoctors();
        List<DocRatingDTO> docratlist = new ArrayList<>();

        for (Doctor dr : drlist){
            DocRatingDTO docrat = new DocRatingDTO();
            docrat.setId(dr.getId());
            docrat.setName(dr.getName());
            docrat.setSurname(dr.getSurname());
            docrat.setAvgrating(Float.toString(dr.calculateAverageRating()));
            docratlist.add(docrat);
        }

        return docratlist;
    }
}
