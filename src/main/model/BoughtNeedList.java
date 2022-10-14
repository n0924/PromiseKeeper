package model;

import java.util.ArrayList;
import java.util.List;

//Represents a list of all bought needed items
public class BoughtNeedList implements BoughtList {
    private List<Item> boughtNeedList; //List of needed items bought
    private int totalBudget; //total budget of all bought needed items (in dollars)
    private int totalPrice; //total amount paid (in dollars)
    private int totalOverspent;

    //EFFECTS: create an empty list of boughtNeed list
    public BoughtNeedList() {
        boughtNeedList = new ArrayList<>();
    }

    //REQUIRES: item is not in boughtNeed list already
    //MODIFIES: this
    //EFFECTS: add a bought needed item with given price
    @Override
    public void addBought(Item item, int price) {

    }

    //REQUIRES: boughtNeed list is non-empty
    //MODIFIES: this
    //EFFECTS: filter list so that it only contains overspent items
    @Override
    public void filterOverspent() {

    }

    //EFFECTS: produce the total amount overspent on needed items
    @Override
    public int getTotalOverspent() {
        return 0;
    }

    //EFFECTS: returns the size of boughtNeed list
    @Override
    public int sizeBought() {
        return 0; // stub
    }

    //EFFECTS: returns true if item is in boughtNeed list
    @Override
    public boolean containsBought() {
        return false; //stube
    }

    //EFFECTS: get the element from boughtNeed list of index i
    @Override
    public void getBought() {
        //stub
    }

    @Override
    public int getTotalBudget() {
        return 0;
    }

    @Override
    public int getTotalPrice() {
        return 0;
    }
}
