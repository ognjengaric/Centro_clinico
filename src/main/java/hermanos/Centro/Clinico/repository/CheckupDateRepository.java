package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.CheckupDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckupDateRepository extends JpaRepository<CheckupDate, Long> {

        CheckupDate findByDate(String date);

}
