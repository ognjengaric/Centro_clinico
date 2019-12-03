package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

        Room findById(long id);
}
