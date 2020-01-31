package hermanos.Centro.Clinico.dto;


import hermanos.Centro.Clinico.model.Checkup;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class RoomDTO {
    private long id;
    private String name;

    public RoomDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoomDTO() {
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

}
