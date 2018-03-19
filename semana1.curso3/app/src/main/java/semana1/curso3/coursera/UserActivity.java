package semana1.curso3.coursera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import semana1.curso3.coursera.adapter.PetProfileAdapter;
import semana1.curso3.coursera.pojo.PetProfile;
import semana1.curso3.coursera.pojo.User;
import semana1.curso3.coursera.restApi.Endpoints;
import semana1.curso3.coursera.restApi.adapter.RestApiAdapter;
import semana1.curso3.coursera.restApi.model.PetProfileResponse;
import semana1.curso3.coursera.utils.SharedPreferencesManager;

public class UserActivity extends AppCompatActivity {

    private CircularImageView civ_photo;
    private TextView tv_name;
    private RecyclerView rv_pet_profile;

    private ArrayList<PetProfile> petProfiles;
    private PetProfileAdapter petProfileAdapter;

    private String id_usuario_notificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar actionbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(actionbar);
        civ_photo = (CircularImageView) findViewById(R.id.civ_photo);
        tv_name = (TextView) findViewById(R.id.tv_name);
        rv_pet_profile = (RecyclerView) findViewById(R.id.rv_pet_profile);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(UserActivity.this, 3);
        rv_pet_profile.setLayoutManager(gridLayoutManager);
        petProfiles = new ArrayList<>();
        petProfileAdapter = new PetProfileAdapter(UserActivity.this, petProfiles);
        rv_pet_profile.setAdapter(petProfileAdapter);
        Bundle bundle = getIntent().getExtras();
        id_usuario_notificacion = bundle.getString("id_usuario_notificacion");
        getPetProfile();
    }

    public void getPetProfile() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.buildGsonDeserializerMediaRecent();
        Endpoints endpoints = restApiAdapter.establishConnectionRestApiInstagram(gson);
        Call<PetProfileResponse> petProfileResponseCall = endpoints.getRecentMedia(id_usuario_notificacion);
        petProfileResponseCall.enqueue(new Callback<PetProfileResponse>() {
            @Override
            public void onResponse(Call<PetProfileResponse> call, Response<PetProfileResponse> response) {
                PetProfileResponse petProfileResponse = response.body();
                User user = petProfileResponse.getUser();
                Picasso.with(UserActivity.this).load(user.getPhoto()).placeholder(R.drawable.image_not_found).into(civ_photo);
                tv_name.setText(user.getName());
                petProfiles.addAll(petProfileResponse.getPetProfiles());
                petProfileAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PetProfileResponse> call, Throwable throwable) {
                Toast.makeText(UserActivity.this, R.string.unexpected_error_occured, Toast.LENGTH_SHORT).show();
            }
        });
    }

}