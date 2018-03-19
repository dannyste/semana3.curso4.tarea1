package semana1.curso3.coursera.restApi;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import semana1.curso3.coursera.restApi.model.LikePhotoResponse;
import semana1.curso3.coursera.restApi.model.LikeResponse;
import semana1.curso3.coursera.restApi.model.PetProfileResponse;
import semana1.curso3.coursera.restApi.model.RelationshipResponse;
import semana1.curso3.coursera.restApi.model.UserResponse;

public interface Endpoints {

    @GET(Constants.INSTAGRAM_URL_GET_SEARCH_USER)
    Call<PetProfileResponse> getSearch(@Query("q") String q, @Query("access_token") String access_token);

    @GET(Constants.INSTAGRAM_URL_GET_RECENT_MEDIA_USER_ID)
    Call<PetProfileResponse> getRecentMedia(@Path("user_id") String user_id);

    @POST(Constants.INSTAGRAM_URL_POST_LIKES_MEDIA_ID)
    Call<LikeResponse> postLikeMedia(@Path("media-id") String user_id);

    @POST(Constants.INSTAGRAM_URL_POST_RELATIONSHIP_USER_ID)
    Call<RelationshipResponse> postRelationshipUser(@Path("media-id") String user_id, @Body Map<String, String> body);

    @FormUrlEncoded
    @POST(Constants.SERVER_URL_POST_REGISTRAR_USUARIO)
    Call<UserResponse> postRegistrarUsuario(@Field("id_dispositivo") String id_dispositivo, @Field("id_usuario_instagram") String id_usuario_instagram);

    @FormUrlEncoded
    @POST(Constants.SERVER_URL_POST_REGISTRAR_LIKE_FOTO)
    Call<LikePhotoResponse> postRegistrarLikeFoto(@Field("id_dispositivo") String id_dispositivo, @Field("id_usuario_instagram") String id_usuario_instagram, @Field("id_foto_instagram") String id_foto_instagram, @Field("id_usuario_notificacion") String id_usuario_notificacion, @Field("usuario_instagram") String usuario_instagram);

}