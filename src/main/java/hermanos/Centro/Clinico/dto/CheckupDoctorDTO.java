package hermanos.Centro.Clinico.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.MINUTES;

public class CheckupDoctorDTO {
    private long id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private long doctor_id;
    private String doctor_name;
    private long patient_id;
    private String patient_name;
    private long room_id;
    private long duration;
    private String start;
    private String end;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public CheckupDoctorDTO(long id, LocalDate date, LocalTime startTime, LocalTime endTime, long doctor_id, String doctor_name, long patient_id, String patient_name, long room_id) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.duration = startTime.until(endTime, MINUTES);
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.room_id = room_id;
        //this.start = LocalDateTime.now();
        this.start = date.atTime(startTime).format(formatter);
        this.end = date.atTime(endTime).format(formatter);
//        String s1 = "2020-02-14 10:00";
//        String s2 = "2020-02-14 13:00";
//        this.start = LocalDateTime.parse(s1, formatter);
//        this.end = LocalDateTime.parse(s2, formatter);
//        System.out.println("Before : " + s1);
//
        System.out.println("After 1: " + this.start);
        System.out.println("After 1: " + this.end);
//
//        System.out.println("After 2: " + this.start.format(formatter));

    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(long patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }



    public CheckupDoctorDTO() {}



    public CheckupDoctorDTO(long id, LocalDate date, LocalTime startTime, LocalTime endTime, long doctor_id, String doctor_name, long room_id) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.room_id = room_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
//
//    public void setDate(LocalDateTime datetime) { this.date = datetime.toLocalDate();}

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
//
//    public void setStartTime(LocalDateTime startDatetime) {
//        this.startTime = startDatetime.toLocalTime();
//    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
