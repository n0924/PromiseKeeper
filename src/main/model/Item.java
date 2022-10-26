package model;


//Represents an item with a name, priority, category, and budget (in dollars)
public class Item {
    private String name;         //name of product
    private String priority;     //how much they want/need
    private int budget;          //budget of the product (in dollars)
    private int price;           //price paid for this item when bought

    //REQUIRES: budget > 0
    //MODIFIES: this
    //EFFECTS: set budget if Budget > 0, no changes otherwise
    public void setBudget(int budget) {
        if (budget > 0) {
            this.budget = budget;
        }
    }

    //REQUIRES: price > 0
    //MODIFIES: this
    //EFFECTS: set price to be the paid price
    public void setPrice(int price) {
        this.price = price;
    }

    //MODIFIES: this
    //EFFECTS: set priority to the selected priority
    public void setPriority(String priority) {
        this.priority = priority;
    }


    //MODIFIES: this
    //EFFECTS: change name to given name
    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getBudget() {
        return budget;
    }
}
