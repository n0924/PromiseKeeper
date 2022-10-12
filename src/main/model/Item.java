package model;

//Represents a needed/wanted item with a name, priority, category, and budget (in dollars)
public class Item {
    private String name;         //name of product
    private String priority;     //how much they want/need
    private int budget;          //budget of the product (in dollars)
    private int price;           // price paid for this item when bought

    //REQUIRES: budget > 0
    //EFFECTS: creates an item with the given name and budget
    // Has default priority "Low Priority", and the price paid by default is 0.
    public Item(String name, int budget) {
        this.name = name;
        this.budget = budget;
        priority = "Low Priority";
        price = 0;
    }

    //REQUIRES: newBudget > 0
    //MODIFIES: this
    //EFFECTS: set budget to new budget if newBudget > 0, no changes otherwise
    public void setNewBudget(int newBudget) {
        if (newBudget > 0) {
            this.budget = newBudget;
        }
    }

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
