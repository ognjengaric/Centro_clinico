package hermanos.Centro.Clinico.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "PATIENT")
public class Patient extends Person implements Serializable {
    @Column(nullable = true, unique = true)
    private String socialSecurityNumber;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Checkup> checkups = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "medical_record", referencedColumnName = "m_record_id")
    private MedicalRecord medicalRecord;

    @Column(nullable = true)
    private boolean activated;

    @OneToMany(mappedBy = "patient")
    private List<PatientDoctorRating> ratedByD;

    @OneToMany(mappedBy = "patient")
    private List<PatientClinicRating> ratedByC;

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

    public List<Checkup> getCheckups() {
        return checkups;
    }

    public void setCheckups(List<Checkup> checkups) {
        this.checkups = checkups;
    }

    public List<PatientDoctorRating> getRatedByD() {
        return ratedByD;
    }

    public void setRatedByD(List<PatientDoctorRating> ratedByD) {
        this.ratedByD = ratedByD;
    }

    public List<PatientClinicRating> getRatedByC() {
        return ratedByC;
    }

    public void setRatedByC(List<PatientClinicRating> ratedByC) {
        this.ratedByC = ratedByC;
    }
}

