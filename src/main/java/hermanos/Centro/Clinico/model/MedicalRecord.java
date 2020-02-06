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
    private float height;

    @Column
    private float weight;

    @Column
    private String bloodType;

    @Column
    private String allergies;

    @Column
    private String diopter;

    @Column
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

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

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
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
