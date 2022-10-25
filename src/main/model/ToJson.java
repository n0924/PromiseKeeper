package model;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ToJson {

    //CITE: CPSC210 JsonSerializationDemo
    //EFFECTS: returns the list as a JsonObject
    JSONObject toJson();

    //CITE: CPSC210 JsonSerializationDemo
    //EFFECTS: returns the list as a JsonArray
    JSONArray listToJson();
}
