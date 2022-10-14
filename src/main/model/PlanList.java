package model;

import java.util.List;

public interface PlanList {
    //REQUIRES: item is wanted
    //MODIFIES: this
    //EFFECTS: adds a wanted item to the top of the want list if not already in list
    // otherwise, make no changes
    void addItem(Item item);

    //REQUIRES: item is already in the want list, want list is non-empty
    //MODIFIES: this
    //EFFECTS: removes the given item from the want list, preserve order of original list
    void removeItem(Item item);

    //REQUIRES: sizeWant >= 1
    //MODIFIES: this
    //EFFECTS: filter the wanted list by the given priority, preserve order of orignal list
    // if no item with the given priority exists, return an empty list
    void filterByPriorityItem(String priority);

    //EFFECTS: checks if an item already exists in list
    public boolean contains(Item item);


    //EFFECTS: checks how many elements are in list
    public int size();

    // EFFECTS: get the element of  list at index i
    public Item get(int i);

    public List<Item> getList();
}
