package semana1.curso3.coursera.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import semana1.curso3.coursera.R;
import semana1.curso3.coursera.restApi.Endpoints;
import semana1.curso3.coursera.restApi.adapter.RestApiAdapter;
import semana1.curso3.coursera.restApi.model.GetRelationshipResponse;
import semana1.curso3.coursera.restApi.model.PostRelationshipResponse;

public class FollowUnfollowBroadcastReceiver extends BroadcastReceiver {

    private Context context;

    private String id_usuario_notificacion;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        String ACTION_KEY = "FOLLOW_UNFOLLOW";
        String action = intent.getAction();
        if (ACTION_KEY.equals(action)){
            id_usuario_notificacion = intent.getStringExtra("id_usuario_notificacion");
            getRelationshipUser();
        }

    }

    private void getRelationshipUser() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.buildGsonDeserializerGetRelationship();
        Endpoints endpoints = restApiAdapter.establishConnectionRestApiInstagram(gson);
        Call<GetRelationshipResponse> getRelationshipResponseCall = endpoints.getRelationshipUser(id_usuario_notificacion);
        getRelationshipResponseCall.enqueue(new Callback<GetRelationshipResponse>() {
            @Override
            public void onResponse(Call<GetRelationshipResponse> call, Response<GetRelationshipResponse> response) {
                GetRelationshipResponse getRelationshipResponse = response.body();
                postRelationshipUser(getRelationshipResponse.getRelationship().getOutgoing_status().equalsIgnoreCase("none") ? "follow" : "unfollow");
            }

            @Override
            public void onFailure(Call<GetRelationshipResponse> call, Throwable throwable) {
                Toast.makeText(context, R.string.unexpected_error_occured, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postRelationshipUser(final String action) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.buildGsonDeserializerPostRelationship();
        Endpoints endpoints = restApiAdapter.establishConnectionRestApiInstagram(gson);
        Call<PostRelationshipResponse> relationshipResponseCall = endpoints.postRelationshipUser(id_usuario_notificacion, action);
        relationshipResponseCall.enqueue(new Callback<PostRelationshipResponse>() {
            @Override
            public void onResponse(Call<PostRelationshipResponse> call, Response<PostRelationshipResponse> response) {
                PostRelationshipResponse postRelationshipResponse = response.body();
                if (postRelationshipResponse.getRelationship().getCode() == 200) {
                    if (action.equalsIgnoreCase("follow")) {
                        Toast.makeText(context, "Siguiendo...", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(context, "Dejó de seguir", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<PostRelationshipResponse> call, Throwable throwable) {
                Toast.makeText(context, R.string.unexpected_error_occured, Toast.LENGTH_SHORT).show();
            }
        });
    }

}