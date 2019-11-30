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

        public DocRating(long id, String name, String surname, String avgrating) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.avgrating = avgrating;
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
        for (Doctor dr : drlist){
            DocRating docrat = new DocRating(dr.getId(), dr.getName(), dr.getSurname(), dr.getAvgrating());
            docratlist.add(docrat);
        }

        return docratlist;
    }
}
