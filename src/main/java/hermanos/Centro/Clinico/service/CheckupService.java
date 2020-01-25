package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.model.Checkup;
import hermanos.Centro.Clinico.repository.CheckupRepository;
import hermanos.Centro.Clinico.service.interfaces.CheckupServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckupService implements CheckupServiceInterface {
    @Autowired
    CheckupRepository checkupRepository;

    @Override
    public Checkup findById(long id) {
        return checkupRepository.findById(id);
    }

    @Override
    public Checkup save(Checkup checkup) {
        return checkupRepository.save(checkup);
    }

    @Override
    public boolean isValid(Checkup checkup){
        for(Checkup c : checkupRepository.findAll()){
            if(c.getDate().isEqual(checkup.getDate()) &&
                    c.getStartEnd().getStartTime().compareTo(checkup.getStartEnd().getStartTime()) >= 0 &&
                        c.getStartEnd().getEndTime().compareTo(checkup.getStartEnd().getEndTime()) <= 0){
                return false;
            }
        }
        return true;
    }
}
