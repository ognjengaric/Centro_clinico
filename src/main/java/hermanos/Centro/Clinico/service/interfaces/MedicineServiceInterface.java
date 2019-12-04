package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Medicine;

public interface MedicineServiceInterface {

    public Medicine findById(long id);
    public Medicine save(Medicine medicine);
}
