package hermanos.Centro.Clinico.model;


import java.io.Serializable;
import java.util.Objects;

public class PatientDoctorRatingID implements Serializable {
    private long patient;
    private long doctor;

    public PatientDoctorRatingID(){}

    public long getPatient() {
        return patient;
    }

    public void setPatient(long patient) {
        this.patient = patient;
    }

    public long getDoctor() {
        return doctor;
    }

    public void setDoctor(long doctor) {
        this.doctor = doctor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(patient, doctor);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        PatientDoctorRatingID that = (PatientDoctorRatingID) obj;
        return Objects.equals(patient, that.patient) &&
                Objects.equals(doctor, that.doctor);
    }
}
