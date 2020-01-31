package hermanos.Centro.Clinico.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnosis_id", nullable = false, unique = false)
    private long id;

    @Column(nullable = true, unique = false)
    private String code;

    @Column(nullable = true)
    private String description;

    //@OneToMany(cascade=CascadeType.ALL)
    //@JoinTable(name="checkup_diag",joinColumns=@JoinColumn(name="diagnosis_id"),inverseJoinColumns=@JoinColumn(name="checkup_id"))
    //private Collection<Checkup> checkup_diag = new ArrayList<>();


    public Diagnosis() {
    }

    //public Collection<Checkup> getCheckup_diag() {
      //  return checkup_diag;
    //}

    //public void setCheckup_diag(Checkup checkup_diag) {
      //  this.checkup_diag.add(checkup_diag);
    //}

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


}
