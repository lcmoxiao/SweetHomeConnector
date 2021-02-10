package server.tools;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import server.myservice.pojo.MatchGroupRelation;
import server.myservice.pojo.MatchRelation;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GsonTools {

    private static Gson instance = null;

    public synchronized static Gson getGson() {
        if (instance == null) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
            builder.registerTypeAdapter(Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
            instance = builder.create();
        }
        return instance;
    }

    public static List<MatchRelation> parseMatchRelation(String json) {
        Type type = new TypeToken<ArrayList<MatchRelation>>() {
        }.getType();
        return instance.fromJson(json, type);
    }

    public static List<MatchGroupRelation> parseMatchGroupRelation(String json) {
        Type type = new TypeToken<ArrayList<MatchGroupRelation>>() {
        }.getType();
        return instance.fromJson(json, type);
    }

    public static class DateDeserializer implements JsonDeserializer<Date> {
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return new Date(json.getAsJsonPrimitive().getAsLong());
        }
    }

    public static class DateSerializer implements JsonSerializer<Date> {
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.getTime());
        }
    }
}
