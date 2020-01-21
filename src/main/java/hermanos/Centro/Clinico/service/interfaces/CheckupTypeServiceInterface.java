package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.CheckupType;

import java.util.List;

public interface CheckupTypeServiceInterface {

    CheckupType findByName(String name);
    CheckupType save(CheckupType checkupType);
    void deleteById(long id);
    List<CheckupType> findAll();
}
