package semana1.curso3.coursera.fragment;

import java.util.ArrayList;

import semana1.curso3.coursera.adapter.PetAdapter;
import semana1.curso3.coursera.pojo.Pet;

public interface IHomeFragmentView {

    void generateLinearLayoutManagerVertical();

    PetAdapter createPetAdapter(ArrayList<Pet> pets);

    void initializePetAdapter(PetAdapter petAdapter);

}
