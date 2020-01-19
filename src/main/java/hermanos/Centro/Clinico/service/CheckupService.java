package hermanos.Centro.Clinico.service;

import hermanos.Centro.Clinico.model.Checkup;
import hermanos.Centro.Clinico.repository.CheckupRepository;
import hermanos.Centro.Clinico.service.interfaces.CheckupServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

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
}
