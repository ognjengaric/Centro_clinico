package hermanos.Centro.Clinico.controllers;


import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.model.Doctor;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/viewBusinessReport")
public class BusinessReportController {
    @Autowired
    ClinicServiceInterface clinicService;
    @Autowired
    DoctorServiceInterface doctorService;

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET,path = "/{id}")
    public List<DocRatingDTO> businessReport(@PathVariable("id") long id){
        Clinic clinic = clinicService.findById(id);
        List<Doctor> drlist = new ArrayList<>();
        drlist = clinic.getDoctors();
        List<DocRatingDTO> docratlist = new ArrayList<>();
        DocRatingDTO docrat = new DocRatingDTO();
        for (Doctor dr : drlist){
            docrat.setId(dr.getId());
            docrat.setName(dr.getName());
            docrat.setSurname(dr.getSurname());
            docrat.setAvgrating(dr.getAvgrating());
            docratlist.add(docrat);
        }

        return docratlist;
    }
}
