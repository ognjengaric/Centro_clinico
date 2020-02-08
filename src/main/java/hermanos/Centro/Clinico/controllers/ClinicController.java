package hermanos.Centro.Clinico.controllers;


import hermanos.Centro.Clinico.dto.*;
import hermanos.Centro.Clinico.exception.ResourceConflictException;
import hermanos.Centro.Clinico.model.*;
import hermanos.Centro.Clinico.service.AuthorityService;
import hermanos.Centro.Clinico.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping(value = "/clinic")
public class ClinicController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    AbsenceRequestServiceInterface absenceRequestService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    ClinicAdministratorServiceInterface clinicAdminService;

    @Autowired
    ClinicServiceInterface clinicService;

    @Autowired
    RoomServiceInterface roomService;

    @Autowired
    DoctorServiceInterface doctorService;

    @Autowired
    NurseServiceInterface nurseService;

    @Autowired
    PersonServiceInterface personService;

    @Autowired
    PredefinedCheckupServiceInterface predefinedCheckupService;

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
        predefinedCheckupService.deleteById(cd.getId());

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/removeCheckupType")
    public ResponseEntity removeCheckupType(@RequestBody CheckupType ct, Principal p) {
        Clinic clinic = clinicAdminService.findByEmail(p.getName()).getClinic();
        boolean canDelete = true;
        for(Doctor dr: clinic.getDoctors()){
            if(dr.getSpecialization().getId() == ct.getId())
                canDelete=false;
        }
        if(canDelete) {
            checkupTypeService.deleteById(ct.getId());
        }else{
            throw new ResourceConflictException("Checkup type cant be deleted if there are doctors who specialize in it!");
        }

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/removeRoom")
    public ResponseEntity removeRoom(@RequestBody Room room, Principal p) {
        Clinic clinic = clinicAdminService.findByEmail(p.getName()).getClinic();
        boolean canDelete = true;
        for(Checkup c: clinic.getCheckups()){
            if(c.isApproved()) {
                if (c.getRoom().getId() == room.getId()){
                    canDelete = false;
                }
            }
        }
        if(canDelete) {
            roomService.deleteById(room.getId());
        }else{
            throw new ResourceConflictException("Room cant be deleted if there are checkups scheduled in it!");
        }

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/removeDoctor")
    public ResponseEntity removeDoctor(@RequestBody Doctor dr) {
        Doctor d = doctorService.findById(dr.getId());
        if(d.getCheckups().isEmpty()) {
            d.setAuthorities(null);
            d.setSpecialization(null);
            doctorService.save(d);
            doctorService.deleteById(dr.getId());
        }else{
            throw new ResourceConflictException("Doctor cant be deleted because he has scheduled checkups!");
        }

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
        doctor.setClinic((clinicAdminService.findByEmail(p.getName()).getClinic()));
        doctor.setPassword(passwordEncoder.encode(pr.getPassword()));
        doctor.setName(pr.getName());
        doctor.setSurname(pr.getSurname());
        doctor.setPhoneNumber(pr.getPhoneNumber());
        CheckupType ct = checkupTypeService.findById(pr.getSpecialization());
        doctor.setSpecialization(ct);
        doctor.setShift(new StartEndTime(pr.getStartTime(),pr.getEndTime()));
        doctor.setMustChangePass(true);

        List<Authority> authorities = authorityService.findByName("DOCTOR");
        doctor.setAuthorities(authorities);


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
    public ResponseEntity scheduleCheckup(@RequestBody ScheduleFilterDTO filterDTO, Principal principal) {

        Patient patient = (Patient) personService.findByEmail(principal.getName());

       Checkup checkup = checkupService.makeNewCheckup(filterDTO, patient, false);

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

        if (!predefinedCheckupService.isValid(checkup))
            return ResponseEntity.badRequest().build();

        predefinedCheckupService.save(checkup);

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
            if(!c.isApproved() && !c.isStarted() && !c.isEnded() && !c.isPendingPatient()){
                pendinglist.add(new CheckupPendingDTO(c.getId(),c.getDate(),c.getStartEnd().getStartTime(),
                        c.getStartEnd().getEndTime(), c.getDoctor().getId(),
                        c.getDoctor().getName() + " " + c.getDoctor().getSurname(), c.isOperation()));
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

        c.setRoom(roomService.findById(cp.getRoom_id()));

        if(c.isUpdated()){
            try {
                clinicService.mailAppointmentUpdated(c);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            c.setPendingPatient(true);
        } else {
            try {
                clinicService.mailAppointment(c, true);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            c.setApproved(true);
        }

        checkupService.save(c);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/updateCheckupRequest/{datestr}")
    public ResponseEntity updateCheckupRequest(@RequestBody CheckupPendingDTO cp, @PathVariable String datestr) {

        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        String[] datesplit = datestr.split(" ");
        String date1 = datesplit[2]+"-"+datesplit[1]+"-"+datesplit[3];
        LocalDate cdate= LocalDate.parse(date1,formatterDate);

        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String time1 = datesplit[4];
        LocalTime cstart = LocalTime.parse(time1, formatterTime);
        LocalTime cend = cstart.plusMinutes(cp.getDuration());
        Checkup c = checkupService.findById(cp.getId());

        c.setDoctor(doctorService.findById(cp.getDoctor_id()));
        c.setStartEnd(new StartEndTime(cstart, cend));
        c.setDate(cdate);
        c.setUpdated(true);
        checkupService.save(c);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/denyCheckupRequest")
    public ResponseEntity denyCheckupRequest(@RequestBody CheckupPendingDTO cp) {

        try {
            clinicService.mailAppointment(checkupService.findById(cp.getId()), false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        checkupService.deleteById(cp.getId());

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/getDocForCheckup/{checkid}")
    public @ResponseBody
    List<DoctorWorkingScheduleDTO> getDocForCheckup(Principal p, @PathVariable String checkid){
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        Clinic clinic = clinicService.findById(id);
        CheckupType ct = checkupService.findById(Long.parseLong(checkid)).getType();

        List<Doctor> docs = clinic.getDoctors();
        List<DoctorWorkingScheduleDTO> doclist = new ArrayList<DoctorWorkingScheduleDTO>();

        for(Doctor d : docs){
            if(d.getSpecialization().getId() == ct.getId()){
                DoctorWorkingScheduleDTO dws = new DoctorWorkingScheduleDTO(d);
                dws.setId(d.getId());
                dws.setIme(d.getName());
                dws.setPrezime(d.getSurname());
                doclist.add(dws);
            }
        }
        return doclist;
    }

  
    @PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.GET, path = "/getPreviousCheckups")
    public List<FullCheckupViewDTO> getPreviousCheckups(Principal principal){
        Patient patient = (Patient) personService.findByEmail(principal.getName());
        List<FullCheckupViewDTO> checkups = new ArrayList<>();

        for(Checkup checkup : patient.getCheckups()){
            if(checkup.isEnded()) {
                checkups.add(new FullCheckupViewDTO(checkup));
            }
        }

        return checkups;
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.GET, path = "/getPredefinedCheckups/{id}")
    public List<FullCheckupViewDTO> getPredefinedCheckupsForClinic(@PathVariable String id){
        Clinic clinic = clinicService.findById(Long.parseLong(id));

        clinicService.deleteExpiredPredefinedCheckups(clinic);

        List<FullCheckupViewDTO> predefinedCheckups = new ArrayList<>();

        for(PredefinedCheckup checkup : clinic.getPredefinedCheckups()){
            predefinedCheckups.add(new FullCheckupViewDTO(checkup));
        }

        return predefinedCheckups;
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @RequestMapping(method = RequestMethod.POST, path = "/schedulePredefined")
    public ResponseEntity schedulePredefinedCheckup(@RequestBody ScheduleFilterDTO filterDTO, Principal principal) {

        Patient patient = (Patient) personService.findByEmail(principal.getName());

        Checkup checkup = checkupService.makeNewCheckup(filterDTO, patient, true);

        if (!checkupService.isValid(checkup))
            return ResponseEntity.badRequest().build();

        checkupService.save(checkup);

        return ResponseEntity.ok().build();
    }


    @PreAuthorize("hasAuthority('DOCTOR')")
    @RequestMapping(method = RequestMethod.GET, path = "/getAllPatients")
    public List<PatientDTO> getAllPatients(Principal p){
        Clinic clinic = doctorService.findByEmail(p.getName()).getClinic();
        List<PatientDTO> patients = new ArrayList<PatientDTO>();
        for(Checkup c : clinic.getCheckups()){
            boolean isUnique = true;
            PatientDTO pat = new PatientDTO(c.getPatient());
            for(PatientDTO pp : patients){
                if(pp.getEmail().equals(pat.getEmail())){
                    isUnique=false;
                    break;
                }
            }
            if(isUnique)
                patients.add(pat);
        }

        return patients;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/viewBusinessReport")
    public @ResponseBody BusinessReportDTO getBusinessReport(Principal p){
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        BusinessReportDTO br = new BusinessReportDTO(clinicService.findById(id));
        return br;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/viewBusinessReport/{start}/{end}")
    public @ResponseBody BusinessReportDTO getBusinessReportSE(Principal p, @PathVariable String start, @PathVariable String end){
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        HashMap<String,String> hes = new HashMap<String,String>();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(start, formatterDate);
        LocalDate endDate = LocalDate.parse(end, formatterDate);

        for(Checkup c: clinicService.findById(id).getCheckups()){
            if(c.isEnded() && c.getDate().isAfter(startDate) && c.getDate().isBefore(endDate)){
                String val = "";
                if(hes.containsKey(c.getDate().format(formatterDate))) {
                    val = hes.get(c.getDate().format(formatterDate));
                    val = Integer.toString((Integer.parseInt(val)) + 1);
                    hes.put(c.getDate().format(formatterDate), val);
                }else{
                    val = "1";
                    hes.put(c.getDate().format(formatterDate), val);
                }
            }
        }
        BusinessReportDTO brep = new BusinessReportDTO();
        brep.setChart(hes);

        return brep;
    }

    @PreAuthorize("hasAuthority('DOCTOR') or hasAuthority('NURSE')")
    @RequestMapping(method = RequestMethod.POST, path = "/createAbsenceRequest")
    public ResponseEntity createAbsenceRequest(@RequestBody AbsenceRequestDTO arDTO, Principal p) {

        Person person = personService.findByEmail(p.getName());
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(arDTO.getStartDate(), formatterDate);
        LocalDate endDate = LocalDate.parse(arDTO.getEndDate(), formatterDate);

        AbsenceRequest ar = new AbsenceRequest();
        ar.setApproved(false);
        ar.setPerson(person);
        ar.setPeriod(new StartEndDate(startDate, endDate));
        ar.setReviewed(false);
        ar.setType(arDTO.getType());

        absenceRequestService.save(ar);


        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/getPendingAbsenceRequests")
    public @ResponseBody List<AbsenceRequestDTO> getPendingAbsenceRequests(Principal p){
        long id = clinicAdminService.findByEmail(p.getName()).getClinic().getId();
        List<AbsenceRequest> rqs = absenceRequestService.findAll();
        List<AbsenceRequestDTO> rqsDTO = new ArrayList<AbsenceRequestDTO>();
        for(AbsenceRequest ar : rqs){
            if(!ar.isReviewed()){
                boolean thisClinic = false;
                String role = "";
                if(doctorService.findById(ar.getPerson().getId()) != null){
                    if(doctorService.findById(ar.getPerson().getId()).getClinic().getId() == id) {
                        thisClinic = true;
                        role = "DOCTOR";
                    }
                } else if(nurseService.findById(ar.getPerson().getId()) != null){
                    if(nurseService.findById(ar.getPerson().getId()).getClinic().getId() == id) {
                        thisClinic = true;
                        role = "NURSE";
                    }
                }
                if(thisClinic){
                    AbsenceRequestDTO arDTO = new AbsenceRequestDTO(ar, role);
                    rqsDTO.add(arDTO);
                }
            }
        }

        return rqsDTO;
    }

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/approveAbsenceRequest")
    public ResponseEntity denyAbsenceRequest(@RequestBody AbsenceRequestDTO arDTO, Principal p) {
        AbsenceRequest ar = absenceRequestService.findById(arDTO.getId());
        ar.setReviewed(true);
        ar.setApproved(arDTO.isApproved());
        ar.setReason(arDTO.getReason());
        absenceRequestService.save(ar);
        try {
            String appdec = "";
            if(ar.isApproved()) {
                appdec = "approved";
            }else{
                appdec = "declined";
            }
            sendAbsenceRequestEmail(ar.getPerson().getEmail(), ar.getPerson().getName(), ar.getPerson().getSurname(), appdec, ar.getReason());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }
    void sendAbsenceRequestEmail(String sendTo, String firstName, String lastName, String appdec, String reason) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(sendTo);

        helper.setSubject("Centro Clinico account registration");
        String text = "Dear "+ firstName + " " + lastName + ", " + '\n';
        text += "Your absence request was reviewed and " + appdec + " by our clinic administrator.\n\nReason:\n"+reason+"\n\n\n";
        text += "Sincerely, Centro Clinico support team.";
        helper.setText(text);

        javaMailSender.send(msg);

    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @RequestMapping(method = RequestMethod.POST, path = "/scheduleAnotherCheckup")
    public ResponseEntity scheduleAnotherCheckup(@RequestBody CheckupPendingDTO cpDTO) {
        Checkup temp_c = checkupService.findById(cpDTO.getId());
        Checkup c = new Checkup();

        if(temp_c.isStarted() && !temp_c.isEnded()) {
            c.setPatient(temp_c.getPatient());
            c.setClinic(temp_c.getClinic());
            c.setStartEnd(new StartEndTime(cpDTO.getStartTime(), cpDTO.getEndTime()));
            c.setApproved(false);
            c.setDate(cpDTO.getDate());
            c.setDoctor(temp_c.getDoctor());
            c.setEnded(false);
            c.setStarted(false);
            c.setOperation(cpDTO.isOperation());
            c.setType(temp_c.getType());
            checkupService.save(c);
        }else{
            throw new ResourceConflictException("Your checkup has already ended, or hasnt even started!");
        }


        return ResponseEntity.ok().build();
    }
}
