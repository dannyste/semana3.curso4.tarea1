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
import semana1.curso3.coursera.restApi.model.RelationshipResponse;

public class RelationshipDeserializer implements JsonDeserializer<RelationshipResponse> {

    @Override
    public RelationshipResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        RelationshipResponse relationshipResponse = gson.fromJson(json, RelationshipResponse.class);
        JsonObject jsonObject = json.getAsJsonObject().getAsJsonObject(JsonKeys.RESPONSE_META);
        relationshipResponse.setRelationship(deserializerRelationshipOfJson(jsonObject));
        return relationshipResponse;
    }

    private Relationship deserializerRelationshipOfJson(JsonObject jsonObject){
        Relationship relationship = new Relationship();

        int code = jsonObject.get(JsonKeys.CODE).getAsInt();

        relationship.setCode(code);

        return relationship;
    }

}