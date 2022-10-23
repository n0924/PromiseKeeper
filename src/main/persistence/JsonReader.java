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
//Represent a reader that reads need, want, or bought-want list from
//JSON data stored in file
public class JsonReader {
    private NeedList needList;
    private WantList wantList;
    private BoughtWantList boughtWantList;

    private String file;  // either need, want, or bought-want list

    //CITE: CPSC210 JsonSerializationDemo
    //EFFECTS: constructs a reader to read from file
    public JsonReader(String file) {
        this.file = file;
        needList = new NeedList();
        wantList = new WantList();
        boughtWantList = new BoughtWantList();
    }

    //CITE: CPSC210 JsonSerializationDemo
    //EFFECTS: reads a need list as string and return it
    //throw IOException if an error occurs reading data from file
    public NeedList readNeed() throws IOException {

        String jsonData = readFile(file);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseNeedList(jsonObject);
    }

    //CITE: CPSC210 JsonSerializationDemo
    //EFFECTS: reads wantlist from file and returns it
    //throw IOException if an error occurs reading data from file
    public WantList readWant() throws IOException {
        String jsonData = readFile(file);
        JSONObject jsonWants = new JSONObject(jsonData);
        return parseWantList(jsonWants);
    }

    //CITE: CPSC210 JsonSerializationDemo
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

    //EFFECTS: parses a need list from JSON object and returns it
    private NeedList parseNeedList(JSONObject jsonObject) {
        parseItems(jsonObject);
        return needList;
    }

    //EFFECTS: parses a want list from JSON object and returns it
    private WantList parseWantList(JSONObject jsonObject) {
        parseItems(jsonObject);
        return wantList;
    }

    //EFFECTS: parses a boughtWant list from JSON object and returns it
    private BoughtWantList parseBoughtWantList(JSONObject jsonObject) {
        parseItems(jsonObject);
        return boughtWantList;
    }

    //CITE: CPSC210 JsonSerializationDemo
    //EFFECTS: parses items from JSON object and adds them to the given list
    private void parseItems(JSONObject jsonObject) {
        JSONArray jsonList = jsonObject.getJSONArray("item");
        String listType = jsonObject.getString("name");

        for (Object item : jsonList) {
            JSONObject jsonItem = (JSONObject) item;
            parseItem(jsonItem, listType);
        }
    }

    //EFFECTS: parses an item from JSON object and add it to the given list
    private void parseItem(JSONObject jsonItem, String listType) {
        Item savedItem = new Item();

        String name = jsonItem.getString("name");
        int budget = jsonItem.getInt("budget");
        String priority = jsonItem.getString("priority");

        savedItem.setName(name);
        savedItem.setBudget(budget);
        savedItem.setPriority(priority);

        if (listType.equals("Need List")) {
            needList.addItem(savedItem);
        } else {
            if (listType.equals("Want List")) {
                wantList.addItem(savedItem);
            } else {
                int price = jsonItem.getInt("Price List");
                boughtWantList.addBought(savedItem, price);
            }
        }
    }

}
