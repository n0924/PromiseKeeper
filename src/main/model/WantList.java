package model;

import java.util.ArrayList;
import java.util.List;

// Represents Want list, with no duplicated items
public class WantList {
    private List<Item> wantList;

    //EFFECTS: create empty need and want list
    public WantList() {
        wantList = new ArrayList<>();
    }


    //REQUIRES: item is wanted
    //MODIFIES: this
    //EFFECTS: adds a wanted item to the top of the want list if not already in list
    // otherwise, make no changes
    public void addWant(Item item) {
        if (!wantList.contains(item)) {
            wantList.add(0, item);
        }
    }

    //REQUIRES: item is already in the want list, want list is non-empty
    //MODIFIES: this
    //EFFECTS: removes the given item from the want list, preserve order of original list
    public void removeWant(Item item) {
        wantList.remove(item);
    }

    //REQUIRES: sizeWant >= 1
    //MODIFIES: this
    //EFFECTS: filter the wanted list by the given priority, preserve order of orignal list
    // if no item with the given priority exists, return an empty list
    public void filterByPriorityWant(String priority) {
        List<Item> filterOut = new ArrayList<>();

        for (Item item : wantList) {
            if (!priority.equals(item.getPriority())) {
                filterOut.add(item);
            }
        }
        wantList.removeAll(filterOut);
    }


    //REQUIRES: item is wanted
    //EFFECTS: checks if an item already exists in wanted list
    public boolean containsWant(Item item) {
        return wantList.contains(item);
    }


    //REQUIRES: item is wanted
    //EFFECTS: checks how many elements are in the wanted list
    public int sizeWant() {
        return wantList.size();
    }


    //REQUIRES: item is wanted
    // EFFECTS: get the element of need list at index i
    public Item getWant(int i) {
        return wantList.get(i);
    }

    public List<Item> getWantList() {
        return wantList;
    }
}

