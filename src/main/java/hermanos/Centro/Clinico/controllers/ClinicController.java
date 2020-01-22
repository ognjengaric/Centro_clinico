package hermanos.Centro.Clinico.controllers;


import hermanos.Centro.Clinico.dto.*;
import hermanos.Centro.Clinico.model.*;
import hermanos.Centro.Clinico.service.interfaces.*;
import org.hibernate.annotations.Check;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
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
    PersonServiceInterface personService;

    @Autowired
    CheckupDateServiceInterface checkupDateService;

    @Autowired
    CheckupTypeServiceInterface checkupTypeService;

    @Autowired
    CheckupServiceInterface checkupService;

    @RequestMapping(method = RequestMethod.GET,path = "/")
    public Clinic clinicInfo(Principal p){
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        Clinic clinic = clinicService.findById(id);

        return clinic;

    }

    //@PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.GET,path = "/all")
    public List<ClinicTableDTO> clinicInfo(){
        List<ClinicTableDTO> retVal = new ArrayList<>();
        List<Clinic> clinics  = clinicService.findAll();

        for(Clinic clinic : clinics){
            retVal.add(new ClinicTableDTO(clinic));
        }

        return retVal;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/addCheckupDate")
    public ResponseEntity newCheckupDate(Principal p, @RequestBody CheckupDate cd){
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        cd.setClinic(clinicService.findById(id));
        checkupDateService.save(cd);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/addCheckupType")
    public ResponseEntity newCheckupType(Principal p, @RequestBody CheckupType ct){
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        ct.setClinic(clinicService.findById(id));
        checkupTypeService.save(ct);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/removeCheckupDate")
    public ResponseEntity removeCheckupDate(@RequestBody CheckupDate cd){
        checkupDateService.deleteById(cd.getId());

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/removeCheckupType")
    public ResponseEntity removeCheckupType(@RequestBody CheckupType ct){
        checkupTypeService.deleteById(ct.getId());

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/removeRoom")
    public ResponseEntity removeRoom(@RequestBody Room room){
        roomService.deleteById(room.getId());

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/removeDoctor")
    public ResponseEntity removeDoctor(@RequestBody Doctor dr){
        doctorService.deleteById(dr.getId());

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/addRoom")
    public ResponseEntity newRoom(Principal p, @RequestBody Room room){
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        room.setClinic(clinicService.findById(id));
        roomService.save(room);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/getCheckupDates")
    public @ResponseBody List<CheckupDateDTO> getCheckupDates(Principal p){
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
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

    @PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.GET, path = "/getAllCheckupTypes", consumes = "application/json")
    public @ResponseBody List<CheckupTypeDTO> getCheckupTypes(){
        List<CheckupType> types = checkupTypeService.findAll();
        List<CheckupTypeDTO> typeDTOS = new ArrayList<>();

        for(CheckupType checkupType : types){
            CheckupTypeDTO dto = new CheckupTypeDTO();
            dto.setId(checkupType.getId());
            dto.setName(checkupType.getName());
            dto.setDuration(String.valueOf(checkupType.getDuration()));
            dto.setPrice(String.valueOf(checkupType.getPrice()));
            typeDTOS.add(dto);
        }

        return typeDTOS;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/getCheckupTypes")
    public @ResponseBody List<CheckupTypeDTO> getCheckupTypes(Principal p){
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        List<CheckupType> ctlist = clinicService.findById(id).getCheckupTypes();
        List<CheckupTypeDTO> ctdtolist = new ArrayList<>();
        for(CheckupType ct : ctlist){
            CheckupTypeDTO ctdto = new CheckupTypeDTO();
            ctdto.setId(ct.getId());
            ctdto.setName(ct.getName());
            ctdto.setDuration(String.valueOf(ct.getDuration()));
            ctdto.setPrice(String.valueOf(ct.getPrice()));
            ctdtolist.add(ctdto);
        }
        return ctdtolist;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/getRooms")
    public @ResponseBody List<RoomDTO> getRooms(Principal p){
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        List<Room> rlist = clinicService.findById(id).getRooms();
        List<RoomDTO> rdtolist = new ArrayList<>();
        for(Room r : rlist){
            RoomDTO rdto = new RoomDTO(r.getId(),r.getName());
            rdtolist.add(rdto);
        }
        return rdtolist;
    }

    /*
    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/getRoomSchedule")
    public @ResponseBody List<CheckupScheduleDTO> getRoomSchedule(Principal p){
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        List<Checkup> checkups= clinicService.findById(id).getRooms().get(0).getCheckups();
        List<CheckupScheduleDTO> checkupsdto = new ArrayList<CheckupScheduleDTO>();
        for(Checkup c : checkups){
            CheckupScheduleDTO csdto = new CheckupScheduleDTO(c.getStartTime(),c.getEndTime());
            checkupsdto.add(csdto);
        }
        return checkupsdto;
    }

    */


    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/getDoctors")
    public @ResponseBody List<DocNameSurnameDTO> getDoctors(Principal p){
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
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
    @RequestMapping(method = RequestMethod.POST, path = "/addDoctor")
    public ResponseEntity newDoctor(@RequestBody PatientRequest pr, Principal p){
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
        doctor.setClinic((clinicAdminService.findByEmail(p.getName()).getClinic()));
        doctor.setPassword(pr.getPassword());
        doctor.setName(pr.getName());
        doctor.setSurname(pr.getSurname());
        doctor.setPhoneNumber(pr.getPhoneNumber());

        doctorService.save(doctor);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/getClinic")
    public @ResponseBody EditClinicDTO getClinic(Principal p){
        Clinic clinic = clinicAdminService.findByEmail(p.getName()).getClinic();
        EditClinicDTO ec = new EditClinicDTO(clinic);

        return ec;
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.GET, path = "/getClinic/{id}")
    public @ResponseBody EditClinicDTO getClinic(@PathVariable String id){
        Clinic clinic = clinicService.findById(Long.parseLong(id));
        EditClinicDTO ec = new EditClinicDTO(clinic);
        return ec;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/editClinic")
    public ResponseEntity editClinic(Principal p, @RequestBody EditClinicDTO ec){
        Clinic clinic = clinicAdminService.findByEmail(p.getName()).getClinic();

        clinic.setName(ec.getName());
        clinic.setDescription(ec.getDescription());

        Address newadr = new Address();
        boolean na = false;

        if((clinic.getAddress().getStreet() != ec.getStreet()) || (clinic.getAddress().getNumber() != ec.getNumber()) || (clinic.getAddress().getCity() != ec.getCity()) || (clinic.getAddress().getPostcode() != ec.getPostcode()) || (clinic.getAddress().getCountry() != ec.getCountry())){
            newadr.setStreet(ec.getStreet());
            newadr.setNumber(ec.getNumber());
            newadr.setCity(ec.getCity());
            newadr.setPostcode(ec.getPostcode());
            newadr.setCountry(ec.getCountry());
            na = true;
        }

        if(na){
            clinic.setAddress(newadr);
        }

        Clinic c = clinicService.save(clinic);

        return ResponseEntity.ok().build();
    }

    /*@PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/searchRooms/{str}")
    public @ResponseBody List<RoomDTO> searchRooms(Principal p,@PathVariable("str") String str){
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        List<Room> rlist = clinicService.findById(id).getRooms();
        List<RoomDTO> rdtolist = new ArrayList<>();
        for(Room r : rlist){
            RoomDTO rdto = new RoomDTO();
            rdto.setId(r.getId());
            rdto.setName(r.getName());
            if(r.getName().contains(str)) {
                rdtolist.add(rdto);
            }
        }
        return rdtolist;
    }*/

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/searchRooms")
    public @ResponseBody List<RoomDTO> searchRooms(Principal p){
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        List<Room> rlist = clinicService.findById(id).getRooms();
        List<RoomDTO> rdtolist = new ArrayList<>();
        for(Room r : rlist) {
            RoomDTO rdto = new RoomDTO();
            rdto.setId(r.getId());
            rdto.setName(r.getName());
            rdtolist.add(rdto);
        }
        return rdtolist;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/searchDoctors")
    public @ResponseBody List<DocRatingDTO> searchDoctors(Principal p){
        List<Doctor> drlist= clinicAdminService.findByEmail(p.getName()).getClinic().getDoctors();
        List<DocRatingDTO> drdtolist = new ArrayList<>();
        for(Doctor dr : drlist){
            DocRatingDTO drdto = new DocRatingDTO(dr);
            drdtolist.add(drdto);
        }
        return drdtolist;
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.GET, path = "/searchDoctors/{clinicId}")
    public @ResponseBody List<DocRatingDTO> searchDoctors(@PathVariable String clinicId){
        List<Doctor> drlist= clinicService.findById(Long.parseLong(clinicId)).getDoctors();
        List<DocRatingDTO> drdtolist = new ArrayList<>();
        for(Doctor dr : drlist){
            DocRatingDTO drdto = new DocRatingDTO(dr);
            drdtolist.add(drdto);
        }
        return drdtolist;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/searchCheckupTypes")
    public @ResponseBody List<CheckupTypeDTO> searchCheckupTypes(Principal p){
        List<CheckupType> ctlist = clinicAdminService.findByEmail(p.getName()).getClinic().getCheckupTypes();
        List<CheckupTypeDTO> ctdtolist = new ArrayList<>();
        for(CheckupType ct : ctlist){
            CheckupTypeDTO ctdto = new CheckupTypeDTO();
            ctdto.setId(ct.getId());
            ctdto.setName(ct.getName());
            ctdto.setDuration(String.valueOf(ct.getDuration()));
            ctdto.setPrice(String.valueOf(ct.getPrice()));
            ctdtolist.add(ctdto);
        }
        return ctdtolist;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/searchCheckupDates")
    public @ResponseBody List<CheckupDateDTO> searchCheckupDates(Principal p){
        List<CheckupDate> cdlist = clinicAdminService.findByEmail(p.getName()).getClinic().getCheckupDates();
        List<CheckupDateDTO> cddtolist = new ArrayList<>();
        for(CheckupDate cd : cdlist){
            CheckupDateDTO cddto = new CheckupDateDTO();
            cddto.setId(cd.getId());
            cddto.setDate(cd.getDate());
            cddtolist.add(cddto);
        }
        return cddtolist;
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.POST, path = "/getAvailableClinics")
    public List<ClinicTableDTO> getAvailableClinicsForCheckup(@RequestBody ScheduleFilterDTO filterDTO){
        List<Clinic> clinics = clinicService.findAll();
        List<ClinicTableDTO> availableClinics = new ArrayList<>();

        LocalTime startTime = LocalTime.parse(filterDTO.getCheckupTime());
        LocalDate date = LocalDate.parse(filterDTO.getCheckupDate());



        for (Clinic clinic: clinics) {
            boolean hasAvailableDoctors = false;

            for (Doctor doctor : clinic.getDoctors()){
                boolean isAbsent = false;
                boolean hasCheckupAlready = false;

                //check if doctor is specialized for type of checkup
                if(!doctor.getSpecialization().getName().equalsIgnoreCase(filterDTO.getCheckupType()))
                    break;


                //check if given time is in range of doctors working hours
                if(startTime.compareTo(doctor.getShift().getStartTime()) < 0 ||
                        startTime.compareTo(doctor.getShift().getEndTime()) > 0)
                    break;

                //check if doctor is absent on the given date
                for(StartEndDate absence: doctor.getAbsences()){
                    if(absence.getStartDate().compareTo(date) <= 0 && absence.getEndDate().compareTo(date) >= 0){
                        isAbsent = true;
                        break;
                    }
                }

                if(isAbsent)
                    break;

                //check if doctor does not have checkup at given time and date
                for(Checkup checkup: doctor.getCheckups()){
                    if(checkup.getDate().isEqual(date)){
                        if(startTime.compareTo(checkup.getStartEnd().getStartTime()) >= 0 &&
                                startTime.compareTo(checkup.getStartEnd().getEndTime()) <= 0){
                            hasCheckupAlready = true;
                            break;
                        }
                    }
                }

                if(hasCheckupAlready)
                    break;

                availableClinics.add(new ClinicTableDTO(clinic));
                hasAvailableDoctors = true;
            }

            if(hasAvailableDoctors)
                break;
        }

        return availableClinics;
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.POST, path = "/schedule")
    public ResponseEntity scheduleCheckup(@RequestBody ScheduleFilterDTO filterDTO){
        
        CheckupType type = checkupTypeService.findByName(filterDTO.getCheckupType());
        Doctor doctor = doctorService.findById(Long.parseLong(filterDTO.getDoctorId()));
        Clinic clinic = clinicService.findById(doctor.getClinic().getId());

        LocalDate date = LocalDate.parse(filterDTO.getCheckupDate());
        LocalTime startTime = LocalTime.parse(filterDTO.getCheckupTime());
        LocalTime endTime = startTime.plusMinutes(type.getDuration());

        Checkup checkup = new Checkup(date, startTime, endTime, doctor, type, clinic);

        checkupService.save(checkup);

        return ResponseEntity.ok().build();
    }
}
