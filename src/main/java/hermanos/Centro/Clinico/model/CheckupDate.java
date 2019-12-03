package hermanos.Centro.Clinico.model;


import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "CHECKUP_DATE")
public class CheckupDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkupdate_id", nullable = false, unique = true)
    private long id;


    @ManyToOne
    @JoinColumn(name = "checkupdates_clinic", referencedColumnName = "clinic_id", nullable = true)
    private Clinic checkupdates_clinic;

    @Column(nullable = false, unique = true)
    private String date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CheckupDate(){
        super();
    }

    public Clinic getClinic() {
        return checkupdates_clinic;
    }

    public void setClinic(Clinic clinic) {
        this.checkupdates_clinic = clinic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
