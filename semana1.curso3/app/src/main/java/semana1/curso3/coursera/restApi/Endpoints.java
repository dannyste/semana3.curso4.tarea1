package semana1.curso3.coursera.restApi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import semana1.curso3.coursera.restApi.model.PetProfileResponse;
import semana1.curso3.coursera.restApi.model.UserResponse;

public interface Endpoints {

    @GET(Constants.INSTAGRAM_URL_GET_SEARCH_USER)
    Call<PetProfileResponse> getSearch(@Query("q") String q, @Query("access_token") String access_token);

    @GET(Constants.INSTAGRAM_URL_GET_RECENT_MEDIA_USER_ID)
    Call<PetProfileResponse> getRecentMedia(@Path("user_id") String user_id);

    @FormUrlEncoded
    @POST(Constants.URL_POST_REGISTRAR_USUARIO)
    Call<UserResponse> postRegistrarUsuario(@Field("id_dispositivo") String id_dispositivo, @Field("id_usuario_instagram") String id_usuario_instagram);

}