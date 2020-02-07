package hermanos.Centro.Clinico.model;

import javax.persistence.*;

@Entity
@IdClass(PatientClinicRatingID.class)
public class PatientClinicRating {
    @Id
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "person_id")
    private Patient patient;

    @Id
    @ManyToOne
    @JoinColumn(name = "clinic_id", referencedColumnName = "clinic_id")
    private Clinic clinic;

    @Column
    private int rating;

    public PatientClinicRating() {
    }

    public PatientClinicRating(Patient patient, Clinic clinic, int rating){
        this.patient = patient;
        this.clinic = clinic;
        this.rating = rating;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
