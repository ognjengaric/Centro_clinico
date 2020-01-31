package hermanos.Centro.Clinico.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@DiscriminatorValue(value = "MEDICINE")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id", nullable = false, unique = false)
    private long id;

    @Column(nullable = false, unique = false)
    private String code;

    @Column(nullable = false)
    private String description;

    //@ManyToMany(cascade=CascadeType.ALL)
    //@JoinTable(name="medicine_list_prescription",joinColumns=@JoinColumn(name="medicine_id"),inverseJoinColumns=@JoinColumn(name="prescription_id") )
    //private List<Prescription> medicine_list_prescription;

    public Medicine() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    //public void setPrescription(Prescription p){ this.medicine_list_prescription.add(p);}
}
