package hermanos.Centro.Clinico.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "CHECKUP_TYPE")
public class CheckupType extends Person implements Serializable {

    @ManyToOne
    @JoinColumn(name = "clinic", referencedColumnName = "clinic_id", nullable = true)
    private Clinic clinic;

    @Column(nullable = false, unique = true)
    private String name;


    public CheckupType(){
        super();
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
