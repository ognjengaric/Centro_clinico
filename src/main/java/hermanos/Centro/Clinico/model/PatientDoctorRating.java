package hermanos.Centro.Clinico.model;

import javax.persistence.*;

@Entity
@IdClass(PatientDoctorRatingID.class)
public class PatientDoctorRating {

    @Id
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "person_id")
    private Patient patient;

    @Id
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "person_id")
    private Doctor doctor;

    @Column
    private int rating;

    public PatientDoctorRating(){}

    public PatientDoctorRating(Patient patient, Doctor doctor, int rating){
        this.patient = patient;
        this.doctor = doctor;
        this.rating = rating;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
