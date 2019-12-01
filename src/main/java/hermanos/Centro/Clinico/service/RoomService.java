package hermanos.Centro.Clinico.service;


import hermanos.Centro.Clinico.model.Room;
import hermanos.Centro.Clinico.repository.RoomRepository;
import hermanos.Centro.Clinico.service.interfaces.RoomServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements RoomServiceInterface {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room findByRoomId(String roomId){
        return  roomRepository.findByRoomId(roomId);
    }

    @Override
    public Room save(Room room){
        return roomRepository.save(room);
    }

    public List<Room> findAll() {
        List<Room> result = roomRepository.findAll();
        return result;
    }
}

