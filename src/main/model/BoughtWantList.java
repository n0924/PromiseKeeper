package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//Represents bought wanted items
public class BoughtWantList implements ToJson {
    private static final String name = "Bought Wanted List";

    private List<Item> boughtWantList; //List of want items bought
    private int totalPrice; // total amount spent on wanted items
    private int totalOverspent; //total amount spent above budget on wanted items (in dollars)

    //EFFECTS: create an empty list of bought wantedlist, with initial total Price = 0,
    // and initial totalOverspent = 0
    public BoughtWantList() {
        boughtWantList = new ArrayList<>();
        totalPrice = 0;
        totalOverspent = 0;
    }

    //REQUIRES: item is not in boughtWant list already, price >=0
    //MODIFIES: this
    //EFFECTS: add a bought needed item with given price at the top of the list,
    // calculate the total amount of money spent on wanted items, and total amount spent above budget
    public void addBought(Item item, int price) {
        item.setPrice(price);
        totalPrice += price;
        boughtWantList.add(0, item);

        if (item.getPrice() > item.getBudget()) {
            totalOverspent += item.getPrice() - item.getBudget();
        }
    }

    //REQUIRES: boughtWant list is non-empty
    //MODIFIES: this
    //EFFECTS: filter list so that it only contains overspent items
    public List<Item> filterOverspent() {
        List<Item> overspent = new ArrayList<>();

        for (Item item : boughtWantList) {
            if (item.getPrice() > item.getBudget()) {
                overspent.add(item);
            }
        }
        return overspent;
    }

    //REQUIRES: boughtWant list is non-empty
    //EFFECTS: get the total price paid for all wanted items
    public int getTotalPrice() {
        return totalPrice;
    }

    //REQUIRES: boughtWant list is non-empty
    //EFFECTS: get the total amount spent above budget
    public int getTotalOverspent() {
        return totalOverspent;
    }

    //EFFECTS: get the element from boughWant list
    public List<Item> getBoughtList() {
        return boughtWantList;
    }

    //CITE: CPSC210 JsonSerializationDemo
    //EFFECTS: returns an item as a JsonArray
    @Override
    public JSONArray listToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item item : boughtWantList) {
            jsonArray.put(item.toJson());
        }
        return jsonArray;
    }

    //CITE: CPSC210 JsonSerializationDemo
    //EFFECTS: returns the want list as a JsonObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("item", boughtWantList);
        return json;
    }
}

