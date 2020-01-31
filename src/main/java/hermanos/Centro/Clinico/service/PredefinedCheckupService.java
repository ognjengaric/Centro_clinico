package hermanos.Centro.Clinico.service;


import hermanos.Centro.Clinico.model.Checkup;
import hermanos.Centro.Clinico.model.PredefinedCheckup;
import hermanos.Centro.Clinico.repository.PredefinedCheckupRepository;
import hermanos.Centro.Clinico.repository.CheckupRepository;
import hermanos.Centro.Clinico.service.interfaces.PredefinedCheckupServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PredefinedCheckupService implements PredefinedCheckupServiceInterface {

    @Autowired
    PredefinedCheckupRepository predefinedCheckupRepository;
    @Autowired
    CheckupRepository checkupRepository;

    @Override
    public PredefinedCheckup findById(long id){
        return  predefinedCheckupRepository.findById(id);
    }

    @Override
    public PredefinedCheckup save(PredefinedCheckup predefinedCheckup){
        return predefinedCheckupRepository.save(predefinedCheckup);
    }

    @Override
    public void deleteById(long id){
        predefinedCheckupRepository.deleteById(id);}

    @Override
    public boolean isValid(PredefinedCheckup checkup){
        for(PredefinedCheckup c : predefinedCheckupRepository.findAll()){
            if(c.getDate().isEqual(checkup.getDate()) &&
                    c.getStartEnd().getStartTime().compareTo(checkup.getStartEnd().getStartTime()) >= 0 &&
                    c.getStartEnd().getEndTime().compareTo(checkup.getStartEnd().getEndTime()) <= 0 &&
                    (c.getDoctor().getId()==checkup.getDoctor().getId() || c.getRoom().getId()==checkup.getRoom().getId())){
                return false;
            }
        }
        for(Checkup c : checkupRepository.findAll()){
            if(c.getDate().isEqual(checkup.getDate()) &&
                    !(
                            (c.getStartEnd().getStartTime().isAfter(checkup.getStartEnd().getEndTime())) ||
                            (c.getStartEnd().getEndTime().isBefore(checkup.getStartEnd().getStartTime()))
                    ) &&
                    (c.getDoctor().getId()==checkup.getDoctor().getId() || c.getRoom().getId()==checkup.getRoom().getId())){
                return false;
            }
        }
        return true;
    }

}

