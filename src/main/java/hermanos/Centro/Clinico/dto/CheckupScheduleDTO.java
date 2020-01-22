package hermanos.Centro.Clinico.dto;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;


import hermanos.Centro.Clinico.model.StartEndTime;

import java.sql.Date;
import java.sql.Time;

public class CheckupScheduleDTO {
    private int id;
    private String title;
    private boolean allDay;
    private LocalDateTime start;
    private LocalDateTime end;

    public CheckupScheduleDTO(StartEndTime startEnd, LocalDate date, int i, String name) {
        this.start = LocalDateTime.of(date, LocalTime.of(startEnd.getStartTime().getHour(), startEnd.getStartTime().getMinute(), 0));
        this.end = LocalDateTime.of(date, LocalTime.of(startEnd.getEndTime().getHour(), startEnd.getEndTime().getMinute(), 0));
        this.allDay = false;
        this.id = i;
        this.title = name;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }
}
