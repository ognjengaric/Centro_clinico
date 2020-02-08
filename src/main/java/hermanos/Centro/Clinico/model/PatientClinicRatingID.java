package hermanos.Centro.Clinico.model;

import java.io.Serializable;
import java.util.Objects;

public class PatientClinicRatingID implements Serializable {

    private long patient;
    private long clinic;

    public PatientClinicRatingID(){}

    public long getPatient() {
        return patient;
    }

    public void setPatient(long patient) {
        this.patient = patient;
    }

    public long getClinic() {
        return clinic;
    }

    public void setClinic(long clinic) {
        this.clinic = clinic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(patient, clinic);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        PatientClinicRatingID that = (PatientClinicRatingID) obj;
        return Objects.equals(patient, that.patient) &&
                Objects.equals(clinic, that.clinic);
    }
}
