package hermanos.Centro.Clinico.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Clinic {

    @Column(name = "clinic_id", nullable=false, unique = true)
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ClinicAdministrator> admins = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "address", referencedColumnName = "address_id", nullable = false)
    private Address address;

    public Clinic() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
