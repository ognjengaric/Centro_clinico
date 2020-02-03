package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.model.Report;
import hermanos.Centro.Clinico.repository.ReportRepository;
import hermanos.Centro.Clinico.service.interfaces.ReportServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService implements ReportServiceInterface {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Report findById(long id){
        return  reportRepository.findById(id);
    }

    @Override
    public Report save(Report report){
        return reportRepository.save(report);
    }

    public List<Report> findAll() {
        List<Report> result = reportRepository.findAll();
        return result;
    }
}
