package semana1.curso3.coursera.restApi.model;

public class LikePhotoResponse {

    private String id;
    private String idDevice;
    private String idUserInstagram;
    private String idPhotoInstagram;

    public LikePhotoResponse() {

    }

    public LikePhotoResponse(String id, String idDevice, String idUserInstagram, String idPhotoInstagram) {
        this.id = id;
        this.idDevice = idDevice;
        this.idUserInstagram = idUserInstagram;
        this.idPhotoInstagram = idPhotoInstagram;
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

    public String getIdPhotoInstagram() {
        return idPhotoInstagram;
    }

    public void setIdPhotoInstagram(String idPhotoInstagram) {
        this.idPhotoInstagram = idPhotoInstagram;
    }

}