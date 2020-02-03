package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.model.Doctor;
import hermanos.Centro.Clinico.model.Nurse;
import hermanos.Centro.Clinico.repository.NurseRepository;
import hermanos.Centro.Clinico.service.interfaces.NurseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseService implements NurseServiceInterface {

    @Autowired
    private NurseRepository nurseRepository;

    @Override
    public Nurse findById(long id){
        return  nurseRepository.findById(id);
    }

    @Override
    public Nurse save(Nurse doctor){
        return nurseRepository.save(doctor);
    }

    @Override
    public void deleteById(long id) {nurseRepository.deleteById(id);}

    public List<Nurse> findAll() {
        List<Nurse> result = nurseRepository.findAll();
        return result;
    }
}
