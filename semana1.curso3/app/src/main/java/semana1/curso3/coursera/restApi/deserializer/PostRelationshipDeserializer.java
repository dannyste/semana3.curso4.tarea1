package semana1.curso3.coursera.restApi.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import semana1.curso3.coursera.pojo.Relationship;
import semana1.curso3.coursera.restApi.JsonKeys;
import semana1.curso3.coursera.restApi.model.PostRelationshipResponse;

public class PostRelationshipDeserializer implements JsonDeserializer<PostRelationshipResponse> {

    @Override
    public PostRelationshipResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        PostRelationshipResponse postRelationshipResponse = gson.fromJson(json, PostRelationshipResponse.class);
        JsonObject jsonObject = json.getAsJsonObject().getAsJsonObject(JsonKeys.RESPONSE_META);
        postRelationshipResponse.setRelationship(deserializerPostRelationshipOfJson(jsonObject));
        return postRelationshipResponse;
    }

    private Relationship deserializerPostRelationshipOfJson(JsonObject jsonObject){
        Relationship relationship = new Relationship();

        int code = jsonObject.get(JsonKeys.CODE).getAsInt();

        relationship.setCode(code);

        return relationship;
    }

}