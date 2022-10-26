package persistence;

import model.BoughtWantList;
import model.Item;
import model.NeedList;
import model.WantList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//CITE: Some parts were inspired by CPSC210 JsonSerializationDemo
//Represent a reader that reads need/want/bought-want lists from JSON data stored in file
public class JsonReader {
    private String file;


    //EFFECTS: constructs a reader to read from file
    public JsonReader(String file) {
        this.file = file;
    }

    //CITE: CPSC210 JsonSerializationDemo
    //REQUIRES: the file is a JSON need list
    //EFFECTS: reads a need list as string and return it
    //throw IOException if an error occurs reading data from file
    //throw InvalidFileException if WantList or BoughtWant list file is passed on
    public NeedList readNeed() throws IOException {

        String jsonData = readFile(file);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseNeedList(jsonObject);
    }

    //CITE: CPSC210 JsonSerializationDemo
    //REQUIRES: the file is a JSON want list
    //EFFECTS: reads wantlist from file and returns it
    //throw IOException if an error occurs reading data from file
    public WantList readWant() throws IOException {

        String jsonData = readFile(file);
        JSONObject jsonWants = new JSONObject(jsonData);
        return parseWantList(jsonWants);
    }

    //CITE: CPSC210 JsonSerializationDemo
    //REQUIRES: the file is a JSON boughtWant list
    //EFFECTS: reads boughtWantlist from file and returns it
    //throw IOException if an error occurs reading data from file
    public BoughtWantList readBoughtWant() throws IOException {
        String jsonData = readFile(file);
        JSONObject jsonBoughtWants = new JSONObject(jsonData);
        return parseBoughtWantList(jsonBoughtWants);
    }

    //CITE: CPSC210 JsonSerializationDemo
    //EFFECTS: reads file as string and returns it
    private String readFile(String file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(file),
                StandardCharsets.UTF_8)) {
            stream.forEach(s -> stringBuilder.append(s));
        }
        return stringBuilder.toString();
    }

    //REQUIRES: jsonObject is a list of need items
    //EFFECTS: parses a need list from JSON object and returns it
    private NeedList parseNeedList(JSONObject jsonObject) {
        NeedList needList = new NeedList();
        parseNeedItems(jsonObject, needList);
        return needList;
    }

    //REQUIRES: jsonObject is a list of want items
    //EFFECTS: parses a want list from JSON object and returns it
    private WantList parseWantList(JSONObject jsonObject) {
        WantList wantList = new WantList();
        parseWantItems(jsonObject, wantList);
        return wantList;
    }

    //REQUIRES: jsonObject is a list of bought wanted items
    //EFFECTS: parses a boughtWant list from JSON object and returns it
    private BoughtWantList parseBoughtWantList(JSONObject jsonObject) {
        BoughtWantList boughtWantList = new BoughtWantList();
        parseBoughtWantItems(jsonObject, boughtWantList);
        return boughtWantList;
    }

    //CITE: CPSC210 JsonSerializationDemo
    //EFFECTS: parses items from JSON object and adds them to need list
    private void parseNeedItems(JSONObject jsonObject, NeedList needs) {
        JSONArray jsonArray = jsonObject.getJSONArray("item");

        for (Object item : jsonArray) {
            JSONObject jsonItem = (JSONObject) item;
            parseNeed(jsonItem, needs);
        }
    }

    //CITE: CPSC210 JsonSerializationDemo
    //EFFECTS: parses items from JSON object and adds them to want list
    private void parseWantItems(JSONObject jsonObject, WantList wants) {
        JSONArray jsonArray = jsonObject.getJSONArray("item");


        for (Object item : jsonArray) {
            JSONObject jsonItem = (JSONObject) item;
            parseWant(jsonItem, wants);
        }
    }

    //CITE: CPSC210 JsonSerializationDemo
    //EFFECTS: parses items from JSON bought-want list and adds them to bought-want list
    private void parseBoughtWantItems(JSONObject jsonObject, BoughtWantList boughtWants) {
        JSONArray jsonArray = jsonObject.getJSONArray("item");

        for (Object item : jsonArray) {
            JSONObject jsonItem = (JSONObject) item;
            parseBoughtWant(jsonItem, boughtWants);
        }
    }


    //EFFECTS: parses an item from JSON need list and add it to the need list
    // preserve the original order of the list
    private void parseNeed(JSONObject jsonItem, NeedList needs) {
        Item savedItem = new Item();

        String name = jsonItem.getString("name");
        int budget = jsonItem.getInt("budget");
        String priority = jsonItem.getString("priority");

        savedItem.setName(name);
        savedItem.setBudget(budget);
        savedItem.setPriority(priority);

        needs.addLast(savedItem);
    }


    //EFFECTS: parses an item from JSON object and add it to the want list
    // preserve the original order of the list
    private void parseWant(JSONObject jsonItem, WantList wants) {
        Item savedItem = new Item();

        String name = jsonItem.getString("name");
        int budget = jsonItem.getInt("budget");
        String priority = jsonItem.getString("priority");

        savedItem.setName(name);
        savedItem.setBudget(budget);
        savedItem.setPriority(priority);

        wants.addLast(savedItem);
    }


    //EFFECTS: parses an item from JSON object and add it to the boughWant list
    // preserve the original order of the list
    private void parseBoughtWant(JSONObject jsonItem, BoughtWantList boughtWants) {
        Item savedItem = new Item();

        String name = jsonItem.getString("name");
        int budget = jsonItem.getInt("budget");
        String priority = jsonItem.getString("priority");
        int price = jsonItem.getInt("price");

        savedItem.setName(name);
        savedItem.setBudget(budget);
        savedItem.setPriority(priority);
        savedItem.setPrice(price);

        boughtWants.addLast(savedItem);
    }


}
