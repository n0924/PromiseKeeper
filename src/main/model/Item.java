package model;

//Represents a needed/wanted item with a name, priority, category, and budget (in dollars)
public class Item {
    private String name;         //name of product
    private String priority;     //how much they want/need
    private String category;     //category of the item
    private int budget;          //budget of the product (in dollars)

    //REQUIRES: budget > 0
    //EFFECTS: creates an item with the given name and budget
    // Has default priority set to "Low Priority", default category set to "Food".
    // and by default this item is a needed item.
    public Item(String name, int budget) {
        this.name = name;
        this.budget = budget;
        priority = "Low Priority";
        category = "Food";
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
    //EFFECTS: set name to be the given (new) name
    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: set priority to the selected priority
    public void setPriority(String priority) {
        this.priority = priority;
    }


    //MODIFIES: this
    //EFFECTS: set category to choosen category
    public void setCategory(String category) {
        this.category = category;
    }


    public String getPriority() {
        return priority;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getBudget() {
        return budget;
    }


}
