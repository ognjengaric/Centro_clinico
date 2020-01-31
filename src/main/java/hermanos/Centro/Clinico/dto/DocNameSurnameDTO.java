package hermanos.Centro.Clinico.dto;


import hermanos.Centro.Clinico.model.Doctor;

public class DocNameSurnameDTO {
    private long id;
    private String name;
    private String surname;
    private String spec;

    public DocNameSurnameDTO() {
        super();
    }

    public DocNameSurnameDTO(Doctor d) {
        this.id = d.getId();
        this.name = d.getName();
        this.surname = d.getSurname();
        this.spec = d.getSpecialization().getName();
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
}
