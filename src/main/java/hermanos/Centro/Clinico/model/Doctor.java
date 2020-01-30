package hermanos.Centro.Clinico.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@DiscriminatorValue(value = "DOCTOR")
public class Doctor extends Person implements Serializable {

    @ManyToOne
    @JoinColumn(name = "doctor_clinic", referencedColumnName = "clinic_id", nullable = true)
    private Clinic doctor_clinic;

    @Column(nullable = true)
    private String avgrating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shift", referencedColumnName = "time_id", nullable = true)
    private StartEndTime shift;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="absences", joinColumns=@JoinColumn(name="person_id"),
            inverseJoinColumns=@JoinColumn(name="dates_id"))
    private List<StartEndDate> absences;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CheckupType specialization;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Checkup> checkups = new ArrayList<>();

    public Doctor(){
        super();
    }

    public Clinic getClinic() {
        return doctor_clinic;
    }

    public void setClinic(Clinic clinic) {
        this.doctor_clinic = clinic;
    }

    public String getAvgrating() {
        return avgrating;
    }

    public void setAvgrating(String avgrating) {
        this.avgrating = avgrating;
    }

    public Clinic getDoctor_clinic() {
        return doctor_clinic;
    }

    public void setDoctor_clinic(Clinic doctor_clinic) {
        this.doctor_clinic = doctor_clinic;
    }

    public List<Checkup> getCheckups() {
        return checkups;
    }

    public void setCheckups(List<Checkup> checkups) {
        this.checkups = checkups;
    }

    public StartEndTime getShift() {
        return shift;
    }

    public void setShift(StartEndTime shift) {
        this.shift = shift;
    }

    public List<StartEndDate> getAbsences() {
        return absences;
    }

    public void setAbsences(List<StartEndDate> absences) {
        this.absences = absences;
    }

    public CheckupType getSpecialization() {
        return specialization;
    }

    public void setSpecialization(CheckupType specialization) {
        this.specialization = specialization;
    }
}
