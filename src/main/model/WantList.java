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


    //REQUIRES: item has a name
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
    public List<Item> filterByPriority(String priority) {
        List<Item> filter = new ArrayList<>();

        for (Item item : wantList) {
            if (priority.equals(item.getPriority())) {
                filter.add(item);
            }
        }
        return filter;

    }

    //EFFECTS: get the item with that name
    @Override
    public boolean inList(String name) {
        List<String> names = new ArrayList<>();

        for (Item itemInList : wantList) {
            String nameItem = itemInList.getName();
            names.add(nameItem);
        }
        return (names.contains(name));
    }

    //EFFECTS: produce true if there is an item in list with given name
    @Override
    public Item getItem(String name) {
        Item foundItem = new Item();

        if (inList(name)) {
            for (Item itemInList : wantList) {
                if (itemInList.getName() == name) {
                    foundItem = itemInList;
                }
            }
        }
        return foundItem;
    }


    @Override
    public List<Item> getList() {
        return wantList;
    }

    //REQUIRES: item is wanted
    //EFFECTS: checks if an item already exists in wanted list
    @Override
    public boolean containsItem(Item item) {
        return wantList.contains(item);
    }


    //REQUIRES: item is wanted
    //EFFECTS: checks how many elements are in the wanted list
    @Override
    public int sizeItem() {
        return wantList.size();
    }


    //REQUIRES: item is wanted
    // EFFECTS: get the element of need list at index i
    @Override
    public Item getItemIndex(int i) {
        return wantList.get(i);
    }
}

