package model;

public interface BoughtList {
    //REQUIRES: item is not in boughtWant list already
    //MODIFIES: this
    //EFFECTS: add a bought wanted item with given price
    void addBought(Item item, int price);

    //REQUIRES: boughtWant list is non-empty
    //MODIFIES: this
    //EFFECTS: filter list so that it only contains overspent items
    void filterOverspent();

    //EFFECTS: produce the total amount paid for bought wanted items
    int totalBudget();

    //EFFECTS: produce the total amount of budget of items in boughtWant list
    int totalPrice();

    //EFFECTS: produce the total amount overspent on wanted items
    int totalOverspent();
}
