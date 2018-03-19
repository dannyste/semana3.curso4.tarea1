package semana1.curso3.coursera.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import semana1.curso3.coursera.R;
import semana1.curso3.coursera.restApi.Endpoints;
import semana1.curso3.coursera.restApi.adapter.RestApiAdapter;
import semana1.curso3.coursera.restApi.model.RelationshipResponse;

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
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("action", "follow");
            postRelationshipUser(hashMap);
        }

    }

    private void postRelationshipUser(HashMap<String, String> hashMap) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.buildGsonDeserializerRelationship();
        Endpoints endpoints = restApiAdapter.establishConnectionRestApiInstagram(gson);
        Call<RelationshipResponse> relationshipResponseCall = endpoints.postRelationshipUser(id_usuario_notificacion, hashMap);
        relationshipResponseCall.enqueue(new Callback<RelationshipResponse>() {
            @Override
            public void onResponse(Call<RelationshipResponse> call, Response<RelationshipResponse> response) {
                RelationshipResponse relationshipResponse = response.body();
            }

            @Override
            public void onFailure(Call<RelationshipResponse> call, Throwable throwable) {
                Toast.makeText(context, R.string.unexpected_error_occured, Toast.LENGTH_SHORT).show();
            }
        });
    }

}