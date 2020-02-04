package hermanos.Centro.Clinico.dto;

import hermanos.Centro.Clinico.model.Medicine;

public class MedicineDTO {

    private String code;
    private String description;

    public MedicineDTO(){}

    public MedicineDTO(Medicine medicine){
        this.code = medicine.getCode();
        this.description = medicine.getDescription();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
