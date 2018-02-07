package semana1.curso3.coursera.fragment;

import java.util.ArrayList;

import semana1.curso3.coursera.adapter.PetProfileAdapter;
import semana1.curso3.coursera.pojo.PetProfile;
import semana1.curso3.coursera.pojo.User;

public interface IPetProfileFragmentView {

    void generateGridLayoutManager();

    PetProfileAdapter createPetProfileAdapter(ArrayList<PetProfile> petProfiles);

    void initializePetProfileAdapter(PetProfileAdapter petProfileAdapter);

    void setProfile(User user);

}
