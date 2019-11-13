package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.model.ClinicalCenterAdministrator;
import hermanos.Centro.Clinico.repository.ClinicalCenterAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicalCenterAdministratorService {

    @Autowired
    private ClinicalCenterAdministratorRepository clinicalCenterAdministratorRepository;

    public ClinicalCenterAdministrator findByiD(long id){
        return  clinicalCenterAdministratorRepository.findById(id);
    }

    public ClinicalCenterAdministrator save(ClinicalCenterAdministrator clinicalCenterAdministrator){
        return clinicalCenterAdministratorRepository.save(clinicalCenterAdministrator);
    }

}
