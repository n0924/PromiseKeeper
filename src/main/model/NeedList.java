package model;

import java.util.ArrayList;
import java.util.List;

// Represents a Need list, with no duplicated items
public class NeedList implements PlanList {
    private List<Item> needList;

    //EFFECTS: create empty need list
    public NeedList() {
        needList = new ArrayList<>();
    }


    //REQUIRES: item has a name
    //MODIFIES: this
    //EFFECTS: adds a needed item to the top of need list if not item, if the name is not taken
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

    //REQUIRES: sizeNeed >= 1, priority is "High Priority", "Medium Priority" "Low Priority"
    //MODIFIES: this
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

    //Produce a list of names of all needed items
    @Override
    public List<String> toName() {
        List<String> names = new ArrayList<>();

        for (Item item : needList) {
            names.add(item.getName());
        }
        return names;
    }

    //REQUIRES: the name exists in the list
    //EFFECTS: get the item with that name
    @Override
    public Item getItem(String name) {
        Item foundItem = new Item();

        if (inList(name)) {
            for (Item itemInList : needList) {
                if (itemInList.getName() == name) {
                    foundItem = itemInList;
                }
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
    public int sizeItem() {
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
}