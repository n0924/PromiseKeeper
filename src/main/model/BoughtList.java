package model;

public interface BoughtList {
    //REQUIRES: item is not in list already
    //MODIFIES: this
    //EFFECTS: add a bought item with given price
    void addBought(Item item, int price);

    //REQUIRES: list is non-empty
    //MODIFIES: this
    //EFFECTS: filter list so that it only contains overspent items
    void filterOverspent();

    //EFFECTS: returns the size of bought list
    int sizeBought();

    //EFFECTS: returns true if item is in list
    boolean containsBought();

    //EFFECTS: get the element from boughWant  list of index i
    void getBought();

    int getTotalBudget();

    int getTotalPrice();

    //EFFECTS: produce the total amount overspent. If bought at below-budget price, then
    int getTotalOverspent();
}
