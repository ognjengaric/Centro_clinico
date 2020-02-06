package hermanos.Centro.Clinico.model;

import javax.persistence.*;

@Entity
public class AbsenceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "absencerq_person", referencedColumnName = "person_id", nullable = true)
    private Person person;

    @Column(nullable = false)
    private String type;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "start_end_date", referencedColumnName = "dates_id", nullable = false)
    private StartEndDate period;

    @Column(nullable = true)
    private String reason;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean reviewed;

    @Column(nullable = true)
    private boolean approved;

    public AbsenceRequest() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public StartEndDate getPeriod() {
        return period;
    }

    public void setPeriod(StartEndDate period) {
        this.period = period;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
