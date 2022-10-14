package model;

import java.util.ArrayList;
import java.util.List;

//Represents a list of bought wanted items
public class BoughtWantList implements BoughtList {
    private List<Item> boughtWantList; //List of want items bought
    private int totalBudget;  //total budget of all bought wanted items (in dollars)
    private int totalPrice;  //total amount paid (in dollars)
    private int totalOverspent; //total amount overspent (in dollars)

    //EFFECTS: create an empty list of boughtWant list
    public BoughtWantList() {
        boughtWantList = new ArrayList<>();
    }

    //REQUIRES: item is not in boughtWant list already
    //MODIFIES: this
    //EFFECTS: add a bought wanted item with given price at the top of the lsit
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

    //EFFECTS: get the element from boughWant  list of index i
    @Override
    public void getBought() {
        //stub
    }

    @Override
    public int getTotalBudget() {
        return 0; //stub
    }

    @Override
    public int getTotalPrice() {
        return 0; //stub


    //EFFECTS: produce the total amount overspent on wanted items
    @Override
    public int getTotalOverspent() {
        return 0; //stub
    }

    //EFFECTS: returns the size of boughtWant list
    @Override
    public int sizeBought() {
        return 0; // stub
    }

    //EFFECTS: returns true if item is in boughtWant list
    @Override
    public boolean containsBought() {
        return false; //stube
    }

    }
}
