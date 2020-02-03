package hermanos.Centro.Clinico.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.MINUTES;

public class CheckupPendingDTO {
    private long id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private long doctor_id;
    private String doctor_name;
    private long room_id;
    private long duration;

    public CheckupPendingDTO() {}

    public CheckupPendingDTO(long id, LocalDate date, LocalTime startTime, LocalTime endTime, long doctor_id, String doctor_name) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.duration = startTime.until(endTime, MINUTES);
    }

    public CheckupPendingDTO(long id, LocalDate date, LocalTime startTime, LocalTime endTime, long doctor_id, String doctor_name, long room_id) {
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
