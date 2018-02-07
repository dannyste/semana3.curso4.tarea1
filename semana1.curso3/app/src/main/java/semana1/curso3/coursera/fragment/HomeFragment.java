package semana1.curso3.coursera.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import semana1.curso3.coursera.R;
import semana1.curso3.coursera.adapter.PetAdapter;
import semana1.curso3.coursera.pojo.Pet;
import semana1.curso3.coursera.pojo.PetConstructor;
import semana1.curso3.coursera.presenter.HomeFragmentPresenter;
import semana1.curso3.coursera.presenter.IHomeFragmentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements IHomeFragmentView {

    private RecyclerView rv_pet;

    private IHomeFragmentPresenter iHomeFragmentPresenter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rv_pet = (RecyclerView) view.findViewById(R.id.rv_pet);
        iHomeFragmentPresenter = new HomeFragmentPresenter(getActivity(), this );
        return view;
    }

    @Override
    public void generateLinearLayoutManagerVertical() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_pet.setLayoutManager(linearLayoutManager);
    }

    @Override
    public PetAdapter createPetAdapter(ArrayList<Pet> pets) {
        PetAdapter petAdapter = new PetAdapter(getActivity(), pets);
        return petAdapter;
    }

    @Override
    public void initializePetAdapter(PetAdapter petAdapter) {
        rv_pet.setAdapter(petAdapter);
    }

}