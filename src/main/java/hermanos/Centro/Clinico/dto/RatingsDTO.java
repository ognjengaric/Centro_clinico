package hermanos.Centro.Clinico.dto;

import java.util.ArrayList;
import java.util.List;

public class RatingsDTO {

    List<RatingDTO> clinicRatings = new ArrayList<>();

    List<RatingDTO> doctorRatings = new ArrayList<>();

    public RatingsDTO(){}

    public List<RatingDTO> getClinicRatings() {
        return clinicRatings;
    }

    public void setClinicRatings(List<RatingDTO> clinicRatings) {
        this.clinicRatings = clinicRatings;
    }

    public List<RatingDTO> getDoctorRatings() {
        return doctorRatings;
    }

    public void setDoctorRatings(List<RatingDTO> doctorRatings) {
        this.doctorRatings = doctorRatings;
    }
}
