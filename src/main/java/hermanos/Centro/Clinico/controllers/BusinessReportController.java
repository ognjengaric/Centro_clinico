package hermanos.Centro.Clinico.controllers;


import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.model.Doctor;
import hermanos.Centro.Clinico.service.interfaces.ClinicServiceInterface;
import hermanos.Centro.Clinico.service.interfaces.DoctorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Component
    public class DocRating implements Serializable {
        private long id;
        private String name;
        private String surname;
        private String avgrating;

        public DocRating() {
            super();
        }
    }

    @RequestMapping(method = RequestMethod.GET,path = "/{id}")
    public List<DocRating> businessReport(@PathVariable("id") long id){
        Clinic clinic = clinicService.findById(id);
        Doctor doca = doctorService.findById(id);
        List<Doctor> drlist = new ArrayList<>();
        drlist = clinic.getDoctors();
        drlist.add(doca);
        List<DocRating> docratlist = new ArrayList<>();
        DocRating docrat = new DocRating();
        for (Doctor dr : drlist){
            docrat.id = dr.getId();
            docrat.name = dr.getName();
            docrat.surname = dr.getSurname();
            docrat.avgrating = dr.getAvgrating();
            docratlist.add(docrat);
        }

        return docratlist;
    }
}
