package hermanos.Centro.Clinico.dto;

import hermanos.Centro.Clinico.model.Doctor;
import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.model.StartEndDate;
import hermanos.Centro.Clinico.model.StartEndTime;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BusinessReportDTO {

    private List<DocRatingDTO> doctors = new ArrayList<>();
    private long rating;
    private HashMap<String, String> chart = new HashMap<>();

    public BusinessReportDTO(Clinic c) {
        this.rating = 0;
        int i = 0;
        for(Doctor d: c.getDoctors()){
            this.doctors.add(new DocRatingDTO(d));
            this.rating += d.calculateAverageRating();
            i++;
        }
        this.rating = this.rating/i;
    }

    public BusinessReportDTO(){}

    public List<DocRatingDTO> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DocRatingDTO> doctors) {
        this.doctors = doctors;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public HashMap<String, String> getChart() {
        return chart;
    }

    public void setChart(HashMap<String, String> chart) {
        this.chart = chart;
    }
}
