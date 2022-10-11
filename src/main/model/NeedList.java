package model;

import java.util.ArrayList;
import java.util.List;

// Represents a Need and a Want list, with no duplicated items
public class NeedList {
    private List<Item> needList;
    private List<Item> wantList;

    //EFFECTS: create empty need and want list
    public NeedList() {
        needList = new ArrayList<>();
        wantList = new ArrayList<>();
    }

    //REQUIRES: item is needed
    //MODIFIES: this
    //EFFECTS: adds a needed item to the top of need list if not already in list.
    // otherwise, make no changes
    public void addNeed(Item item) {
        // stub
    }

    //REQUIRES: item is wanted
    //MODIFIES: this
    //EFFECTS: adds a wanted item to the top of the want list if not already in list
    // otherwise, make no changes
    public void addWant(Item item) {
        // stub
    }

    //REQUIRES: item is already in the need list, need list is non-empty
    //MODIFIES: this
    //EFFECTS: removes the given item from the need list, preserve order of list
    public void removeNeed(Item item) {
        //stub
    }

    //REQUIRES: item is already in the want list, want list is non-empty
    //MODIFIES: this
    //EFFECTS: removes the given item from the want list, preserve order of list
    public void removeWant(Item item) {
        //stub
    }

    //REQUIRES: sizeNeed >= 1
    //MODIFIES: this
    //EFFECTS: filter the needed list by the given priority, preserve order of original list
    // if no item with the given priority exists, return an empty list
    public void filterByPriorityNeed(String priority) {
        // stub
    }

    //REQUIRES: sizeWant >= 1
    //MODIFIES: this
    //EFFECTS: filter the wanted list by the given priority, preserve order of orignal list
    // if no item with the given priority exists, return an empty list
    public void filterByPriorityWant( String priority) {
        // stub
    }


    //REQUIRES: item is needed
    //EFFECTS: checks if an item already exists in needed list
    public boolean containsNeed(Item item) {
        return needList.contains(item);
    }

    //REQUIRES: item is wanted
    //EFFECTS: checks if an item already exists in wanted list
    public boolean containsWant(Item item) {
        return needList.contains(item);
    }

    //REQUIRES: item is needed
    //EFFECTS: checks how many elements are in the needed list
    public int sizeNeed() {
        return needList.size();
    }

    //REQUIRES: item is wanted
    //EFFECTS: checks how many elements are in the wanted list
    public int sizeWant() {
        return wantList.size();
    }

    //REQUIRES: item is needed
    // EFFECTS: get the element of need list at index i
    public Item getNeed(int i) {
        return needList.get(i);
    }

    //REQUIRES: item is wanted
    // EFFECTS: get the element of need list at index i
    public Item getWant(int i) {
        return wantList.get(i);
    }

    public List<Item> getNeedList() {
        return needList;
    }

    public List<Item> getWantList() {
        return wantList;
    }
}



