package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Room;

public interface RoomServiceInterface {

    public Room findByRoomId(long roomId);
    public Room save(Room room);
}
