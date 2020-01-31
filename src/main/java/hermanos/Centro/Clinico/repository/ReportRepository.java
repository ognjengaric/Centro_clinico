package hermanos.Centro.Clinico.repository;

import hermanos.Centro.Clinico.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findById(long id);
}
