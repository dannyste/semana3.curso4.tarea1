package semana1.curso3.coursera.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import semana1.curso3.coursera.R;
import semana1.curso3.coursera.pojo.Like;
import semana1.curso3.coursera.pojo.PetProfile;
import semana1.curso3.coursera.restApi.Endpoints;
import semana1.curso3.coursera.restApi.adapter.RestApiAdapter;
import semana1.curso3.coursera.restApi.model.LikePhotoResponse;
import semana1.curso3.coursera.restApi.model.LikeResponse;
import semana1.curso3.coursera.restApi.model.UserResponse;
import semana1.curso3.coursera.utils.SharedPreferencesManager;

public class PetProfileAdapter extends RecyclerView.Adapter<PetProfileAdapter.PetProfileViewHolder> {

    private Activity activity;
    private ArrayList<PetProfile> petProfiles;

    public PetProfileAdapter(Activity activity, ArrayList<PetProfile> petProfiles) {
        this.activity = activity;
        this.petProfiles = petProfiles;
    }

    @Override
    public PetProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pet_profile, parent, false);
        return new PetProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PetProfileViewHolder petProfileViewHolder, int position) {
        final PetProfile petProfile = petProfiles.get(position);
        Picasso.with(activity).load(petProfile.getPhoto()).placeholder(R.drawable.image_not_found).into(petProfileViewHolder.iv_photo);
        petProfileViewHolder.tv_rating.setText(String.valueOf(petProfile.getRating()));
        petProfileViewHolder.iv_bone_yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                petProfileViewHolder.tv_rating.setText(String.valueOf(Integer.valueOf(petProfileViewHolder.tv_rating.getText().toString()) + 1));
                postLikeMedia(petProfile);
            }
        });
    }

    public void postLikeMedia(final PetProfile petProfile) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.buildGsonDeserializerLikeMedia();
        Endpoints endpoints = restApiAdapter.establishConnectionRestApiInstagram(gson);
        Call<LikeResponse> likeResponseCall = endpoints.postLikeMedia(petProfile.getIdPhoto());
        likeResponseCall.enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                LikeResponse likeResponse = response.body();
                Like like = likeResponse.getLike();
                if (like.getCode() == 200) {
                    postRegistrarLikeFoto(petProfile);
                }
                else {
                    Toast.makeText(activity, R.string.unexpected_error_occured, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable throwable) {
                Toast.makeText(activity, R.string.unexpected_error_occured, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void postRegistrarLikeFoto(PetProfile petProfile) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Endpoints endpoints = restApiAdapter.establishConnectionRestApiServer();
        Call<LikePhotoResponse> likePhotoResponseCall = endpoints.postRegistrarLikeFoto(FirebaseInstanceId.getInstance().getToken(), SharedPreferencesManager.getUserId(activity), petProfile.getIdPhoto());
        likePhotoResponseCall.enqueue(new Callback<LikePhotoResponse>() {
            @Override
            public void onResponse(Call<LikePhotoResponse> call, Response<LikePhotoResponse> response) {
                LikePhotoResponse likePhotoResponse = response.body();
                getLikeFoto();
            }

            @Override
            public void onFailure(Call<LikePhotoResponse> call, Throwable throwable) {
                Toast.makeText(activity, R.string.unexpected_error_occured, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getLikeFoto() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Endpoints endpoints = restApiAdapter.establishConnectionRestApiServer();
        Call<UserResponse> userResponseCall = endpoints.getLikeFoto("-L5GZ8kMCydEg63g8BaF", "coursera.danny");
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable throwable) {
                Toast.makeText(activity, R.string.unexpected_error_occured, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return petProfiles.size();
    }

    static class PetProfileViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_photo;
        private TextView tv_rating;
        private ImageView iv_bone_yellow;

        PetProfileViewHolder(View view) {
            super(view);
            iv_photo = (ImageView) view.findViewById(R.id.iv_photo);
            tv_rating = (TextView) view.findViewById(R.id.tv_rating);
            iv_bone_yellow = (ImageView) view.findViewById(R.id.iv_bone_yellow);
        }

    }

}