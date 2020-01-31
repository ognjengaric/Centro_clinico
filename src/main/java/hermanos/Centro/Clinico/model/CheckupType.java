package hermanos.Centro.Clinico.model;


import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "CHECKUP_TYPE")
public class CheckupType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkuptype_id", nullable = false, unique = true)
    private long id;

    @Column
    private Long duration;

    @Column
    private int price;

    @ManyToOne
    @JoinColumn(name = "checkuptypes_clinic", referencedColumnName = "clinic_id", nullable = true)
    private Clinic checkuptypes_clinic;

    @Column(nullable = false, unique = true)
    private String name;


    public CheckupType(){
        super();
    }

    public Clinic getClinic() {
        return checkuptypes_clinic;
    }

    public void setClinic(Clinic clinic) {
        this.checkuptypes_clinic = clinic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Clinic getCheckuptypes_clinic() {
        return checkuptypes_clinic;
    }

    public void setCheckuptypes_clinic(Clinic checkuptypes_clinic) {
        this.checkuptypes_clinic = checkuptypes_clinic;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
