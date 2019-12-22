package hermanos.Centro.Clinico.dto;

import hermanos.Centro.Clinico.model.Clinic;

public class ClinicTableDTO {
    private String name;
    private String description;

    public  ClinicTableDTO(){

    }

    public ClinicTableDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public  ClinicTableDTO(Clinic clinic){
        this.name = clinic.getName();
        this.description = clinic.getDescription();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
