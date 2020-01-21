package hermanos.Centro.Clinico.model;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class StartEndTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_id", nullable = false, unique = true)
    private long id;

    @Column
    private LocalTime startTime;
    @Column
    private LocalTime endTime;

    public StartEndTime(){}

    public StartEndTime(LocalTime start, LocalTime end){
        startTime = start;
        endTime = end;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
