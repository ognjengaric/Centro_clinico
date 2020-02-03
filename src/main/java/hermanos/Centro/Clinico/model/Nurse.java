package hermanos.Centro.Clinico.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Nurse extends Person {

    @ManyToOne
    @JoinColumn(name = "nurse_clinic", referencedColumnName = "clinic_id", nullable = true)
    private Clinic doctor_clinic;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shift", referencedColumnName = "time_id", nullable = true)
    private StartEndTime shift;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="absences", joinColumns=@JoinColumn(name="person_id"),
            inverseJoinColumns=@JoinColumn(name="dates_id"))
    private List<StartEndDate> absences;

    public Nurse() {
        super();
    }

    public Clinic getClinic() {
        return doctor_clinic;
    }

    public void setClinic(Clinic clinic) {
        this.doctor_clinic = clinic;
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

}
