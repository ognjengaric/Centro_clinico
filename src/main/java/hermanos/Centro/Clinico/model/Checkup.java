package hermanos.Centro.Clinico.model;

import java.sql.*;

import javax.persistence.*;

@Entity
public class Checkup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkup_id", nullable = false, unique = true)
    private long id;

    @Column
    private Time startTime;
    @Column
    private Time endTime;
    @Column
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "checkup_clinic", referencedColumnName = "clinic_id", nullable = true)
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "checkup_doctor", referencedColumnName = "person_id", nullable = true)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "checkup_room", referencedColumnName = "room_id", nullable = true)
    private Room room;

    @Column
    private boolean isApproved;
    @Column
    private boolean isStarted;
    @Column
    private boolean isEnded;

    public Checkup(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public void setEnded(boolean ended) {
        isEnded = ended;
    }
}
