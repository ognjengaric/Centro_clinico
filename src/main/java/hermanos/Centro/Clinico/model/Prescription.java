package hermanos.Centro.Clinico.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class Prescription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_id", nullable = false, unique = true)
    private long id;

    @ManyToMany
    @JoinTable(name="medicine_list_prescription",joinColumns=@JoinColumn(name="prescription_id"),inverseJoinColumns=@JoinColumn(name="medicine_id") )
    private List<Medicine> medicine_list;

    @Column(nullable = false)
    private boolean certified;

    @ManyToOne
    @JoinColumn(name="byWho", referencedColumnName = "person_id")
    private Person byWho;

    public Prescription() {

    }

    public List<Medicine> getMedicine_list() {
        return medicine_list;
    }
    public Medicine getMedicine(Medicine m){
        for(Medicine med : medicine_list){
            if(med.getId().equals(m.getId())){
                return med;
            }
        }
        return null;
    }

    public void setMedicine_ist(List<Medicine> medicine_list) {
        this.medicine_list = medicine_list;
    }

    public boolean isCertified() {
        return certified;
    }

    public void setCertified(boolean certified) {
        this.certified = certified;
    }

    public Person getByWho() {
        return byWho;
    }

    public void setByWho(Person byWho) {
        this.byWho = byWho;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
