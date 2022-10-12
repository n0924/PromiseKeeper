package model;

import java.util.ArrayList;
import java.util.List;

//Represents a list of all bought needed items
public class BoughtNeedList {
    private List<Item> boughtNeedList; //List of needed items bought
    private int totalBudget; //total budget of all bought needed items (in dollars)
    private int totalPrice; //total amount paid (in dollars)

    //EFFECTS: create an empty list of boughtNeed list
    public BoughtNeedList() {
        boughtNeedList = new ArrayList<>();
    }

    //REQUIRES: item is not in boughtNeed list already
    //MODIFIES: this
    //EFFECTS: add a bought needed item with given price
    public void addBoughtNeed(Item item, int price) {
        //stub
    }

    //REQUIRES: boughtNeed list is non-empty
    //MODIFIES: this
    //EFFECTS: filter list so that it only contains overspent items
    public void filterOverspentNeed() {
        // stub
    }


    //EFFECTS: produce the total amount of price in boughtNeed list
    public int totalBudgetNeed() {
        return 0; //stub
    }

    //EFFECTS: produce the total amount paid for bought needed items
    public int totalPriceNeed() {
        return 0; //stub
    }

    //EFFECTS: produce the total amount overspent on needed items
    public int totalOverspentNeed() {
        return 0; //stub
    }

    //EFFECTS: returns the size of boughtNeed list
    public int sizeBoughtNeed() {
        return 0; // stub
    }

    //EFFECTS: returns true if item is in boughtNeed list
    public boolean containsBoughtNeed() {
        return false; //stube
    }

    //EFFECTS: get the element from boughtNeed list of index i
    public void getBoughtNeed() {
        //stub
    }

}



