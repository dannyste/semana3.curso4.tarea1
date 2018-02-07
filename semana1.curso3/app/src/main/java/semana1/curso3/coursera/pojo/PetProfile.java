package semana1.curso3.coursera.pojo;

public class PetProfile {

    private String id;
    private String photo;
    private String name;
    private int rating;

    public PetProfile() {

    }

    public PetProfile(String photo, String name, int rating) {
        this.photo = photo;
        this.name = name;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
