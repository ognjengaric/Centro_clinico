package hermanos.Centro.Clinico.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Report implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false, unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name="diagnosis_id", referencedColumnName = "diagnosis_id")
    private Diagnosis diagnosis;

    @OneToOne
    @JoinColumn(name = "prescription_id", referencedColumnName = "prescription_id")
    private Prescription prescription;

    @Column(nullable = true)
    private String description;

    public Report() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnose_id) {
        this.diagnosis = diagnose_id;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
