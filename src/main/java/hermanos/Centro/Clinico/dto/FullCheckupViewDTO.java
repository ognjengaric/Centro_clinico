package hermanos.Centro.Clinico.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FullCheckupViewDTO {
    private String id;
    private String checkupDate;
    private String checkupTime;
    private String checkupType;
    private Long duration;
    private String doctorName;
    private String roomName;

    public FullCheckupViewDTO() {
    }

    public FullCheckupViewDTO(String id, LocalDate checkupDate, LocalTime checkupTime, String checkupType, Long duration, String doctorName, String roomName) {
        this.id = id;
        this.checkupDate = checkupDate.toString();
        this.checkupTime = checkupTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        this.checkupType = checkupType;
        this.duration = duration;
        this.doctorName = doctorName;
        this.roomName = roomName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
