package hermanos.Centro.Clinico.dto;

import hermanos.Centro.Clinico.model.Patient;

public class PatientDTO {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String street;
    private String streetNumber;
    private String city;
    private String postcode;
    private String country;
    private String phoneNumber;
    private String socialSecurityNumber;

    public  PatientDTO(){

    }

    public PatientDTO(Patient p){
        this.name = p.getName();
        this.surname = p.getSurname();
        this.email = p.getEmail();
        this.phoneNumber = p.getPhoneNumber();
        this.street = p.getAddress().getStreet();
        this.streetNumber = p.getAddress().getNumber();
        this.city = p.getAddress().getCity();
        this.postcode = p.getAddress().getPostcode();
        this.country = p.getAddress().getCountry();
        this.socialSecurityNumber = p.getSocialSecurityNumber();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }
}
