package model;


import java.util.Objects;

//Represents an item with a name, priority, category, and budget (in dollars)
public class Item {
    private String name;         //name of product
    private String priority;     //how much they want/need
    private int budget;          //budget of the product (in dollars)
    private int price;           //price paid for this item when bought

    //EFFECTS: create with a given name, priority, budget, and initial price of 0
    public Item(String name, int budget, String priority) {
        this.name = name;
        this.priority = priority;
        this.budget = budget;
        this.price = 0;
    }

    //REQUIRES: budget > 0
    //MODIFIES: this
    //EFFECTS: set budget if Budget > 0, no changes otherwise
    public void setBudget(int budget) {
        if (budget > 0) {
            this.budget = budget;
        }
    }

    //REQUIRES: price >= 0
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return budget == item.budget && price == item.price && Objects.equals(name, item.name)
                && Objects.equals(priority, item.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, priority, budget, price);
    }
}
