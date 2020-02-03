package hermanos.Centro.Clinico.dto;

import hermanos.Centro.Clinico.model.Checkup;
import hermanos.Centro.Clinico.model.Doctor;
import hermanos.Centro.Clinico.model.StartEndDate;
import hermanos.Centro.Clinico.model.StartEndTime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.ChronoUnit;

public class DoctorWorkingScheduleDTO {

    private List<CheckupScheduleDTO> checkups = new ArrayList<>();
    private List<CheckupScheduleDTO> absences = new ArrayList<>();
    private StartEndTime shift = new StartEndTime();
    private String ime;
    private String prezime;
    private long id;

    public DoctorWorkingScheduleDTO(){

    }

    public DoctorWorkingScheduleDTO(Doctor doctor){
        shift = doctor.getShift();

        for(Checkup c : doctor.getCheckups()){
            checkups.add(new CheckupScheduleDTO(c));
        }

        for(StartEndDate abs : doctor.getAbsences()){

            for(int i=0; i <= ChronoUnit.DAYS.between(abs.getStartDate(), abs.getEndDate()); i++){
                absences.add(new CheckupScheduleDTO(abs.getStartDate().plusDays(i), shift));
            }
        }
    }

    public List<CheckupScheduleDTO> getAbsences() {
        return absences;
    }

    public void setAbsences(List<CheckupScheduleDTO> absences) {
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

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
