package model;

import java.util.ArrayList;
import java.util.List;

//Represents a list of bought wanted items
public class BoughtWantList implements BoughtList {
    private List<Item> boughtWantList; //List of want items bought
    private int totalBudget;  //total budget of all bought wanted items (in dollars)
    private int totalPrice;  //total amount paid (in dollars)

    //EFFECTS: create an empty list of boughtWant list
    public BoughtWantList() {
        boughtWantList = new ArrayList<>();
    }

    //REQUIRES: item is not in boughtWant list already
    //MODIFIES: this
    //EFFECTS: add a bought wanted item with given price
    @Override
    public void addBought(Item item, int price) {
        //stub
    }

    //REQUIRES: boughtWant list is non-empty
    //MODIFIES: this
    //EFFECTS: filter list so that it only contains overspent items
    @Override
    public void filterOverspent() {
        // stub
    }


    //EFFECTS: produce the total amount paid for bought wanted items
    @Override
    public int totalBudget() {
        return 0; //stub
    }

    //EFFECTS: produce the total amount of budget of items in boughtWant list
    @Override
    public int totalPrice() {
        return 0; //stub
    }

    //EFFECTS: produce the total amount overspent on wanted items
    @Override
    public int totalOverspent() {
        return 0; //stub
    }

    //EFFECTS: returns the size of boughtWant list
    public int sizeBoughtWant() {
        return 0; // stub
    }

    //EFFECTS: returns true if item is in boughtWant list
    public boolean containsBoughtWant() {
        return false; //stube
    }

    //EFFECTS: get the element from boughWant  list of index i
    public void getBoughtWant() {
        //stub
    }
}
