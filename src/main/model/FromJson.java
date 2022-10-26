package model;

//Methods used to load data while preserving the original order
public interface FromJson {
    //MODIFIES: this
    //EFFECTS: adds item at the end of list
    void addLast(Item item);
}
