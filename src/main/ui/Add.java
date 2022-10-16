package ui;

public class Add {
    //MODIFIES: this
    //EFFECTS: adds an item to appropriate list
    private void addItem() {
        System.out.println("Enter the name of the item");
        String name = input.next();
        addName(name);

        System.out.println("Enter the budget ($)");
        int budget = input.nextInt();
        addBudget(budget);

        System.out.println("Select Priority from");
        System.out.println("\t High Priority");
        System.out.println("\t Medium Priority");
        System.out.println("\t Low Priority");
        String highMediumLow = input.next().toLowerCase();
        selectPriority(highMediumLow);

        System.out.println("Do you need the item or want the item?");
        String needOrWant = input.next();
        addToList(needOrWant);

        showAddedItem();
    }
}
