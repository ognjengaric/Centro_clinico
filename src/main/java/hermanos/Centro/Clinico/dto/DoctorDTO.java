package hermanos.Centro.Clinico.dto;

import hermanos.Centro.Clinico.model.CheckupType;
import hermanos.Centro.Clinico.model.Doctor;
import hermanos.Centro.Clinico.model.StartEndTime;

public class DoctorDTO {
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
    private String avgRating;

    public DoctorDTO(){

    }

    public DoctorDTO(Doctor d){
        this.name = d.getName();
        this.surname = d.getSurname();
        this.email = d.getEmail();
        this.phoneNumber = d.getPhoneNumber();
        this.street = d.getAddress().getStreet();
        this.streetNumber = d.getAddress().getNumber();
        this.city = d.getAddress().getCity();
        this.postcode = d.getAddress().getPostcode();
        this.country = d.getAddress().getCountry();
        this.avgRating = Float.toString(d.calculateAverageRating());
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

    public String getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(String avgRating) {
        this.avgRating = avgRating;
    }
}
