package ui;

import model.Item;
import model.NeedList;
import model.WantList;

import java.util.Scanner;

public class PromiseKeeper {
    private WantList wantlist;
    private NeedList needlist;
    private WantList wantlistSaved;
    private NeedList needlistSaved;
    private Item item;
    private Scanner input;
    private Item foundItem;


    //Some parts of this code was inspired by the TellerApp()
    //EFFECTS: run the PromiseKeeper app
    public PromiseKeeper() {
        runApp();
    }


    //CITE:Inspired by CPSC210/TellerApp()
    //MODIFIES: this
    //EFFECTS: process user input

    private void runApp() {
        boolean keepGoing = true;

        item = new Item();
        input = new Scanner(System.in);
        wantlist = new WantList();
        needlist = new NeedList();

        while (keepGoing) {
            menu();
            String command = input.next().toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processInput(command);
            }

        }
        System.out.println("Let's use money wisely!");
    }


    //CITE: CPSC210/TellerApp()
    //MODIFIES: process user input
    private void processInput(String command) {
        if (command.equals("add")) {
            addItem();
        } else {
            if (command.equals("edit")) {
                edit();
            }
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

    //MODIFIES: this
    //EFFECTS: edits an item
    private void edit() {
        System.out.println("Is the item needed or wanted?");
        String needOrWant = input.next().toLowerCase();
        System.out.println("Hello");
        notTwoOption(needOrWant, "need", "want");
        getItemFromList(needOrWant);

        System.out.println("Do you want to remove or change this item?");
        String removeOrChange = input.next().toLowerCase();
        notTwoOption(removeOrChange, "remove", "change");
        toRemoveOrChange(removeOrChange, needOrWant);

    }


    //EFFECTS: find the item that gets edited/removed
    private void getItemFromList(String needOrWant) {
        System.out.println("Which item do you want to edit?");
        String editItem = input.next().toLowerCase();
        findItem(needOrWant, editItem);
    }

    //EFFECTS: find the item of the given name
    private void findItem(String needOrWant, String editItem) {
        if (needOrWant.equals("need")) {
            if (needlist.inList(editItem)) {
                foundItem = needlist.getItem(editItem);
            } else {
                notInList();
            }
        } else {
            if (wantlist.inList(editItem)) {
                foundItem = wantlist.getItem(editItem);
            } else {
                notInList();
            }
        }
    }


    //EFFECTS: process when item is not in the list
    private void notInList() {
        System.out.println("The item does not exist");
        System.out.println("\n Try another name or quit");
        String nextStep = input.next().toLowerCase();
        if (nextStep.equals("quit")) {
            runApp();
        } else {
            getItemFromList(nextStep);
        }
    }

    //EFFECTS: process whether to remove or change from need or want list
    private void toRemoveOrChange(String removeOrChange, String needOrWant) {
        if (removeOrChange.equals("remove")) {
            if (needOrWant.equals("need")) {
                remove("need");
            } else {
                remove("want");
            }
        } else {
            changeOptions();
        }
    }

    //MODIFIES: this
    //EFFECTS: remove an item from a list
    private void remove(String selection) {
        if (selection.equals("need")) {
            needlist.removeItem(foundItem);
        } else {
            wantlist.removeItem(foundItem);
        }
        System.out.println("Removed item:" + foundItem.getName());
    }


    //EFFECTS: process changing an item
    private void changeOptions() {
        changeMenu();

        String changeWhat = input.nextLine().toLowerCase();
        isThreeOption(changeWhat, "name", "budget", "priority");

        if (changeWhat.equals("name")) {
            System.out.println("Enter new name for item");
            String name = input.nextLine();
            addName(name);
            System.out.println("Name changed to:" + name);
        } else {
            if (changeWhat.equals("budget")) {
                System.out.println("Enter new budget");
                int budget = input.nextInt();
                addBudget(budget);
                System.out.println("Budget changed to:" + budget);
            } else {
                System.out.println("Select new priority");
                String priority = input.nextLine().toLowerCase();
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
        if (budget <= 0) {
            System.out.println("Budget must be a positive number");
            budget = input.nextInt();
            addBudget(budget);
        }
        item.setBudget(budget);
    }

    //MODIFIES: this
    //EFFECTS: set a priority
    private void selectPriority(String highMediumLow) {
        while (!isThreeOption(highMediumLow, "high", "medium", "low")) {
            System.out.println("Enter Valid Priority");
            highMediumLow = input.next().toLowerCase();
        }
        item.setPriority(highMediumLow + " " + "priority");
    }

    //MODIFIES: this
    //EFFECTS: add to the need or want list given the input
    private void addToList(String needOrWant) {
        notTwoOption(needOrWant, "need", "want");

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

        if (priority.equals("high priority")) {
            System.out.println("High Priority:" + " " + name + "," + " $" + budget);
        } else {
            if (priority.equals("medium priority")) {
                System.out.println(
                        "Medium Priority:" + " " + name + "," + " $" + budget);
            } else {
                System.out.println("Low Priority:" + " " + name + "," + " $" + budget);
            }
        }
    }


    //EFFECTS: process invalid user input when two options were given
    private void notTwoOption(String selection, String option1, String option2) {
        while (!(selection.equals(option1) || selection.equals(option2))) {
            String capitalOption1 = capitalizeFirstLetter(option1);
            String capitalOption2 = capitalizeFirstLetter(option2);

            System.out.println("Enter " + capitalOption1 + " or " + capitalOption2);
            selection = input.next().toLowerCase();
        }
    }

    //EFFECTS: process invalid user input when three options were given
    private boolean isThreeOption(String selected, String option1, String option2, String option3) {
        if (selected.equals(option1) || selected.equals(option2)) {
            return true;
        } else {
            return selected.equals(option3);
        }
    }


    //EFFECT: Capitalize the first letter
    private String capitalizeFirstLetter(String string) {
        String firstLetter = string.substring(0, 1).toUpperCase();
        String rest = string.substring(1);
        return firstLetter + rest;
    }

}