package hermanos.Centro.Clinico.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Clinic {

    @Column(nullable = false)
    private String name;

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
