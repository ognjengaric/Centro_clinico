package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.CheckupDate;

public interface CheckupDateServiceInterface {

    public CheckupDate findByDate(String date);
    public CheckupDate save(CheckupDate checkupDate);
}
