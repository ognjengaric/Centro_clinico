package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Medicine;

import java.util.List;

public interface MedicineServiceInterface {

    public Medicine findById(long id);
    public Medicine save(Medicine medicine);
    public List<Medicine> findAll();
}
