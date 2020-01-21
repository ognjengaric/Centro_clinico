package hermanos.Centro.Clinico.dto;

import hermanos.Centro.Clinico.model.CheckupType;

import java.sql.Date;
import java.sql.Time;

public class ScheduleFilterDTO {
    private String checkupDate;
    private String checkupTime;
    private String checkupType;

    public ScheduleFilterDTO(){}

    public String getCheckupDate() {
        return checkupDate;
    }

    public void setCheckupDate(String checkupDate) {
        this.checkupDate = checkupDate;
    }

    public String getCheckupTime() {
        return checkupTime;
    }

    public void setCheckupTime(String checkupTime) {
        this.checkupTime = checkupTime;
    }

    public String getCheckupType() {
        return checkupType;
    }

    public void setCheckupType(String checkupType) {
        this.checkupType = checkupType;
    }
}
