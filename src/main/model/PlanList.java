package model;

import java.util.List;

//Methods for both need and want list
public interface PlanList {
    //MODIFIES: this
    //EFFECTS: adds an item to the top of list if not already in list
    // otherwise, make no changes
    void addItem(Item item);

    //REQUIRES: item is already in the want list, list is non-empty
    //MODIFIES: this
    //EFFECTS: removes the given item from the want list, preserve order of original list
    void removeItem(Item item);

    //REQUIRES: sizeList >= 1
    //EFFECTS: filter the wanted list by the given priority, preserve order of orignal list
    // if no item with the given priority exists, return an empty list
    List<Item> filterByPriority(String priority);

    //EFFECTS: checks if an item already exists in list
    boolean containsItem(Item item);

    //EFFECTS: checks how many elements are in list
    int sizeList();

    // EFFECTS: get the element of  list at index i
    Item getItemIndex(int i);

    // EFFECTS: get the item with a given string
    Item getItem(String name);

    //EFFECTS: get the list
    List<Item> getList();

    //EFFECTS: produce true if there is an item in list with given name
    boolean inList(String name);

    //Produce a list of names of all needed items
    List<String> toName();
}
