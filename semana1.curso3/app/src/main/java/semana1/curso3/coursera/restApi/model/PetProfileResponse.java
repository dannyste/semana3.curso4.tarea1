package semana1.curso3.coursera.restApi.model;

import java.util.ArrayList;

import semana1.curso3.coursera.pojo.PetProfile;
import semana1.curso3.coursera.pojo.User;

public class PetProfileResponse {

    private User user;
    private ArrayList<PetProfile> petProfiles;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<PetProfile> getPetProfiles() {
        return petProfiles;
    }

    public void setPetProfiles(ArrayList<PetProfile> petProfiles) {
        this.petProfiles = petProfiles;
    }
}