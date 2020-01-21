package hermanos.Centro.Clinico.dto;


import java.sql.Date;
import java.sql.Time;

public class CheckupScheduleDTO {
    private Time startTime;
    private Time endTime;

    public CheckupScheduleDTO(Time startTime, Time endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
