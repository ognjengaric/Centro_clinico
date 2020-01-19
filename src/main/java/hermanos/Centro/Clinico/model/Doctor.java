package hermanos.Centro.Clinico.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "DOCTOR")
public class Doctor extends Person implements Serializable {

    @ManyToOne
    @JoinColumn(name = "doctor_clinic", referencedColumnName = "clinic_id", nullable = true)
    private Clinic doctor_clinic;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Checkup> checkups = new ArrayList<>();

    @Column(nullable = true)
    private String avgrating;

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

}
