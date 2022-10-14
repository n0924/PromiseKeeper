package model;

import java.util.ArrayList;
import java.util.List;

//Represents a list of all bought needed items
public class BoughtNeedList implements BoughtList {
    private List<Item> boughtNeedList; //List of needed items bought
    protected int totalBudget; //total budget of all bought needed items (in dollars)
    protected int totalPrice; //total amount paid (in dollars)

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

    //EFFECTS: produce the total amount of price in boughtNeed list
    @Override
    public int totalBudget() {
        return 0;
    }

    //EFFECTS: produce the total amount paid for bought needed items
    @Override
    public int totalPrice() {
        return 0;
    }

    //EFFECTS: produce the total amount overspent on needed items
    @Override
    public int totalOverspent() {
        return 0;
    }
}
