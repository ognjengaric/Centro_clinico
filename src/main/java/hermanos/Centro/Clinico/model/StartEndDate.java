package hermanos.Centro.Clinico.model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class StartEndDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dates_id", nullable = false, unique = true)
    private long id;

    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;

    public StartEndDate(){}

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
