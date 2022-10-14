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

    //EFFECTS: produce the total amount paid
    int totalBudget();

    //EFFECTS: produce the total amount of budget
    int totalPrice();

    //EFFECTS: produce the total amount overspent
    int totalOverspent();
}
