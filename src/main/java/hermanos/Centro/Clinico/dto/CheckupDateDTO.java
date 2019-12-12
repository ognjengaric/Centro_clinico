package hermanos.Centro.Clinico.dto;


public class CheckupDateDTO {
    private long id;
    private String date;

    public CheckupDateDTO() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String name) {
        this.date = name;
    }

}
