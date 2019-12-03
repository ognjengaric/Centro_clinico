package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Room;

public interface RoomServiceInterface {

    public Room findById(long id);
    public Room save(Room room);
}
