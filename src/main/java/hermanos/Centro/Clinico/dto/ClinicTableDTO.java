package hermanos.Centro.Clinico.dto;

import hermanos.Centro.Clinico.model.Address;
import hermanos.Centro.Clinico.model.Clinic;

public class ClinicTableDTO {
    private Long id;
    private String name;
    private String description;
    private String fullAddress;

    public  ClinicTableDTO(){

    }

    public ClinicTableDTO(String name, String description, Address address) {
        this.name = name;
        this.description = description;
        this.fullAddress = AddressToString(address);
    }

    public ClinicTableDTO(Clinic clinic){
        this.name = clinic.getName();
        this.description = clinic.getDescription();
        this.fullAddress = AddressToString(clinic.getAddress());
        this.id = clinic.getId();
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    private static String AddressToString(Address address){
        String string;

        string = address.getStreet() + " " + address.getNumber() + ", " + address.getPostcode() + " " + address.getCity()
                + ", " + address.getCountry();

        return string;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
