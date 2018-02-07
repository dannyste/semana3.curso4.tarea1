package semana1.curso3.coursera.restApi.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import semana1.curso3.coursera.pojo.User;
import semana1.curso3.coursera.restApi.JsonKeys;
import semana1.curso3.coursera.restApi.model.PetProfileResponse;

public class UserDeserializer implements JsonDeserializer<PetProfileResponse> {

    @Override
    public PetProfileResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Gson gson = new Gson();
        PetProfileResponse petProfileResponse = gson.fromJson(json, PetProfileResponse.class);
        JsonArray jsonArray = json.getAsJsonObject().getAsJsonArray(JsonKeys.RESPONSE_ARRAY);
        petProfileResponse.setUser(deserializerUserOfJson(jsonArray));
        return petProfileResponse;
    }

    private User deserializerUserOfJson(JsonArray jsonArray){
        User user = new User();

        JsonObject jsonObject   = jsonArray.get(0).getAsJsonObject();

        String id               = jsonObject.get(JsonKeys.USER_ID).getAsString();
        String username         = jsonObject.get(JsonKeys.USER_USERNAME).getAsString();
        String name             = jsonObject.get(JsonKeys.USER_FULLNAME).getAsString();
        String photo            = jsonObject.get(JsonKeys.USER_PROFILE_PICTURE).getAsString();

        user.setId(id);
        user.setUsername(username);
        user.setPhoto(photo);
        user.setName(name);

        return user;
    }

}