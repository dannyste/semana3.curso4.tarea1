package semana1.curso3.coursera.pojo;

public class User {

    private String id;
    private String username;
    private String photo;
    private String name;

    public User() {

    }

    public User(String id, String username, String photo, String name) {
        this.id = id;
        this.username = username;
        this.photo = photo;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

}