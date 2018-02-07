package semana1.curso3.coursera.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import semana1.curso3.coursera.R;
import semana1.curso3.coursera.adapter.PetAdapter;
import semana1.curso3.coursera.adapter.PetProfileAdapter;
import semana1.curso3.coursera.pojo.Pet;
import semana1.curso3.coursera.pojo.PetProfile;
import semana1.curso3.coursera.pojo.User;
import semana1.curso3.coursera.presenter.IHomeFragmentPresenter;
import semana1.curso3.coursera.presenter.IPetProfileFragmentPresenter;
import semana1.curso3.coursera.presenter.PetProfileFragmentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetProfileFragment extends Fragment implements IPetProfileFragmentView {

    private CircularImageView civ_photo;
    private TextView tv_name;
    private RecyclerView rv_pet_profile;

    private IPetProfileFragmentPresenter iPetProfileFragmentPresenter;

    public PetProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pet_profile, container, false);
        civ_photo = (CircularImageView) view.findViewById(R.id.civ_photo);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        rv_pet_profile = (RecyclerView) view.findViewById(R.id.rv_pet_profile);
        iPetProfileFragmentPresenter = new PetProfileFragmentPresenter(getActivity(), this);
        return view;
    }

    @Override
    public void generateGridLayoutManager() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        rv_pet_profile.setLayoutManager(gridLayoutManager);
    }

    @Override
    public PetProfileAdapter createPetProfileAdapter(ArrayList<PetProfile> petProfiles) {
        PetProfileAdapter petProfileAdapter = new PetProfileAdapter(getActivity(), petProfiles);
        return petProfileAdapter;
    }

    @Override
    public void initializePetProfileAdapter(PetProfileAdapter petProfileAdapter) {
        rv_pet_profile.setAdapter(petProfileAdapter);
    }

    @Override
    public void setProfile(User user) {
        Picasso.with(getActivity()).load(user.getPhoto()).placeholder(R.drawable.image_not_found).into(civ_photo);
        tv_name.setText(user.getName());
    }

}