package hermanos.Centro.Clinico.dto;

public class RatingDTO {
    private String nameOfRated;
    private String  id;
    private int rating;

    public RatingDTO(){}

    public RatingDTO(String nameOfRated, int rating, String  id) {
        this.nameOfRated = nameOfRated;
        this.rating = rating;
        this.id = id;
    }

    public String getNameOfRated() {
        return nameOfRated;
    }

    public void setNameOfRated(String nameOfRated) {
        this.nameOfRated = nameOfRated;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
