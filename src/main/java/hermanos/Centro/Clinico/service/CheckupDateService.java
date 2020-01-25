package hermanos.Centro.Clinico.service;


import hermanos.Centro.Clinico.model.Checkup;
import hermanos.Centro.Clinico.model.CheckupDate;
import hermanos.Centro.Clinico.repository.CheckupDateRepository;
import hermanos.Centro.Clinico.repository.CheckupRepository;
import hermanos.Centro.Clinico.service.interfaces.CheckupDateServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckupDateService implements CheckupDateServiceInterface {

    @Autowired
    CheckupDateRepository checkupDateRepository;
    @Autowired
    CheckupRepository checkupRepository;

    @Override
    public CheckupDate findById(long id){
        return  checkupDateRepository.findById(id);
    }

    @Override
    public CheckupDate save(CheckupDate checkupDate){
        return checkupDateRepository.save(checkupDate);
    }

    @Override
    public void deleteById(long id){checkupDateRepository.deleteById(id);}

    @Override
    public boolean isValid(CheckupDate checkup){
        for(CheckupDate c : checkupDateRepository.findAll()){
            if(c.getDate().isEqual(checkup.getDate()) &&
                    c.getStartEnd().getStartTime().compareTo(checkup.getStartEnd().getStartTime()) >= 0 &&
                    c.getStartEnd().getEndTime().compareTo(checkup.getStartEnd().getEndTime()) <= 0 &&
                    (c.getDoctor().getId()==checkup.getDoctor().getId() || c.getRoom().getId()==checkup.getRoom().getId())){
                return false;
            }
        }
        for(Checkup c : checkupRepository.findAll()){
            if(c.getDate().isEqual(checkup.getDate()) &&
                    c.getStartEnd().getStartTime().compareTo(checkup.getStartEnd().getStartTime()) >= 0 &&
                    c.getStartEnd().getEndTime().compareTo(checkup.getStartEnd().getEndTime()) <= 0 &&
                    (c.getDoctor().getId()==checkup.getDoctor().getId() || c.getRoom().getId()==checkup.getRoom().getId())){
                return false;
            }
        }
        return true;
    }

}

