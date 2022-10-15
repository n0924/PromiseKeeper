package ui;

import model.Item;
import model.NeedList;
import model.WantList;

import java.util.Scanner;

public class PromiseKeeper {
    private WantList wantlist;
    private NeedList needlist;
    private Item item;
    private Scanner input;
    private Item foundItem;


    //Some parts of this code was inspired by the TellerApp()
    //EFFECTS: run the PromiseKeeper app
    public PromiseKeeper() {
        runApp();
    }


    //CITE:CPSC210/TellerApp()
    //MODIFES: this
    //EFFECTS: process user input

    private void runApp() {
        String input = null;
        boolean keepGoing = true;

        init();

        while (keepGoing) {
            menu();
            input = this.input.next();
            input = input.toLowerCase();

            if (input.equals("quit")) {
                keepGoing = false;
            } else {
                processInput(input);
            }

        }
        System.out.println("Don't overspend!");
    }


    //CITE: CPSC210/TellerApp()
    //MODIFIES: process user input
    private void processInput(String input) {
        if (input.equals("Add")) {
            addItem();
        }
        if (input.equals("Edit")) {
            edit();
        }

        //if (input.equals("Bought")) {
        //    bought();
        //}
        //if (input.equals("Display")) {
        //    displayList();
        //} else {
        //    System.out.println("Please enter valid option");
        //}
    }

    //CITE: CPSC210/TellerApp()
    //MODIFIES: this
    //EFFECTS: creates a want and a need list
    private void init() {
        wantlist = new WantList();
        needlist = new NeedList();
        item = new Item();
    }

    //EFFECTS: displays a starting menu
    private void menu() {
        System.out.println("\nSelect from:");
        System.out.println("\tAdd");
        System.out.println("\tBought");
        System.out.println("\tEdit");
        System.out.println("\tDisplay");
        System.out.println("\tQuit");
    }

    //MODIFIES: this
    //EFFECTS: adds an item to appriopriate list
    private void addItem() {
        System.out.println("Enter the name of the item");
        String name = input.nextLine();
        addName(name);

        System.out.println("Enter the budget ($)");
        int budget = input.nextInt();
        addBudget(budget);

        System.out.println("\n Select Priority from");
        System.out.println("\t High Priority");
        System.out.println("\t Medium Priority");
        System.out.println("\t Low Priority");
        String priority = input.next();
        selectPriority(priority);

        System.out.println("Do you need the item or want the item?");
        String needOrWant = input.next();
        addToList(needOrWant);

        showAddedItem();
    }

    //MODIFIES: this
    //EFFECTS: edits an item
    private void edit() {
        System.out.println("Is the item needed or wanted?");
        String needOrWant = input.next();
        findItem(needOrWant);

        System.out.println("Do you want to remove or change this item?");
        String removeOrChange = input.next();
        notTwoOptions(removeOrChange, "remove", "change");
        toRemoveOrChange(removeOrChange, needOrWant);

    }


    //EFFECTS: process whether to remove or change from need or want list
    private void toRemoveOrChange(String removeOrChange, String needOrWant) {
        if ((removeOrChange == "remove") && (needOrWant == "need")) {
            remove("need");
        } else {
            if ((removeOrChange == "remove") && (needOrWant == "want")) {
                remove("want");
            } else {
                if ((removeOrChange == "change") && (needOrWant == "need")) {
                    change("need");
                } else {
                    change("want");
                }
            }
        }
    }

    //EFFECTS: find the item that gets edited/removed
    private void findItem(String selection) {
        System.out.println("Which item do you want to edit?");
        String editItem = input.nextLine();

        if (!needlist.inList(editItem)) {
            System.out.println("The item does not exist");
            System.out.println("\n Try another name or quit");
            String next = input.nextLine();
            next = next.toLowerCase();

            if (!(next == "quit")) {
                runApp();
            } else {
                findItem(next);
            }
        }

        if (selection == "need") {
            foundItem = needlist.getItem(editItem);
        } else {
            foundItem = wantlist.getItem(editItem);
        }
    }

    //MODIFIES: this
    //EFFECTS: remove an item from a list
    private void remove(String selection) {
        if (selection == "need") {
            needlist.removeItem(foundItem);
        } else {
            wantlist.removeItem(foundItem);
        }
        System.out.println("Removed item:" + foundItem.getName());
    }

    //MODIFIES: this
    //EFFECTS: edit the description of an item from a list
    private void change(String selection) {
        if (selection == "need") {

        } else {

        }
    }

    //EFFECTS: process changing an item
    private void changeOptions() {
        changeMenu();

        String changeWhat = input.nextLine().toLowerCase();
        notThreeOptions(changeWhat, "name", "budget", "priority");

        if (changeWhat == "name") {
            System.out.println("Enter new name for item");
            String name = input.nextLine();
            addName(name);
            System.out.println("Name changed to:" + name);
        } else {
            if (changeWhat == "budget") {
                System.out.println("Enter new budget");
                int budget = input.nextInt();
                addBudget(budget);
                System.out.println("Budget changed to:" + budget);
            } else {
                System.out.println("Select new priority");
                String priority = input.nextLine();
                notThreeOptions(priority, "high priority",
                        "medium priority", "low priority");
                selectPriority(priority);
                System.out.println("Priority changed to:" + priority);
            }

        }
    }

    //EFFECTS: display options for changing an item description
    private void changeMenu() {
        System.out.println("\n What do you want to change?");
        System.out.println("\n Choose from");
        System.out.println("\t Name");
        System.out.println("\t Budget");
        System.out.println("\t Priority");
    }

    //MODIFIES: this
    //EFFECTS: set a name to be the given name
    private void addName(String name) {
        item.setName(name);
    }


    //MODIFIES: this
    //EFFECTS: set a budget to an item
    private void addBudget(int budget) {
        while (budget <= 0) {
            System.out.println("Budget must be a positive number");
        }
        item.setBudget(budget);
    }

    //MODIFIES: this
    //EFFECTS: set a priority for the item
    private void selectPriority(String priority) {
        priority.toLowerCase();
        notThreeOptions(priority, "high priority", "medium priority", "low priority");
        item.setPriority(priority);
    }

    //MODIFIES: this
    //EFFECTS: add to the need or want list given the input
    private void addToList(String needOrWant) {
        notTwoOptions(needOrWant, "need", "want");

        if (needOrWant.equals("need")) {
            needlist.addItem(item);
        } else {
            wantlist.addItem(item);
        }
    }

    //EFFECT: show the added item
    private void showAddedItem() {
        String name = item.getName();
        String priority = item.getPriority();
        int budget = item.getBudget();

        if (priority == "high priority") {
            System.out.println("High Priority:" + name + "," + budget);
        } else {
            if (priority == "medium priority") {
                item.setPriority("Medium Priority:" + name + "," + budget);
            } else {
                item.setPriority("Low Priority:" + name + "," + budget);
            }
        }
    }


    //EFFECTS: process invalid user input when two options were given
    private void notTwoOptions(String selection, String option1, String option2) {
        while (!(selection.equals(option1) || selection.equals(option2))) {
            System.out.println("Enter" + option1 + "or" + option2);
            selection = input.next();
            selection = selection.toLowerCase();
        }
    }

    //EFFECTS: process invalid user input when two options were given
    private void notThreeOptions(String selection, String option1, String option2, String option3) {
        while (!(selection.equals(option1) || selection.equals(option2) || selection.equals(option3))) {
            System.out.println("Enter" + option1 + "or" + option2 + "or" + option3);
            selection = input.next();
            selection = selection.toLowerCase();
        }
    }

}