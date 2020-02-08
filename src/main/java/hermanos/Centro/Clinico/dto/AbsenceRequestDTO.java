package hermanos.Centro.Clinico.dto;

import hermanos.Centro.Clinico.model.AbsenceRequest;
import hermanos.Centro.Clinico.model.Patient;
import hermanos.Centro.Clinico.service.interfaces.DoctorServiceInterface;
import hermanos.Centro.Clinico.service.interfaces.NurseServiceInterface;
//import jdk.vm.ci.meta.Local;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class AbsenceRequestDTO {

    private long id;
    private String startDate;
    private String endDate;
    private String type;
    private String reason;
    private boolean approved;
    private String person_name;
    private long person_id;
    private String person_role;


    public AbsenceRequestDTO() {
    }

    public AbsenceRequestDTO(AbsenceRequest ar, String role) {
        this.id = ar.getId();
        this.startDate = ar.getPeriod().getStartDate().toString();
        this.endDate = ar.getPeriod().getEndDate().toString();
        this.type = ar.getType();
        this.reason = ar.getReason();
        this.approved = ar.isApproved();
        this.person_name = ar.getPerson().getName() + " " + ar.getPerson().getSurname();
        this.person_id = ar.getPerson().getId();
        this.person_role = role;
    }

    public AbsenceRequestDTO(AbsenceRequest ar) {
        this.id = ar.getId();
        this.startDate = ar.getPeriod().getStartDate().toString();
        this.endDate = ar.getPeriod().getEndDate().toString();
        this.type = ar.getType();
        this.reason = ar.getReason();
        this.approved = ar.isApproved();
        this.person_name = ar.getPerson().getName() + " " + ar.getPerson().getSurname();
        this.person_id = ar.getPerson().getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(long person_id) {
        this.person_id = person_id;
    }

    public String getPerson_role() {
        return person_role;
    }

    public void setPerson_role(String person_role) {
        this.person_role = person_role;
    }
}
