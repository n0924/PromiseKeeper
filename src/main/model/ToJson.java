package model;

import org.json.JSONObject;

public interface ToJson {

    //CITE: CPSC210 JsonSerializationDemo
    //EFFECTS: returns the list as a JsonObject
    JSONObject toJson();
}
