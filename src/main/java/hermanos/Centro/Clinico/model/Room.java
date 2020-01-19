package hermanos.Centro.Clinico.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "ROOM")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false, unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "rooms_clinic", referencedColumnName = "clinic_id", nullable = true)
    private Clinic rooms_clinic;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Checkup> checkups = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String name;


    public Room(){
        super();
    }

    public Clinic getClinic() {
        return rooms_clinic;
    }

    public void setClinic(Clinic clinic) {
        this.rooms_clinic = clinic;
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
