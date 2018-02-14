package semana1.curso3.coursera.restApi.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import semana1.curso3.coursera.pojo.PetProfile;
import semana1.curso3.coursera.pojo.User;
import semana1.curso3.coursera.restApi.JsonKeys;
import semana1.curso3.coursera.restApi.model.PetProfileResponse;

public class PetProfileDeserializer implements JsonDeserializer<PetProfileResponse> {

    @Override
    public PetProfileResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Gson gson = new Gson();
        PetProfileResponse petProfileResponse = gson.fromJson(json, PetProfileResponse.class);
        JsonArray jsonArray = json.getAsJsonObject().getAsJsonArray(JsonKeys.RESPONSE_ARRAY);
        petProfileResponse.setUser(deserializerUserOfJson(jsonArray));
        petProfileResponse.setPetProfiles(deserializerPetProfileOfJson(jsonArray));
        return petProfileResponse;
    }

    private User deserializerUserOfJson(JsonArray jsonArray){
        User user = new User();

        JsonObject jsonObject   = jsonArray.get(0).getAsJsonObject().getAsJsonObject(JsonKeys.USER);

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

    private ArrayList<PetProfile> deserializerPetProfileOfJson(JsonArray jsonArray){
        ArrayList<PetProfile> petProfiles = new ArrayList<>();
        for (int i = 0; i < jsonArray.size() ; i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

            JsonObject userJson     = jsonObject.getAsJsonObject(JsonKeys.USER);
            String id               = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto   = userJson.get(JsonKeys.USER_FULLNAME).getAsString();

            String idMedia = jsonObject.get(JsonKeys.USER_ID).getAsString();

            JsonObject imageJson            = jsonObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson    = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto                  = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = jsonObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            PetProfile petProfile = new PetProfile();
            petProfile.setId(id);
            petProfile.setName(nombreCompleto);
            petProfile.setIdPhoto(idMedia);
            petProfile.setPhoto(urlFoto);
            petProfile.setRating(likes);

            petProfiles.add(petProfile);
        }
        return petProfiles;
    }

}