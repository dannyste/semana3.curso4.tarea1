package semana1.curso3.coursera.presenter;

import android.content.Context;

import java.util.ArrayList;

import semana1.curso3.coursera.fragment.IHomeFragmentView;
import semana1.curso3.coursera.pojo.Pet;
import semana1.curso3.coursera.pojo.PetConstructor;

public class HomeFragmentPresenter implements IHomeFragmentPresenter {

    private Context context;
    private IHomeFragmentView iHomeFragmentView;

    private PetConstructor petConstructor;
    private ArrayList<Pet> pets;

    public HomeFragmentPresenter(Context context, IHomeFragmentView iHomeFragmentView) {
        this.context = context;
        this.iHomeFragmentView = iHomeFragmentView;
        getPets();
    }

    @Override
    public void getPets() {
        petConstructor = new PetConstructor(context);
        pets = petConstructor.getPets();
        showPets();
    }

    @Override
    public void showPets() {
        iHomeFragmentView.initializePetAdapter(iHomeFragmentView.createPetAdapter(pets));
        iHomeFragmentView.generateLinearLayoutManagerVertical();
    }

}