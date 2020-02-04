package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.model.*;
import hermanos.Centro.Clinico.repository.AbsenceRequestRepository;
import hermanos.Centro.Clinico.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AbsenceRequestService implements AbsenceRequestServiceInterface {
    @Autowired
    AbsenceRequestRepository absenceRequestRepository;

    @Override
    public AbsenceRequest findById(long id) {
        return absenceRequestRepository.findById(id);
    }

    @Override
    public AbsenceRequest save(AbsenceRequest absenceRequest) {
        return absenceRequestRepository.save(absenceRequest);
    }
    @Override
    public List<AbsenceRequest> findAll(){return absenceRequestRepository.findAll();}

    public void deleteById(long id){
        absenceRequestRepository.deleteById(id);
    }

}
