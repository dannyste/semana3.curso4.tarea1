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
import semana1.curso3.coursera.restApi.model.GetRelationshipResponse;

public class GetRelationshipDeserializer implements JsonDeserializer<GetRelationshipResponse> {

    @Override
    public GetRelationshipResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        GetRelationshipResponse getRelationshipResponse = gson.fromJson(json, GetRelationshipResponse.class);
        JsonObject jsonObject = json.getAsJsonObject();
        getRelationshipResponse.setRelationship(deserializerGetRelationshipOfJson(jsonObject));
        return getRelationshipResponse;
    }

    private Relationship deserializerGetRelationshipOfJson(JsonObject jsonObject){
        Relationship relationship = new Relationship();

        JsonObject jsonData = jsonObject.getAsJsonObject(JsonKeys.RESPONSE_DATA);

        String outgoing_status = jsonData.get(JsonKeys.OUTGOING_STATUS).getAsString();

        JsonObject jsonMeta = jsonObject.getAsJsonObject(JsonKeys.RESPONSE_META);

        int code = jsonMeta.get(JsonKeys.CODE).getAsInt();

        relationship.setOutgoing_status(outgoing_status);
        relationship.setCode(code);

        return relationship;
    }

}