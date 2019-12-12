package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.CheckupDate;
import java.util.List;

public interface CheckupDateServiceInterface {

    public CheckupDate findByDate(String date);
    public CheckupDate save(CheckupDate checkupDate);
    public void deleteById(long id);
}
