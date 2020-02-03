package hermanos.Centro.Clinico.service;


import hermanos.Centro.Clinico.model.Doctor;
import hermanos.Centro.Clinico.repository.DoctorRepository;
import hermanos.Centro.Clinico.service.interfaces.DoctorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService implements DoctorServiceInterface {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor findById(long id){
        return  doctorRepository.findById(id);
    }

    @Override
    public Doctor findByEmail(String email) { return doctorRepository.findByEmail(email);}

    @Override
    public Doctor save(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteById(long id) {doctorRepository.deleteById(id);}

    public List<Doctor> findAll() {
        List<Doctor> result = doctorRepository.findAll();
        return result;
    }
}

