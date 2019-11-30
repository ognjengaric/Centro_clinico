package hermanos.Centro.Clinico.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "DOCTOR")
public class Doctor extends Person implements Serializable {

    @ManyToOne
    @JoinColumn(name = "clinic", referencedColumnName = "clinic_id", nullable = true)
    private Clinic clinic;


    @Column(nullable = false, unique = true)
    private String socialSecurityNumber;
    private String name;
    private String surname;
    private String avgrating;

    public Doctor(){
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

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAvgrating() {
        return avgrating;
    }

    public void setAvgrating(String avgrating) {
        this.avgrating = avgrating;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }
}
