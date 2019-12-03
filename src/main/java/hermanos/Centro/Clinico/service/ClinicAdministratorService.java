package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.model.ClinicAdministrator;
import hermanos.Centro.Clinico.repository.ClinicAdministratorRepository;
import hermanos.Centro.Clinico.service.interfaces.ClinicAdministratorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicAdministratorService implements ClinicAdministratorServiceInterface {

    @Autowired
    private ClinicAdministratorRepository clinicAdministratorRepository;

    @Override
    public ClinicAdministrator findByiD(long id){
        return  clinicAdministratorRepository.findById(id);
    }

    @Override
    public ClinicAdministrator save(ClinicAdministrator clinicAdministrator){
        return clinicAdministratorRepository.save(clinicAdministrator);
    }

    @Override
    public ClinicAdministrator findByEmail(String email) { return clinicAdministratorRepository.findByEmail(email); }

    public List<ClinicAdministrator> findAll() {
        List<ClinicAdministrator> result = clinicAdministratorRepository.findAll();
        return result;
    }

}
