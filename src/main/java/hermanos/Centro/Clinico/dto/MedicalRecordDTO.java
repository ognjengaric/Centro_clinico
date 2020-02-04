package hermanos.Centro.Clinico.dto;

import hermanos.Centro.Clinico.model.MedicalRecord;

public class MedicalRecordDTO {

    private float height;
    private float weight;
    private String bloodType;
    private String allergies;
    private String diopter;
    private int age;

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
