package hermanos.Centro.Clinico.model;

import org.springframework.context.annotation.Lazy;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class ClinicalCenterAdministrator extends Person{

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RegistrationRequest> registrationRequests = new ArrayList<>();

    public ClinicalCenterAdministrator() {
    }

    public List<RegistrationRequest> getRegistrationRequests() {
        return registrationRequests;
    }

    public void setRegistrationRequests(List<RegistrationRequest> registrationRequests) {
        this.registrationRequests = registrationRequests;
    }
}
