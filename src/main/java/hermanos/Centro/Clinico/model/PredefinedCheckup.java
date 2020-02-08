package hermanos.Centro.Clinico.model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;

@Entity
public class PredefinedCheckup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkup_id", nullable = false, unique = true)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_end_time", referencedColumnName = "time_id", nullable = false)
    private StartEndTime startEnd;

    @Column
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "patient_ssn", referencedColumnName = "socialSecurityNumber", nullable = true)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "checkup_clinic", referencedColumnName = "clinic_id", nullable = true)
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "checkup_doctor", referencedColumnName = "person_id", nullable = true)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "checkup_room", referencedColumnName = "room_id", nullable = true)
    private Room room;


    @ManyToOne
    @JoinColumn(name = "checkup_type", referencedColumnName = "checkuptype_id", nullable = true)
    private CheckupType type;


    public PredefinedCheckup(){}

    public PredefinedCheckup(LocalDate date, LocalTime startTime, LocalTime endTime, Doctor doctor, CheckupType type, Clinic clinic, Room room){
        this.date = date;
        this.startEnd = new StartEndTime(startTime, endTime);
        this.setDoctor(doctor);
        this.setType(type);
        this.setClinic(clinic);
        this.setRoom(room);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StartEndTime getStartEnd() {
        return startEnd;
    }

    public void setStartEnd(StartEndTime startEnd) {
        this.startEnd = startEnd;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public CheckupType getType() {
        return type;
    }

    public void setType(CheckupType type) {
        this.type = type;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
