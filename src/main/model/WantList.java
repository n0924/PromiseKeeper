package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Represents Want list, with no item with the same name
public class WantList implements PlanList, ToJson {
    private static final String name = "Want List";

    private List<Item> wantList;

    //EFFECTS: create empty need and want list
    public WantList() {
        wantList = new ArrayList<>();
    }


    //MODIFIES: this
    //EFFECTS: adds a wanted item to the top of the want list if not already in list
    // otherwise, make no changes
    @Override
    public void addItem(Item item) {
        List<String> names = new ArrayList<>();

        for (Item itemInList : wantList) {
            String name = itemInList.getName();
            names.add(name);
        }

        if (!names.contains(item.getName())) {
            wantList.add(0, item);
        }
    }

    //REQUIRES: item is already in the want list, want list is non-empty
    //MODIFIES: this
    //EFFECTS: removes the given item from the want list, preserve order of original list
    @Override
    public void removeItem(Item item) {
        wantList.remove(item);
    }

    //REQUIRES: sizeWant >= 1
    //EFFECTS: filter the wanted list by the given priority, preserve order of orignal list
    // if no item with the given priority exists, return an empty list
    @Override
    public List<Item> filterByPriority(String priority) {
        List<Item> filter = new ArrayList<>();

        for (Item item : wantList) {
            if (priority.equals(item.getPriority())) {
                filter.add(item);
            }
        }
        return filter;

    }

    //Produce a list of names of all wanted items
    @Override
    public List<String> toName() {
        List<String> names = new ArrayList<>();

        for (Item item : wantList) {
            names.add(item.getName());
        }
        return names;
    }

    //EFFECTS: produce true if there is an item in list with the given name
    @Override
    public boolean inList(String name) {
        List<String> names = new ArrayList<>();

        for (Item itemInList : wantList) {
            String nameItem = itemInList.getName();
            names.add(nameItem);
        }
        return (names.contains(name));
    }


    //REQUIRES: the list contains an item with the given name (inList(name) is true)
    //EFFECTS: produce true if there is an item in list with given name
    @Override
    public Item getItem(String name) {
        Item foundItem = new Item();
        for (Item itemInList : wantList) {
            if (name.equals(itemInList.getName())) {
                foundItem = itemInList;
            }
        }
        return foundItem;
    }

    //EFFECTS: checks if an item already exists in wanted list
    @Override
    public boolean containsItem(Item item) {
        return wantList.contains(item);
    }

    //EFFECTS: checks how many elements are in the wanted list
    @Override
    public int sizeList() {
        return wantList.size();
    }

    //REQUIRES: item is wanted
    // EFFECTS: get the element of need list at index i
    @Override
    public Item getItemIndex(int i) {
        return wantList.get(i);
    }

    //EFFECTS: get the want list
    @Override
    public List<Item> getList() {
        return wantList;
    }

    //CITE: CPSC210 JsonSerializationDemo
    //EFFECTS: returns the want list as a JsonArray
    @Override
    public JSONArray listToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item item : wantList) {
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
        json.put("item", wantList);
        return json;
    }
}