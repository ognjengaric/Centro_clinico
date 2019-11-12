package hermanos.Centro.Clinico.model;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class ClinicAdministrator extends Person{

    private Clinic clinic;

    public ClinicAdministrator() {
    }

    public Address getClinicAddress(){
        return clinic.getAddress();
    }

    public String getClinicName(){
        return clinic.getName();
    }

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
