package hermanos.Centro.Clinico.service;


import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    public Clinic findById(long id){
        return  clinicRepository.findById(id);
    }

    public Clinic save(Clinic clinic){
        return clinicRepository.save(clinic);
    }

}

