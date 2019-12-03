package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface RoomRepository extends JpaRepository<Room, Long> {

        Room findById(long id);

        @Modifying
        void deleteById(long id);
}
