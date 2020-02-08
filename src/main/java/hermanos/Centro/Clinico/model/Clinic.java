package hermanos.Centro.Clinico.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clinic_id", nullable = false, unique = true)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "admin_clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ClinicAdministrator> admins = new ArrayList<>();

    @OneToMany(mappedBy = "doctor_clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Doctor> doctors = new ArrayList<>();

    @OneToMany(mappedBy = "checkuptypes_clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CheckupType> checkupTypes = new ArrayList<>();

    @OneToMany(mappedBy = "rooms_clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PredefinedCheckup> predefinedCheckups = new ArrayList<>();

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Checkup> checkups = new ArrayList<>();

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "address", referencedColumnName = "address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "clinic")
    private List<PatientClinicRating> beingRated;

    @Version
    private Long version;

    public Clinic() {
    }

    public float calculateAverageRating(){
        int sum = 0;

        for(PatientClinicRating patientClinicRating : beingRated){
            sum += patientClinicRating.getRating();
        }

        if(beingRated.size() != 0)
            return sum/beingRated.size();
        else
            return 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ClinicAdministrator> getAdmins() {
        return admins;
    }

    public void setAdmins(List<ClinicAdministrator> admins) {
        this.admins = admins;
    }

    public List<CheckupType> getCheckupTypes() {
        return checkupTypes;
    }

    public void setCheckupTypes(List<CheckupType> checkupTypes) {
        this.checkupTypes = checkupTypes;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<PredefinedCheckup> getPredefinedCheckups() {
        return predefinedCheckups;
    }

    public void setPredefinedCheckups(List<PredefinedCheckup> predefinedCheckups) {
        this.predefinedCheckups = predefinedCheckups;
    }

    public List<Checkup> getCheckups() {
        return checkups;
    }

    public void setCheckups(List<Checkup> checkups) {
        this.checkups = checkups;
    }

    public List<PatientClinicRating> getBeingRated() {
        return beingRated;
    }

    public void setBeingRated(List<PatientClinicRating> beingRated) {
        this.beingRated = beingRated;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
