package hermanos.Centro.Clinico.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "ROOM")
public class Room {

    @ManyToOne
    @JoinColumn(name = "clinic", referencedColumnName = "clinic_id", nullable = true)
    private Clinic clinic;

    @Id
    @Column(nullable = false, unique = true)
    private long roomId;


    public Room(){
        super();
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }







}
