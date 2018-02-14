package semana1.curso3.coursera.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import semana1.curso3.coursera.restApi.Constants;
import semana1.curso3.coursera.restApi.Endpoints;
import semana1.curso3.coursera.restApi.deserializer.LikeDeserializer;
import semana1.curso3.coursera.restApi.deserializer.PetProfileDeserializer;
import semana1.curso3.coursera.restApi.deserializer.UserDeserializer;
import semana1.curso3.coursera.restApi.model.LikeResponse;
import semana1.curso3.coursera.restApi.model.PetProfileResponse;

public class RestApiAdapter {

    /*
     * INSTAGRAM
     */

    public Endpoints establishConnectionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.INSTAGRAM_ROOT_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
        return retrofit.create(Endpoints.class);
    }

    public Gson buildGsonDeserializerSearch(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PetProfileResponse.class, new UserDeserializer());
        return gsonBuilder.create();
    }

    public Gson buildGsonDeserializerMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PetProfileResponse.class, new PetProfileDeserializer());
        return gsonBuilder.create();
    }

    public Gson buildGsonDeserializerLikeMedia(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LikeResponse.class, new LikeDeserializer());
        return gsonBuilder.create();
    }

    /*
     * SERVER
     */

    public Endpoints establishConnectionRestApiServer(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SERVER_ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Endpoints.class);
    }

}