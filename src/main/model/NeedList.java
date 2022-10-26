package model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Represents a Need list, with no items with duplicated names
public class NeedList implements PlanList, ToJson, FromJson {
    private static final String name = "Need List";

    private List<Item> needList;

    //EFFECTS: create empty need list
    public NeedList() {
        needList = new ArrayList<>();
    }


    //REQUIRES: item has a name
    //MODIFIES: this
    //EFFECTS: adds a needed item to the top of list if the name is not taken
    // otherwise, make no changes
    @Override
    public void addItem(Item item) {
        List<String> names = new ArrayList<>();

        for (Item itemInList : needList) {
            String name = itemInList.getName();
            names.add(name);
        }

        if (!names.contains(item.getName())) {
            needList.add(0, item);
        }
    }

    //REQUIRES: item is already in the need list, need list is non-empty
    //MODIFIES: this
    //EFFECTS: removes the given item from the need list, preserve order of list
    @Override
    public void removeItem(Item item) {
        needList.remove(item);
    }

    //REQUIRES: sizeList >= 0
    //EFFECTS: filter the needed list by the given priority, preserve order of original list
    // if no item with the given priority exists, return an empty list
    @Override
    public List<Item> filterByPriority(String priority) {
        List<Item> filtered = new ArrayList<>();

        for (Item item : needList) {
            if (priority.equals(item.getPriority())) {
                filtered.add(item);
            }
        }
        return filtered;

    }

    //Produce a list of names of all needed items
    @Override
    public List<String> toName() {
        List<String> names = new ArrayList<>();

        for (Item item : needList) {
            names.add(item.getName());
        }
        return names;
    }

    //EFFECTS: produce true if there is an item in list with given name
    @Override
    public boolean inList(String name) {
        List<String> names = new ArrayList<>();

        for (Item itemInList : needList) {
            String nameItem = itemInList.getName();
            names.add(nameItem);
        }
        return (names.contains(name));
    }


    //REQUIRES: the list contains an item with the given name (inList(name) is true)
    //EFFECTS: get the item with that name from the list
    @Override
    public Item getItem(String name) {
        Item foundItem = new Item();
        for (Item itemInList : needList) {
            if (name.equals(itemInList.getName())) {
                foundItem = itemInList;
            }
        }
        return foundItem;
    }


    //EFFECTS: checks if an item already exists in needed list
    @Override
    public boolean containsItem(Item item) {
        return needList.contains(item);
    }

    //EFFECTS: checks how many elements are in the needed list
    @Override
    public int sizeList() {
        return needList.size();
    }


    //REQUIRES: i>= 0
    // EFFECTS: get the element of need list at index i
    @Override
    public Item getItemIndex(int i) {
        return needList.get(i);
    }


    //EFFECTS: get the need list
    @Override
    public List<Item> getList() {
        return needList;
    }

    //CITE: CPSC210 JsonSerializationDemo
    //EFFECTS: returns the need list as a JsonObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("item", needList);
        return json;
    }

    //MODIFIES: this
    //EFFECTS: adds item at the end of list
    @Override
    public void addLast(Item item) {
        needList.add(item);
    }
}