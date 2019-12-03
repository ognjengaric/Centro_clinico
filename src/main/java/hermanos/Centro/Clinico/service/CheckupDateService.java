package hermanos.Centro.Clinico.service;


import hermanos.Centro.Clinico.model.CheckupDate;
import hermanos.Centro.Clinico.repository.CheckupDateRepository;
import hermanos.Centro.Clinico.service.interfaces.CheckupDateServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckupDateService implements CheckupDateServiceInterface {

    @Autowired
    private CheckupDateRepository checkupDateRepository;

    @Override
    public CheckupDate findByDate(String date){
        return  checkupDateRepository.findByDate(date);
    }

    @Override
    public CheckupDate save(CheckupDate checkupDate){
        return checkupDateRepository.save(checkupDate);
    }

    @Override
    public void deleteById(long id){checkupDateRepository.deleteById(id);}

    public List<CheckupDate> findAll() {
        List<CheckupDate> result = checkupDateRepository.findAll();
        return result;
    }
}

