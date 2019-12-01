package hermanos.Centro.Clinico.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "PATIENT")
public class Patient extends Person implements Serializable {
    @Column(nullable = true, unique = true)
    private String socialSecurityNumber;
    /* TODO: 11/9/2019 medical record */

    public Patient(){
        super();
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }
}
