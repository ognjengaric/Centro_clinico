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

    public Address getClinicAddress(){
        return clinic.getAddress();
    }

    public String getClinicName(){
        return clinic.getName();
    }

    public long getClinicId() { return clinic.getId();}

    public void setClinicAddress(Address adr){
        clinic.setAddress(adr);
    }

    public void setClinicName(String nam){
        clinic.setName(nam);
    }

    public void setClinic(Clinic c){
        clinic = c;
    }

}
