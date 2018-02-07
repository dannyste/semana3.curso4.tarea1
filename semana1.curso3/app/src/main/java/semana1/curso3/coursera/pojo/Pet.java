package semana1.curso3.coursera.pojo;

public class Pet {

    private int id;
    private int photo;
    private String name;
    private int rating;

    public Pet() {

    }

    public Pet(int photo, String name, int rating) {
        this.photo = photo;
        this.name = name;
        this.rating = rating;
    }

    public Pet(int photo, int rating) {
        this.photo = photo;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
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