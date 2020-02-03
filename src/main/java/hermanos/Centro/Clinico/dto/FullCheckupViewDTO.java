package hermanos.Centro.Clinico.dto;

import hermanos.Centro.Clinico.model.Checkup;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FullCheckupViewDTO {
    private String id;
    private String checkupDate;
    private String checkupTimeStart;
    private String checkupTimeEnd;
    private String checkupType;
    private String doctorName;
    private String roomName;

    public FullCheckupViewDTO() {
    }

    public FullCheckupViewDTO(Checkup checkup) {
        this.id = Long.toString(checkup.getId());
        this.checkupDate = checkup.getDate().toString();
        this.checkupTimeStart = checkup.getStartEnd().getStartTime().format(DateTimeFormatter.ofPattern("HH:mm"));
        this.checkupTimeEnd = checkup.getStartEnd().getEndTime().format(DateTimeFormatter.ofPattern("HH:mm"));
        this.checkupType = checkup.getType().getName();
        this.doctorName = checkup.getDoctor().getName();
        this.roomName = checkup.getRoom().getName();
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

    public String getCheckupTimeStart() {
        return checkupTimeStart;
    }

    public void setCheckupTimeStart(String checkupTimeStart) {
        this.checkupTimeStart = checkupTimeStart;
    }

    public String getCheckupTimeEnd() {
        return checkupTimeEnd;
    }

    public void setCheckupTimeEnd(String checkupTimeEnd) {
        this.checkupTimeEnd = checkupTimeEnd;
    }

    public String getCheckupType() {
        return checkupType;
    }

    public void setCheckupType(String checkupType) {
        this.checkupType = checkupType;
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
