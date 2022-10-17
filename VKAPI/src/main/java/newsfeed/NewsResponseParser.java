package newsfeed;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class NewsResponseParser {
    public NewsInfo parseResponse(String response) {
        JsonObject entries = new JsonParser().parse(response).getAsJsonObject().get("response").getAsJsonObject();
        int countItems = entries.get("items").getAsJsonArray().size();
        String nextFrom = entries.has("next_from") ? entries.get("next_from").getAsString() : null;
        return new NewsInfo(nextFrom, countItems);
    }
}
