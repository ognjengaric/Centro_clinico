package hermanos.Centro.Clinico.model;

import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ClinicalCenterAdministrator extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clinical_center_administrator_id", nullable = false, unique = true)
    private long id;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Person> persons = new ArrayList<>();

    public ClinicalCenterAdministrator() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Person> getPerson() {
        return persons;
    }

    public void setPerson(List<Person> persons) {
        this.persons = persons;
    }
}
