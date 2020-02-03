package hermanos.Centro.Clinico.dto;


import hermanos.Centro.Clinico.model.Doctor;
import hermanos.Centro.Clinico.model.StartEndTime;

public class DocNameSurnameDTO {
    private long id;
    private String name;
    private String surname;
    private String spec;
    private StartEndTime shift;

    public DocNameSurnameDTO() {
        super();
    }

    public DocNameSurnameDTO(Doctor d) {
        this.id = d.getId();
        this.name = d.getName();
        this.surname = d.getSurname();
        this.spec = d.getSpecialization().getName();
        this.shift = d.getShift();
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

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public StartEndTime getShift() {
        return shift;
    }

    public void setShift(StartEndTime shift) {
        this.shift = shift;
    }
}
