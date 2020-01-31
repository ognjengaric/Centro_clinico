package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Report;

import java.util.List;

public interface ReportServiceInterface {

    public Report findById(long id);
    public Report save(Report report);
    public List<Report> findAll();
}
