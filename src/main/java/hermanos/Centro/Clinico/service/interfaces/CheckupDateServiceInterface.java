package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Checkup;
import hermanos.Centro.Clinico.model.CheckupDate;

import java.util.List;

public interface CheckupDateServiceInterface {

    CheckupDate findById(long id);
    CheckupDate save(CheckupDate checkupDate);
    void deleteById(long id);

    boolean isValid(CheckupDate checkup);
}
