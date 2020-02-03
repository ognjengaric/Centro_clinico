package hermanos.Centro.Clinico.model;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {

    //@
    //private List<Report> reportHistory = new ArrayList<>();

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

    //public List<Report> getReportHistory() {
     //   return reportHistory;
    //}

    //public void setReportHistory(List<Report> reportHistory) {
      //  this.reportHistory = reportHistory;
    //}

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
