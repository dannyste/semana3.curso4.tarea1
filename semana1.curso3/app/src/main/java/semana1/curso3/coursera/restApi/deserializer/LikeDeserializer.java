package semana1.curso3.coursera.restApi.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONObject;

import java.lang.reflect.Type;

import semana1.curso3.coursera.pojo.Like;
import semana1.curso3.coursera.restApi.JsonKeys;
import semana1.curso3.coursera.restApi.model.LikeResponse;

public class LikeDeserializer implements JsonDeserializer<LikeResponse> {

    @Override
    public LikeResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        LikeResponse likeResponse = gson.fromJson(json, LikeResponse.class);
        JsonObject jsonObject = json.getAsJsonObject().getAsJsonObject(JsonKeys.RESPONSE_META);
        likeResponse.setLike(deserializerLikeOfJson(jsonObject));
        return likeResponse;
    }

    private Like deserializerLikeOfJson(JsonObject jsonObject){
        Like like = new Like();

        int code = jsonObject.get(JsonKeys.CODE).getAsInt();

        like.setCode(code);

        return like;
    }

}