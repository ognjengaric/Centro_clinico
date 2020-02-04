package hermanos.Centro.Clinico.dto;

public class ScheduleFilterDTO {
    private String checkupDate;
    private String checkupTime;
    private String checkupType;
    private String doctorId;
    private String roomId;
    private String predefinedCheckupId;

    public ScheduleFilterDTO(){}

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

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

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPredefinedCheckupId() {
        return predefinedCheckupId;
    }

    public void setPredefinedCheckupId(String predefinedCheckupId) {
        this.predefinedCheckupId = predefinedCheckupId;
    }
}
