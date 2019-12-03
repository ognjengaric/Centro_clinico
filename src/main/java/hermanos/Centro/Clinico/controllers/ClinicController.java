package hermanos.Centro.Clinico.controllers;


import hermanos.Centro.Clinico.dto.*;
import hermanos.Centro.Clinico.model.*;
import hermanos.Centro.Clinico.service.interfaces.*;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/clinic")
public class ClinicController {
    @Autowired
    ClinicAdministratorServiceInterface clinicAdminService;

    @Autowired
    ClinicServiceInterface clinicService;

    @Autowired
    RoomServiceInterface roomService;

    @Autowired
    DoctorServiceInterface doctorService;

    @Autowired
    CheckupDateServiceInterface checkupDateService;

    @Autowired
    CheckupTypeServiceInterface checkupTypeService;

    @RequestMapping(method = RequestMethod.GET,path = "/{id}")
    public Clinic clinicInfo(@PathVariable("id") long id){
        Clinic clinic = clinicService.findById(id);

        return clinic;

    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/addCheckupDate/{id}")
    public ResponseEntity newCheckupDate(@PathVariable("id") long id, @RequestBody CheckupDate cd){
        cd.setClinic(clinicService.findById(id));
        checkupDateService.save(cd);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/addCheckupType/{id}")
    public ResponseEntity newCheckupType(@PathVariable("id") long id, @RequestBody CheckupType ct){
        ct.setClinic(clinicService.findById(id));
        checkupTypeService.save(ct);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/addRoom/{id}")
    public ResponseEntity newRoom(@PathVariable("id") long id, @RequestBody Room room){
        room.setClinic(clinicService.findById(id));
        roomService.save(room);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/getCheckupDates/{id}")
    public @ResponseBody List<CheckupDateDTO> getCheckupDates(@PathVariable("id") long id){
        List<CheckupDate> cdlist = clinicService.findById(id).getCheckupDates();
        List<CheckupDateDTO> cddtolist = new ArrayList<>();
        for(CheckupDate cd : cdlist){
            CheckupDateDTO cddto = new CheckupDateDTO();
            cddto.setId(cd.getId());
            cddto.setDate(cd.getDate());
            cddtolist.add(cddto);
        }
        return cddtolist;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/getCheckupTypes/{id}")
    public @ResponseBody List<CheckupTypeDTO> getCheckupTypes(@PathVariable("id") long id){
        List<CheckupType> ctlist = clinicService.findById(id).getCheckupTypes();
        List<CheckupTypeDTO> ctdtolist = new ArrayList<>();
        for(CheckupType ct : ctlist){
            CheckupTypeDTO ctdto = new CheckupTypeDTO();
            ctdto.setId(ct.getId());
            ctdto.setName(ct.getName());
            ctdtolist.add(ctdto);
        }
        return ctdtolist;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/getRooms/{id}")
    public @ResponseBody List<RoomDTO> getRooms(@PathVariable("id") long id){
        List<Room> rlist = clinicService.findById(id).getRooms();
        List<RoomDTO> rdtolist = new ArrayList<>();
        for(Room r : rlist){
            RoomDTO rdto = new RoomDTO();
            rdto.setId(r.getId());
            rdto.setName(r.getName());
            rdtolist.add(rdto);
        }
        return rdtolist;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/getDoctors/{id}")
    public @ResponseBody List<DocNameSurnameDTO> getDoctors(@PathVariable("id") long id){
        List<Doctor> doctors = clinicService.findById(id).getDoctors();
        List<DocNameSurnameDTO> docns = new ArrayList<>();
        for(Doctor dr: doctors){
            DocNameSurnameDTO d = new DocNameSurnameDTO();
            d.setId(dr.getId());
            d.setName(dr.getName());
            d.setSurname(dr.getSurname());
            docns.add(d);
        }

        return docns;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/addDoctor/{id}")
    public ResponseEntity newDoctor(@PathVariable("id") long id, @RequestBody PatientRequest pr){
        Address adr = new Address();
        adr.setCity(pr.getCity());
        adr.setCountry(pr.getCountry());
        adr.setNumber(pr.getPhoneNumber());
        adr.setPostcode(pr.getPostcode());
        adr.setStreet(pr.getStreet());


        Doctor doctor = new Doctor();
        doctor.setAddress(adr);
        doctor.setEmail(pr.getEmail());
        doctor.setAvgrating("0");
        doctor.setClinic(clinicService.findById(id));
        doctor.setPassword(pr.getPassword());
        doctor.setName(pr.getName());
        doctor.setSurname(pr.getSurname());
        doctor.setPhoneNumber(pr.getPhoneNumber());


        doctor.setClinic(clinicService.findById(id));
        doctor.setAvgrating("0");


        doctorService.save(doctor);

        return ResponseEntity.ok().build();
    }

}
