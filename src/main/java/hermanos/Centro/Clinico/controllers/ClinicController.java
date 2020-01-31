package hermanos.Centro.Clinico.controllers;


import hermanos.Centro.Clinico.dto.*;
import hermanos.Centro.Clinico.model.*;
import hermanos.Centro.Clinico.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

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
    PredefinedCheckupServiceInterface checkupDateService;

    @Autowired
    CheckupTypeServiceInterface checkupTypeService;

    @Autowired
    CheckupServiceInterface checkupService;

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public Clinic clinicInfo(Principal p) {
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        Clinic clinic = clinicService.findById(id);

        return clinic;

    }

    //@PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public List<ClinicTableDTO> clinicInfo() {
        List<ClinicTableDTO> retVal = new ArrayList<>();
        List<Clinic> clinics = clinicService.findAll();

        for (Clinic clinic : clinics) {
            retVal.add(new ClinicTableDTO(clinic));
        }

        return retVal;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/addCheckupType")
    public ResponseEntity newCheckupType(Principal p, @RequestBody CheckupType ct) {
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        ct.setClinic(clinicService.findById(id));
        checkupTypeService.save(ct);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/removeCheckupDate")
    public ResponseEntity removeCheckupDate(@RequestBody PredefinedCheckup cd) {
        checkupDateService.deleteById(cd.getId());

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/removeCheckupType")
    public ResponseEntity removeCheckupType(@RequestBody CheckupType ct) {
        checkupTypeService.deleteById(ct.getId());

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/removeRoom")
    public ResponseEntity removeRoom(@RequestBody Room room) {
        roomService.deleteById(room.getId());

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/removeDoctor")
    public ResponseEntity removeDoctor(@RequestBody Doctor dr) {
        doctorService.deleteById(dr.getId());

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/addRoom")
    public ResponseEntity newRoom(Principal p, @RequestBody Room room) {
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        room.setClinic(clinicService.findById(id));
        roomService.save(room);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/getCheckupDates")
    public @ResponseBody
    List<CheckupDateDTO> getCheckupDates(Principal p) {
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        List<PredefinedCheckup> cdlist = clinicService.findById(id).getPredefinedCheckups();
        List<CheckupDateDTO> cddtolist = new ArrayList<>();
        for (PredefinedCheckup cd : cdlist) {
            CheckupDateDTO cddto = new CheckupDateDTO(cd.getId(), cd.getDate(), cd.getStartEnd().getStartTime(), cd.getStartEnd().getEndTime(), cd.getDoctor().getId().toString(), cd.getDoctor().getName());

            cddtolist.add(cddto);
        }
        return cddtolist;
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.GET, path = "/getAllCheckupTypes", consumes = "application/json")
    public @ResponseBody
    List<CheckupTypeDTO> getCheckupTypes() {
        List<CheckupType> types = checkupTypeService.findAll();
        List<CheckupTypeDTO> typeDTOS = new ArrayList<>();

        for (CheckupType checkupType : types) {
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
    public @ResponseBody
    List<CheckupTypeDTO> getCheckupTypes(Principal p) {
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        List<CheckupType> ctlist = clinicService.findById(id).getCheckupTypes();
        List<CheckupTypeDTO> ctdtolist = new ArrayList<>();
        for (CheckupType ct : ctlist) {
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
    public @ResponseBody
    List<RoomDTO> getRooms(Principal p) {
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        List<Room> rlist = clinicService.findById(id).getRooms();
        List<RoomDTO> rdtolist = new ArrayList<>();
        for (Room r : rlist) {
            RoomDTO rdto = new RoomDTO(r.getId(), r.getName());
            rdtolist.add(rdto);
        }
        return rdtolist;
    }


    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/getRoomSchedule/{roomid}")
    public @ResponseBody List<CheckupScheduleDTO> getRoomSchedule(Principal p,@PathVariable("roomid") String roomid){
        List<Checkup> checkups = roomService.findById(parseInt(roomid,10)).getCheckups();
        List<CheckupScheduleDTO> checkupsdto = new ArrayList<CheckupScheduleDTO>();

        int i=0;

        for(Checkup c : checkups){
            CheckupScheduleDTO csdto = new CheckupScheduleDTO(c.getStartEnd(), c.getDate(), i, "");
            checkupsdto.add(csdto);
            i++;
        }
        return checkupsdto;
    }




    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/getDoctors")
    public @ResponseBody
    List<DocNameSurnameDTO> getDoctors(Principal p) {
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        List<Doctor> doctors = clinicService.findById(id).getDoctors();
        List<DocNameSurnameDTO> docns = new ArrayList<>();
        for (Doctor dr : doctors) {
            docns.add(new DocNameSurnameDTO(dr));
        }

        return docns;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/addDoctor")
    public ResponseEntity newDoctor(@RequestBody RegisterDoctorDTO pr, Principal p) {
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
        doctor.setSpecialization(checkupTypeService.findById(pr.getSpecialization()));
        doctor.setShift(new StartEndTime(pr.getStartTime(),pr.getEndTime()));

        doctorService.save(doctor);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/getClinic")
    public @ResponseBody
    EditClinicDTO getClinic(Principal p) {
        Clinic clinic = clinicAdminService.findByEmail(p.getName()).getClinic();
        EditClinicDTO ec = new EditClinicDTO(clinic);

        return ec;
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.GET, path = "/getClinic/{id}")
    public @ResponseBody
    EditClinicDTO getClinic(@PathVariable String id) {
        Clinic clinic = clinicService.findById(Long.parseLong(id));
        EditClinicDTO ec = new EditClinicDTO(clinic);
        return ec;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/editClinic")
    public ResponseEntity editClinic(Principal p, @RequestBody EditClinicDTO ec) {
        Clinic clinic = clinicAdminService.findByEmail(p.getName()).getClinic();

        clinic.setName(ec.getName());
        clinic.setDescription(ec.getDescription());

        Address newadr = new Address();
        boolean na = false;

        if ((clinic.getAddress().getStreet() != ec.getStreet()) || (clinic.getAddress().getNumber() != ec.getNumber()) || (clinic.getAddress().getCity() != ec.getCity()) || (clinic.getAddress().getPostcode() != ec.getPostcode()) || (clinic.getAddress().getCountry() != ec.getCountry())) {
            newadr.setStreet(ec.getStreet());
            newadr.setNumber(ec.getNumber());
            newadr.setCity(ec.getCity());
            newadr.setPostcode(ec.getPostcode());
            newadr.setCountry(ec.getCountry());
            na = true;
        }

        if (na) {
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
    public @ResponseBody
    List<RoomDTO> searchRooms(Principal p) {
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        List<Room> rlist = clinicService.findById(id).getRooms();
        List<RoomDTO> rdtolist = new ArrayList<>();
        for (Room r : rlist) {
            RoomDTO rdto = new RoomDTO();
            rdto.setId(r.getId());
            rdto.setName(r.getName());
            rdtolist.add(rdto);
        }
        return rdtolist;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/searchDoctors")
    public @ResponseBody
    List<DocRatingDTO> searchDoctors(Principal p) {
        List<Doctor> drlist = clinicAdminService.findByEmail(p.getName()).getClinic().getDoctors();
        List<DocRatingDTO> drdtolist = new ArrayList<>();
        for (Doctor dr : drlist) {
            DocRatingDTO drdto = new DocRatingDTO(dr);
            drdtolist.add(drdto);
        }
        return drdtolist;
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.GET, path = "/searchDoctors/{clinicId}")
    public @ResponseBody
    List<DocRatingDTO> searchDoctors(@PathVariable String clinicId) {
        List<Doctor> drlist = clinicService.findById(Long.parseLong(clinicId)).getDoctors();
        List<DocRatingDTO> drdtolist = new ArrayList<>();
        for (Doctor dr : drlist) {
            DocRatingDTO drdto = new DocRatingDTO(dr);
            drdtolist.add(drdto);
        }
        return drdtolist;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/searchCheckupTypes")
    public @ResponseBody
    List<CheckupTypeDTO> searchCheckupTypes(Principal p) {
        List<CheckupType> ctlist = clinicAdminService.findByEmail(p.getName()).getClinic().getCheckupTypes();
        List<CheckupTypeDTO> ctdtolist = new ArrayList<>();
        for (CheckupType ct : ctlist) {
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
    public @ResponseBody
    List<CheckupDateDTO> searchCheckupDates(Principal p) {
        List<PredefinedCheckup> cdlist = clinicAdminService.findByEmail(p.getName()).getClinic().getPredefinedCheckups();
        List<CheckupDateDTO> cddtolist = new ArrayList<>();
        for (PredefinedCheckup cd : cdlist) {
            CheckupDateDTO cddto = new CheckupDateDTO(cd.getId(), cd.getDate(), cd.getStartEnd().getStartTime(), cd.getStartEnd().getEndTime(), cd.getDoctor().getName() + " " + cd.getDoctor().getSurname(), cd.getDoctor().getName());
            cddtolist.add(cddto);
        }
        return cddtolist;
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.POST, path = "/getAvailableClinics")
    public List<ClinicTableDTO> getAvailableClinicsForCheckup(@RequestBody ScheduleFilterDTO filterDTO) {
        List<Clinic> clinics = clinicService.findAll();
        List<ClinicTableDTO> availableClinics = new ArrayList<>();

        LocalTime startTime = LocalTime.parse(filterDTO.getCheckupTime());
        LocalDate date = LocalDate.parse(filterDTO.getCheckupDate());


        for (Clinic clinic : clinics) {
            boolean hasAvailableDoctors = false;

            for (Doctor doctor : clinic.getDoctors()) {
                boolean isAbsent = false;
                boolean hasCheckupAlready = false;

                //check if doctor is specialized for type of checkup
                if (!doctor.getSpecialization().getName().equalsIgnoreCase(filterDTO.getCheckupType()))
                    break;


                //check if given time is in range of doctors working hours
                if (startTime.compareTo(doctor.getShift().getStartTime()) < 0 ||
                        startTime.compareTo(doctor.getShift().getEndTime()) > 0)
                    break;

                //check if doctor is absent on the given date
                for (StartEndDate absence : doctor.getAbsences()) {
                    if (absence.getStartDate().compareTo(date) <= 0 && absence.getEndDate().compareTo(date) >= 0) {
                        isAbsent = true;
                        break;
                    }
                }

                if (isAbsent)
                    break;

                //check if doctor does not have checkup at given time and date
                for (Checkup checkup : doctor.getCheckups()) {
                    if (checkup.getDate().isEqual(date)) {
                        if (startTime.compareTo(checkup.getStartEnd().getStartTime()) >= 0 &&
                                startTime.compareTo(checkup.getStartEnd().getEndTime()) <= 0) {
                            hasCheckupAlready = true;
                            break;
                        }
                    }
                }

                if (hasCheckupAlready)
                    break;

                availableClinics.add(new ClinicTableDTO(clinic));
                hasAvailableDoctors = true;
            }

            if (hasAvailableDoctors)
                break;
        }

        return availableClinics;
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.POST, path = "/schedule")
    public ResponseEntity scheduleCheckup(@RequestBody ScheduleFilterDTO filterDTO) {

        CheckupType type = checkupTypeService.findByName(filterDTO.getCheckupType());
        Doctor doctor = doctorService.findById(Long.parseLong(filterDTO.getDoctorId()));
        Clinic clinic = clinicService.findById(doctor.getClinic().getId());

        LocalDate date = LocalDate.parse(filterDTO.getCheckupDate());
        LocalTime startTime = LocalTime.parse(filterDTO.getCheckupTime());
        LocalTime endTime = startTime.plusMinutes(type.getDuration());

        Checkup checkup = new Checkup(date, startTime, endTime, doctor, type, clinic);

        if (!checkupService.isValid(checkup))
            return ResponseEntity.badRequest().build();

        checkupService.save(checkup);

        return ResponseEntity.ok().build();
    }
  
    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/predefine")
    public ResponseEntity predefineCheckup(@RequestBody ScheduleFilterDTO filterDTO) {

        Doctor doctor = doctorService.findById(Long.parseLong(filterDTO.getDoctorId()));
        CheckupType type = doctor.getSpecialization();
        Clinic clinic = clinicService.findById(doctor.getClinic().getId());
        Room room = roomService.findById(Long.parseLong(filterDTO.getRoomId()));

        LocalDate date = LocalDate.parse(filterDTO.getCheckupDate());
        LocalTime startTime = LocalTime.parse(filterDTO.getCheckupTime());
        LocalTime endTime = startTime.plusMinutes(type.getDuration());

        PredefinedCheckup checkup = new PredefinedCheckup(date, startTime, endTime, doctor, type, clinic, room);

        if (!checkupDateService.isValid(checkup))
            return ResponseEntity.badRequest().build();

        checkupDateService.save(checkup);

        return ResponseEntity.ok().build();
    }


    @PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.GET, path = "/doctorWorkingSchedule/{id}")
    public ResponseEntity<DoctorWorkingScheduleDTO> getDoctorSchedule(@PathVariable String id) {

        long doctorId = Long.parseLong(id);
        Doctor doctor = doctorService.findById(doctorId);

        DoctorWorkingScheduleDTO dto = new DoctorWorkingScheduleDTO(doctor);

        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/getPendingCheckups")
    public @ResponseBody
    List<CheckupPendingDTO> getPendingCheckups(Principal p){
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        Clinic clinic = clinicService.findById(id);
        List<Checkup> checkups = clinic.getCheckups();

        List<CheckupPendingDTO> pendinglist = new ArrayList<CheckupPendingDTO>();
        for(Checkup c : checkups){
            if(!c.isApproved() && !c.isStarted() && !c.isEnded()){
                pendinglist.add(new CheckupPendingDTO(c.getId(),c.getDate(),c.getStartEnd().getStartTime(),
                        c.getStartEnd().getEndTime(), c.getDoctor().getId(),
                        c.getDoctor().getName() + " " + c.getDoctor().getSurname()));
            }
        }

        return pendinglist;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/getAvailableRooms/{checkid}")
    public @ResponseBody
    List<RoomDTO> getAvailableRooms(Principal p, @PathVariable String checkid){
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        Clinic clinic = clinicService.findById(id);
        List<Room> rooms = clinic.getRooms();
        Checkup checkup = checkupService.findById(Long.parseLong(checkid));
        LocalDate date = checkup.getDate();
        LocalTime st = checkup.getStartEnd().getStartTime();
        LocalTime et = checkup.getStartEnd().getEndTime();
        Doctor doc = checkup.getDoctor();

        List<RoomDTO> availablelist = new ArrayList<RoomDTO>();
        for(Room r : rooms){
            boolean flag = false;
            for(Checkup cr: r.getCheckups()){
                if(!cr.isEnded() && cr.isApproved()) {
                    if (cr.getDate().isEqual(date) &&
                            !(
                                    (cr.getStartEnd().getStartTime().isAfter(et)) ||
                                            (cr.getStartEnd().getEndTime().isBefore(st))
                            )
                    ) {
                        flag = true;
                        break;
                    }
                }
            }
            if(!flag){
                availablelist.add(new RoomDTO(r.getId(),r.getName()));
            }
        }

        return availablelist;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/approveCheckupRequest")
    public ResponseEntity approveCheckupRequest(@RequestBody CheckupPendingDTO cp) {

        Checkup c = checkupService.findById(cp.getId());
//        if(!c.getDoctor().getId().equals(cp.getDoctor_id()))
//            c.setDoctor(doctorService.findById(cp.getDoctor_id()));
//
//        if(!(c.getStartEnd().getStartTime().equals(cp.getStartTime()) && c.getStartEnd().getEndTime().equals(cp.getEndTime())))
//            c.setStartEnd(new StartEndTime(cp.getStartTime(),cp.getEndTime()));
//
//        if(!c.getDate().equals(cp.getDate()))
//            c.setDate(cp.getDate());

        c.setRoom(roomService.findById(cp.getRoom_id()));
        c.setApproved(true);

        checkupService.save(c);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/denyCheckupRequest")
    public ResponseEntity denyCheckupRequest(@RequestBody CheckupPendingDTO cp) {

        checkupService.deleteById(cp.getId());

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/getDocForCheckup/{checkid}")
    public @ResponseBody
    List<DocNameSurnameDTO> getDocForCheckup(Principal p, @PathVariable String checkid){
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        Clinic clinic = clinicService.findById(id);
        CheckupType ct = checkupService.findById(Long.parseLong(checkid)).getType();
        List<Doctor> docs = clinic.getDoctors();
        List<DocNameSurnameDTO> doclist = new ArrayList<DocNameSurnameDTO>();

        for(Doctor d : docs){
            if(d.getSpecialization().getId() == ct.getId()){
                doclist.add(new DocNameSurnameDTO(d));
            }
        }
        return doclist;
    }

}
