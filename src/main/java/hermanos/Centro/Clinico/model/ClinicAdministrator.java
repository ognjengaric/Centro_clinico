package hermanos.Centro.Clinico.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@DiscriminatorValue(value = "CLINIC_ADMINISTRATOR")
public class ClinicAdministrator extends Person{

    @ManyToOne
    @JoinColumn(name = "clinic", referencedColumnName = "clinic_id", nullable = true)
    private Clinic clinic;

    public ClinicAdministrator() {
    }

    public void setClinic(Clinic c){
        clinic = c;
    }

    public Clinic getClinic() {
        return clinic;
    }
}
