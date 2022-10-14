package model;

import java.util.ArrayList;
import java.util.List;

// Represents Want list, with no duplicated items
public class WantList implements PlanList {
    private List<Item> wantList;

    //EFFECTS: create empty need and want list
    public WantList() {
        wantList = new ArrayList<>();
    }


    //REQUIRES: item is wanted
    //MODIFIES: this
    //EFFECTS: adds a wanted item to the top of the want list if not already in list
    // otherwise, make no changes
    @Override
    public void addItem(Item item) {
        if (!wantList.contains(item)) {
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
    //MODIFIES: this
    //EFFECTS: filter the wanted list by the given priority, preserve order of orignal list
    // if no item with the given priority exists, return an empty list
    @Override
    public void filterByPriorityItem(String priority) {
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
    @Override
    public boolean contains(Item item) {
        return wantList.contains(item);
    }


    //REQUIRES: item is wanted
    //EFFECTS: checks how many elements are in the wanted list
    @Override
    public int size() {
        return wantList.size();
    }


    //REQUIRES: item is wanted
    // EFFECTS: get the element of need list at index i
    @Override
    public Item get(int i) {
        return wantList.get(i);
    }

    //EFFECTS: get the need list
    @Override
    public List<Item> getList() {
        return wantList;
    }
}

