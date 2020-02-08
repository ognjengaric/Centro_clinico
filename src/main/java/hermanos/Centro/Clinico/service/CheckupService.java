package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.dto.ScheduleFilterDTO;
import hermanos.Centro.Clinico.model.*;
import hermanos.Centro.Clinico.repository.CheckupRepository;
import hermanos.Centro.Clinico.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class CheckupService implements CheckupServiceInterface {
    @Autowired
    CheckupRepository checkupRepository;

    @Autowired
    CheckupTypeServiceInterface checkupTypeService;

    @Autowired
    DoctorServiceInterface doctorService;

    @Autowired
    ClinicServiceInterface clinicService;

    @Autowired
    PredefinedCheckupServiceInterface predefinedCheckupService;

    @Override
    public Checkup findById(long id) {
        return checkupRepository.findById(id);
    }

    @Override
    public Checkup save(Checkup checkup) {
        return checkupRepository.save(checkup);
    }

    @Override
    public boolean isValid(Checkup checkup){


        for(Checkup c : checkupRepository.findAll()){
            if(c.getDate().isEqual(checkup.getDate()) &&
                    !(
                        (c.getStartEnd().getStartTime().isAfter(checkup.getStartEnd().getEndTime())) ||
                        (c.getStartEnd().getEndTime().isBefore(checkup.getStartEnd().getStartTime()))
                    )){
                if(c.getDoctor().getId().equals(checkup.getDoctor().getId()))
                    return false;
            }
        }
        return true;
    }

    public void deleteById(long id){
        checkupRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Checkup makeNewCheckup(ScheduleFilterDTO filterDTO, Patient patient,  boolean isPredefined){
        CheckupType type = checkupTypeService.findByName(filterDTO.getCheckupType());
        Doctor doctor = doctorService.findById(Long.parseLong(filterDTO.getDoctorId
                ()));
        Clinic clinic = clinicService.findById(doctor.getClinic().getId());

        LocalDate date = LocalDate.parse(filterDTO.getCheckupDate());
        LocalTime startTime = LocalTime.parse(filterDTO.getCheckupTime());
        LocalTime endTime = startTime.plusMinutes(type.getDuration());

        Checkup checkup = new Checkup(date, startTime, endTime, doctor, type, clinic, patient);
        if(isPredefined) {
            checkup.setApproved(true);
            PredefinedCheckup predefinedCheckup = predefinedCheckupService.findById(Long.parseLong(filterDTO.getPredefinedCheckupId()));
            clinic.getPredefinedCheckups().remove(predefinedCheckup);
            clinicService.save(clinic);
        }

        return checkup;
    }
}
