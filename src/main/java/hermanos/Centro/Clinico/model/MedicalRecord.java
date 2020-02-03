package hermanos.Centro.Clinico.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_record_id", nullable = false, unique = true)
    private long id;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinTable(name="m_record_report_mapping",joinColumns=@JoinColumn(name="m_record_id"),inverseJoinColumns=@JoinColumn(name="report_id"))
    private List<Report> reportHistory = new ArrayList<>();

    @Column
    private String height;

    @Column
    private String weight;

    @Column
    private String bloodType;

    @Column
    private String allergies;

    @Column
    private String diopter;


    public MedicalRecord() {
    }

    public List<Report> getReportHistory() {
        return reportHistory;
    }

    public void setReportHistory(List<Report> reportHistory) {
        this.reportHistory = reportHistory;
    }

    public void setReport(Report report) {
        this.reportHistory.add(report);
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getDiopter() {
        return diopter;
    }

    public void setDiopter(String diopter) {
        this.diopter = diopter;
    }
}
