package hermanos.Centro.Clinico.controllers;


import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.model.Doctor;
import hermanos.Centro.Clinico.service.interfaces.ClinicServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/viewBusinessReport")
public class BusinessReportController {
    @Autowired
    ClinicServiceInterface clinicService;

    public class DocRating{
        private long id;
        private String name;
        private String surname;
        private String avgrating;
    }

    @RequestMapping(method = RequestMethod.GET,path = "/{id}")
    public List<DocRating> businessReport(@PathVariable("id") long id){
        Clinic clinic = clinicService.findById(id);
        List<Doctor> drlist = clinic.getDoctors();
        List<DocRating> docratlist = new ArrayList<>();
        for (Doctor dr : drlist){
            DocRating docrat = new DocRating();
            docrat.id = dr.getId();
            docrat.name = dr.getName();
            docrat.surname = dr.getSurname();
            docrat.avgrating = dr.getAvgrating();
            docratlist.add(docrat);
        }

        return docratlist;
    }
}
