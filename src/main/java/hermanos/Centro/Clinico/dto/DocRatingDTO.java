package hermanos.Centro.Clinico.dto;


import hermanos.Centro.Clinico.model.Doctor;

public class DocRatingDTO {
    private long id;
    private String name;
    private String surname;
    private String avgrating;
    private String specialization;
    private String clinic;

    public DocRatingDTO(Doctor doctor){
        id = doctor.getId();
        name = doctor.getName();
        surname = doctor.getSurname();
        avgrating = Float.toString(doctor.calculateAverageRating());
        specialization = doctor.getSpecialization().getName();
        clinic = doctor.getClinic().getName();
    }

    public DocRatingDTO() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAvgrating() {
        return avgrating;
    }

    public void setAvgrating(String avgrating) {
        this.avgrating = avgrating;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }
}
