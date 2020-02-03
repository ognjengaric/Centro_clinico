package hermanos.Centro.Clinico.service;


import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.model.PredefinedCheckup;
import hermanos.Centro.Clinico.repository.ClinicRepository;
import hermanos.Centro.Clinico.service.interfaces.ClinicServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClinicService implements ClinicServiceInterface {

    @Autowired
    private ClinicRepository clinicRepository;

    @Override
    public Clinic findById(long id){
        return  clinicRepository.findById(id);
    }

    @Override
    public Clinic save(Clinic clinic){
        return clinicRepository.save(clinic);
    }

    @Override
    public void deleteExpiredPredefinedCheckups(Clinic clinic) {
        List<PredefinedCheckup> temp = new ArrayList<>(clinic.getPredefinedCheckups());

        for(PredefinedCheckup checkup : temp){
            if(checkup.getDate().compareTo(LocalDate.now()) <= 0 && checkup.getStartEnd().getStartTime().
                    compareTo(LocalTime.now()) <= 0){
                clinic.getPredefinedCheckups().remove(checkup);
            }
        }
        clinicRepository.save(clinic);
    }

    public List<Clinic> findAll() {
        List<Clinic> result = clinicRepository.findAll();
        return result;
    }
}

