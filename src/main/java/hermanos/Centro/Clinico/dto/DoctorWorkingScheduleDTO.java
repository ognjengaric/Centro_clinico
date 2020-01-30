package hermanos.Centro.Clinico.dto;

import hermanos.Centro.Clinico.model.Checkup;
import hermanos.Centro.Clinico.model.Doctor;
import hermanos.Centro.Clinico.model.StartEndDate;
import hermanos.Centro.Clinico.model.StartEndTime;

import java.util.ArrayList;
import java.util.List;

public class DoctorWorkingScheduleDTO {

    private List<CheckupScheduleDTO> checkups = new ArrayList<>();
    private List<StartEndDate> absences = new ArrayList<>();
    private StartEndTime shift = new StartEndTime();

    public DoctorWorkingScheduleDTO(){

    }

    public DoctorWorkingScheduleDTO(Doctor doctor){
        absences = doctor.getAbsences();
        shift = doctor.getShift();

        for(Checkup c : doctor.getCheckups()){
            checkups.add(new CheckupScheduleDTO(c));
        }
    }

    public List<StartEndDate> getAbsences() {
        return absences;
    }

    public void setAbsences(List<StartEndDate> absences) {
        this.absences = absences;
    }

    public StartEndTime getShift() {
        return shift;
    }

    public void setShift(StartEndTime shift) {
        this.shift = shift;
    }

    public List<CheckupScheduleDTO> getCheckups() {
        return checkups;
    }

    public void setCheckups(List<CheckupScheduleDTO> checkups) {
        this.checkups = checkups;
    }
}
