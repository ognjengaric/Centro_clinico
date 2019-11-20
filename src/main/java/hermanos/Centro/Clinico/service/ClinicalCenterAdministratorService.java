package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.model.ClinicalCenterAdministrator;
import hermanos.Centro.Clinico.repository.ClinicalCenterAdministratorRepository;
import hermanos.Centro.Clinico.service.interfaces.ClinicalCenterAdministratorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicalCenterAdministratorService implements ClinicalCenterAdministratorServiceInterface {

    @Autowired
    private ClinicalCenterAdministratorRepository clinicalCenterAdministratorRepository;

    @Override
    public ClinicalCenterAdministrator findByiD(long id){
        return  clinicalCenterAdministratorRepository.findById(id);
    }

    @Override
    public ClinicalCenterAdministrator save(ClinicalCenterAdministrator clinicalCenterAdministrator){
        return clinicalCenterAdministratorRepository.save(clinicalCenterAdministrator);
    }
    public List<ClinicalCenterAdministrator> findAll() {
        List<ClinicalCenterAdministrator> result = clinicalCenterAdministratorRepository.findAll();
        return result;
    }

}
