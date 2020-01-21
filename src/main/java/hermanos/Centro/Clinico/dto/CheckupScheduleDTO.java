package hermanos.Centro.Clinico.dto;


import hermanos.Centro.Clinico.model.StartEndTime;

import java.sql.Date;
import java.sql.Time;

public class CheckupScheduleDTO {
    private StartEndTime startEnd;

    public CheckupScheduleDTO(StartEndTime startEnd) {
        this.startEnd = startEnd;
    }

    public StartEndTime getStartEnd() {
        return startEnd;
    }

    public void setStartEnd(StartEndTime startEnd) {
        this.startEnd = startEnd;
    }
}
