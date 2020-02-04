package hermanos.Centro.Clinico.dto;

import hermanos.Centro.Clinico.model.MedicalRecord;
import hermanos.Centro.Clinico.model.Report;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDTO {

    private String height;
    private String weight;
    private String bloodType;
    private String allergies;
    private String diopter;
    private int age;

    private List<ReportDTO> reports = new ArrayList<>();

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public MedicalRecordDTO() {
    }

    public MedicalRecordDTO(MedicalRecord medicalRecord){

        this.height = medicalRecord.getHeight();
        this.weight = medicalRecord.getWeight();
        this.bloodType = medicalRecord.getBloodType();
        this.age = medicalRecord.getAge();
        this.diopter = medicalRecord.getDiopter();
        this.allergies = medicalRecord.getAllergies();

        for(Report report: medicalRecord.getReportHistory()){
            this.reports.add(new ReportDTO(report));
        }
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

    public List<ReportDTO> getReports() {
        return reports;
    }

    public void setReports(List<ReportDTO> reports) {
        this.reports = reports;
    }
}
