package model;

//Represents an item that gets added to the Need/Want lists
public class Item {
    private String name;         //name of product
    private boolean needOrWant;  //true if needed item, false if wanted item
    private String priority;     //how much they want/need
    private String category;     //category of the item
    private int budget;          //budget of the product

    //REQUIRES: budget > 0
    //EFFECTS: creates an item with the given name and budget,
    // with default priority "No Priority", default category "Food".
    // by default it is a needed item
    public Item(String name, int budget) {
        this.name = name;
        needOrWant = true;
        this.budget = budget;
        priority = "No Priority";
        category = "Food";
    }


    //MODIFIES: this
    //EFFECTS: set an item to be a need item or want item
    public void setNeeded(boolean needOrWant) {
        this.needOrWant = needOrWant;
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

    // EFFECTS: return true if needed item, false if wanted item
    public boolean isNeeded() {
        return needOrWant;
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
