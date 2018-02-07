package semana1.curso3.coursera.restApi.model;

public class UserResponse {

    private String id;
    private String idDevice;
    private String idUserInstagram;

    public UserResponse() {

    }

    public UserResponse(String id, String idDevice, String idUserInstagram) {
        this.id = id;
        this.idDevice = idDevice;
        this.idUserInstagram = idUserInstagram;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public String getIdUserInstagram() {
        return idUserInstagram;
    }

    public void setIdUserInstagram(String idUserInstagram) {
        this.idUserInstagram = idUserInstagram;
    }

}