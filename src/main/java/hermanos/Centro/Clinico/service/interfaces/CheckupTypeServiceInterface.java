package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.CheckupType;

public interface CheckupTypeServiceInterface {

    public CheckupType findByName(String name);
    public CheckupType save(CheckupType checkupType);
}
