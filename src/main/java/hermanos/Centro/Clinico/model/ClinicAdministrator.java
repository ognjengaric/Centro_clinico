package hermanos.Centro.Clinico.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@DiscriminatorValue(value = "CLINIC_ADMIN")
public class ClinicAdministrator extends Person{

    @ManyToOne
    @JoinColumn(name = "admin_clinic", referencedColumnName = "clinic_id", nullable = true)
    private Clinic admin_clinic;

    public ClinicAdministrator() {
    }

    public void setClinic(Clinic c){
        admin_clinic = c;
    }

    public Clinic getClinic() {
        return admin_clinic;
    }
}
