package model;

import org.json.JSONArray;
import org.json.JSONObject;

public interface SaveList {
    JSONObject itemToJSON();

    JSONArray listToJSON();
}
