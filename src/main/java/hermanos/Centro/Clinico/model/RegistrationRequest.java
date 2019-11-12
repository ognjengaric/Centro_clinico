package hermanos.Centro.Clinico.model;


import javax.persistence.*;

public class RegistrationRequest {

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Person person;

    public RegistrationRequest() {
    }

    public Person getPerson(){
        return person;
    }

    public void setPerson(Person person){
        this.person = person;
    }
}
