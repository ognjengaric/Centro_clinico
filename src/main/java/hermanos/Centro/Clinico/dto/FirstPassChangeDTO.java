package hermanos.Centro.Clinico.dto;

import hermanos.Centro.Clinico.model.Doctor;

public class FirstPassChangeDTO {
    private String email;
    private String password;

    public FirstPassChangeDTO() {}

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
}
