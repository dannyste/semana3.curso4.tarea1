package semana1.curso3.coursera.presenter;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import semana1.curso3.coursera.R;
import semana1.curso3.coursera.fragment.IPetProfileFragmentView;
import semana1.curso3.coursera.pojo.PetProfile;
import semana1.curso3.coursera.pojo.User;
import semana1.curso3.coursera.restApi.Endpoints;
import semana1.curso3.coursera.restApi.adapter.RestApiAdapter;
import semana1.curso3.coursera.restApi.model.PetProfileResponse;
import semana1.curso3.coursera.utils.SharedPreferencesManager;

public class PetProfileFragmentPresenter implements IPetProfileFragmentPresenter {

    private Context context;
    private IPetProfileFragmentView iPetProfileFragmentView;

    private User user;
    private ArrayList<PetProfile> petProfiles;

    public PetProfileFragmentPresenter(Context context, IPetProfileFragmentView iPetProfileFragmentView) {
        this.context = context;
        this.iPetProfileFragmentView = iPetProfileFragmentView;
        getPetProfile();
    }

    @Override
    public void getPetProfile() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.buildGsonDeserializerMediaRecent();
        Endpoints endpoints = restApiAdapter.establishConnectionRestApiInstagram(gson);
        Call<PetProfileResponse> petProfileResponseCall = endpoints.getRecentMedia(SharedPreferencesManager.getUserId(context));
        petProfileResponseCall.enqueue(new Callback<PetProfileResponse>() {
            @Override
            public void onResponse(Call<PetProfileResponse> call, Response<PetProfileResponse> response) {
                PetProfileResponse petProfileResponse = response.body();
                user = petProfileResponse.getUser();
                petProfiles = petProfileResponse.getPetProfiles();
                showData();
            }

            @Override
            public void onFailure(Call<PetProfileResponse> call, Throwable throwable) {
                Toast.makeText(context, R.string.unexpected_error_occured, Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public void showData() {
        iPetProfileFragmentView.setProfile(user);
        iPetProfileFragmentView.initializePetProfileAdapter(iPetProfileFragmentView.createPetProfileAdapter(petProfiles));
        iPetProfileFragmentView.generateGridLayoutManager();
    }

}