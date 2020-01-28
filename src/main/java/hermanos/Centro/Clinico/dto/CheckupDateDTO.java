package hermanos.Centro.Clinico.dto;


import jdk.vm.ci.meta.Local;

import java.time.LocalDate;
import java.time.LocalTime;

public class CheckupDateDTO {
    private long id;
    private String date;
    private String startTime;
    private String endTime;
    private String doctor;
    private String room;

    public CheckupDateDTO() {
        super();
    }

    public CheckupDateDTO(long id, LocalDate date, LocalTime startTime, LocalTime endTime, String doctor, String room) {
        this.id = id;
        this.date = date.toString();
        this.startTime = startTime.toString();
        this.endTime = endTime.toString();
        this.doctor = doctor;
        this.room = room;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
