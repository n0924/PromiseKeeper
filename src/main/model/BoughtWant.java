package model;

import java.util.ArrayList;
import java.util.List;

//Represents bought wanted items
public class BoughtWant {
    private List<Item> boughtWantList; //List of want items bought
    private int totalPrice; // total amount spent on wanted items
    private int totalOverspent; //total amount spent above budget on wanted items (in dollars)

    //EFFECTS: create an empty list of bought wantedlist
    public BoughtWant() {
        boughtWantList = new ArrayList<>();
    }

    //REQUIRES: item is not in boughtWant list already
    //MODIFIES: this
    //EFFECTS: add a bought needed item with given price at the top of the list
    public void addBought(Item item, int price) {
        //stub
    }

    //REQUIRES: boughtWant list is non-empty
    //MODIFIES: this
    //EFFECTS: filter list so that it only contains overspent items, preserve order of original list,
    // calculate the total amount spent above budget
    public List<Item> filterOverspent() {
        return boughtWantList; //stub
    }

    //REQUIRES: boughtWant list is non-empty
    //EFFECTS: get the total price paid for all wanted items
    public int getTotalPrice() {
        return 0; //stub
    }

    //REQUIRES: boughtWant list is non-empty
    //EFFECTS: get the total amount spent above budget
    public int getTotalOverspent() {
        return 0;
    }

    //EFFECTS: get the element from boughWant list
    public List<Item> getBoughtList() {
        return boughtWantList;
    }
}

