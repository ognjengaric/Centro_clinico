package hermanos.Centro.Clinico.service;


import hermanos.Centro.Clinico.model.CheckupType;
import hermanos.Centro.Clinico.repository.CheckupTypeRepository;
import hermanos.Centro.Clinico.service.interfaces.CheckupTypeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckupTypeService implements CheckupTypeServiceInterface {

    @Autowired
    private CheckupTypeRepository checkupTypeRepository;

    @Override
    public CheckupType findByName(String name){
        return  checkupTypeRepository.findByName(name);
    }

    @Override
    public CheckupType findById(long id){
        //return checkupTypeRepository.findById(id);
        return null;
    }

    @Override
    public CheckupType save(CheckupType checkupType){
        return checkupTypeRepository.save(checkupType);
    }

    @Override
    public void deleteById(long id) {checkupTypeRepository.deleteById(id);}

    @Override
    public List<CheckupType> findAll() {
        List<CheckupType> result = checkupTypeRepository.findAllByOrderByNameAsc();
        return result;
    }

}

