package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Prescription;

import java.util.List;

public interface PrescriptionServiceInterface {

    public Prescription findById(long id);
    public Prescription save(Prescription prescription);
    public List<Prescription> findAll();
}
