package hermanos.Centro.Clinico.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "PATIENT")
public class Patient extends Person implements Serializable {
    @Column(nullable = true, unique = true)
    private String socialSecurityNumber;
    /* TODO: 11/9/2019 medical record */

    @OneToOne
    @JoinColumn(name = "medical_record", referencedColumnName = "m_record_id")
    private MedicalRecord medicalRecord;

    @Column(nullable = true)
    private boolean activated;

    public Patient(){
        super();
        activated = false;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }
}
