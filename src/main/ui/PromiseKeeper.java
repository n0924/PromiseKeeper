package ui;

import model.Item;
import model.NeedList;
import model.WantList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PromiseKeeper {
    private WantList wantList;
    private NeedList needList;
    private Item item;
    private Scanner input;


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

        wantList = new WantList();
        needList = new NeedList();
        input = new Scanner(System.in);

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
            } else {
                if (command.equals("display")) {
                    display();
                } else {
                    if (command.equals("bought")) {
                        bought();
                    } else {
                        System.out.println("Please Enter a valid option");
                    }
                }

            }
        }
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
        item = new Item();

        System.out.println("Enter the name of the item");
        String name = input.next();
        addName(name);

        System.out.println("Do you need the item or want the item?");
        String needOrWant = input.next();
        addToList(needOrWant);

        System.out.println("Enter the budget ($)");
        int budget = input.nextInt();
        addBudget(budget);

        System.out.println("Select Priority from");
        System.out.println("\t High Priority");
        System.out.println("\t Medium Priority");
        System.out.println("\t Low Priority");
        String highMediumLow = input.next().toLowerCase();
        selectPriority(highMediumLow);

        showAddedItem();
    }


    //MODIFIES: this
    //EFFECTS: edits an item
    private void edit() {
        System.out.println("Is the item needed or wanted?");
        String needOrWant = input.next().toLowerCase();
        notTwoOption(needOrWant, "need", "want");

        System.out.println("Which item do you want to edit?");
        String editItemName = input.next();
        getItemFromList(needOrWant, editItemName);

        System.out.println("Do you want to remove or change this item?");
        String removeOrChange = input.next().toLowerCase();
        notTwoOption(removeOrChange, "remove", "change");
        toRemoveOrChange(removeOrChange, needOrWant, editItemName);
    }

    //EFFECTS: display lists
    private void display() {
        displayMenu();
        String selection = input.next().toLowerCase();

        if (selection.equals("need")) {
            displayNeed();
        } else {
            if (selection.equals("want")) {
                displayWant();
            } else {
                displayBought();
            }
        }
    }

    //EFFECTS: show the need list
    private void displayNeed() {
        System.out.println("Do you want to filter by priority?");
        String yesOrNo = input.next().toLowerCase();
        notTwoOption(yesOrNo, "yes", "no");
        if (yesOrNo.equals("yes")) {
            System.out.println("Select priority");
            String priority = input.next().toLowerCase();
            selectPriority(priority);
            List<Item> filteredList = needList.filterByPriority(priority);
            List<String> itemsString = convertToString(filteredList);
            System.out.println(itemsString);
        } else {
            List<String> itemsString = convertToString(needList.getList());
            System.out.println(itemsString);
        }
    }

    //EFFECTS: show the want list
    private void displayWant() {
        System.out.println("Do you want to filter by priority?");
        String yesOrNo = input.next().toLowerCase();
        notTwoOption(yesOrNo, "yes", "no");
        if (yesOrNo.equals("yes")) {
            System.out.println("Select priority");
            String priority = input.next().toLowerCase();
            selectPriority(priority);
            List<Item> filteredWantList = wantList.filterByPriority(priority);
            List<String> itemsString = convertToString(filteredWantList);
            System.out.println(itemsString);
        } else {
            List<String> itemsString = convertToString(wantList.getList());
            System.out.println(itemsString);
        }
    }

    //EFFECTS: convert all elements in given list to strings
    public List<String> convertToString(List<Item> list) {
        List<String> itemDescriptions = new ArrayList<>();
        for (Item item : list) {
            String priority = item.getPriority();
            String name = item.getName();
            int budget = item.getBudget();
            String itemDescription =
                    "[" + priority + ": " + name + ", " + "$" + budget + "]";
            itemDescriptions.add(itemDescription);
        }
        return itemDescriptions;
    }

    //EFFECTS: show the Bought list
    private void displayBought() {
        System.out.println("do you want to ");
    }


    //EFFECTS: show the display menu
    private void displayMenu() {
        System.out.println("Select from:");
        System.out.println("\t need");
        System.out.println("\t want");
        System.out.println("\t bought");
    }

    //EFFECTS: add a bought item
    private void bought() {
        //stub
    }


    //EFFECTS: find the item that gets edited/removed
    private void getItemFromList(String needOrWant, String editItemName) {
        if (needOrWant.equals("need")) {
            if (!needList.inList(editItemName)) {
                notInList(needOrWant);
            }
        } else {
            if (!wantList.inList(editItemName)) {
                notInList(needOrWant);
            }
        }
    }


    //EFFECTS: process when item is not in the list
    private void notInList(String needOrWant) {
        System.out.println("The item does not exist. Try another name");
        String nextStep = input.next().toLowerCase();
        if (!nextStep.equals("cancel")) {
            getItemFromList(needOrWant, nextStep);
        }
    }

    //EFFECTS: process whether to remove or change from need or want list
    private void toRemoveOrChange(String removeOrChange, String needOrWant, String editItemName) {
        if (removeOrChange.equals("remove")) {
            remove(needOrWant, editItemName);
        } else {
            changeOptions(needOrWant, editItemName);
        }
    }


    //MODIFIES: this
    //EFFECTS: remove an item from a list
    private void remove(String selection, String editItemName) {
        if (selection.equals("need")) {
            Item itemRemoveN = needList.getItem(editItemName);
            needList.removeItem(itemRemoveN);
            System.out.println("Item successfully removed");
        } else {
            Item itemRemoveW = wantList.getItem(editItemName);
            wantList.removeItem(itemRemoveW);
            System.out.println("Item successfully removed");
        }
        System.out.println("Removed item: " + editItemName);
    }


    //EFFECTS: process changing an item
    private void changeOptions(String needOrWant, String editItemName) {
        Item foundItem;

        if (needOrWant.equals("need")) {
            foundItem = needList.getItem(editItemName);
        } else {
            foundItem = wantList.getItem(editItemName);
        }

        changeMenu();

        String changeWhat = input.next().toLowerCase();
        isThreeOption(changeWhat, "name", "budget", "priority");

        if (changeWhat.equals("name")) {
            changeName(foundItem);
        } else {
            if (changeWhat.equals("budget")) {
                changeBudget(foundItem);
            } else {
                changePriority(foundItem);
            }

        }
    }

    //MODIFIES: foundItem
    //EFFECS: change name to inputted name
    private void changeName(Item foundItem) {
        System.out.println("Enter new name for item");
        String name = input.next();
        foundItem.setName(name);
        System.out.println("Name changed to: " + name);
    }


    //MODIFIES: foundItem
    //EFFECTS: change budget to inputted budget
    private void changeBudget(Item foundItem) {
        System.out.println("Enter new budget");
        int budget = input.nextInt();
        foundItem.setBudget(budget);
        System.out.println("Budget changed to: " + budget);
    }

    //REQUIRES:
    //EFFECTS: change priority to inputted priority
    private void changePriority(Item foundItem) {
        System.out.println("Select new priority");
        String priority = input.nextLine().toLowerCase();
        String validPriority = validPriority(priority);
        foundItem.setPriority(validPriority);
        System.out.println("Priority changed to: " + priority);
    }


    //EFFECTS: display options for changing an item description
    private void changeMenu() {
        System.out.println("\n What do you want to change? Choose from");
        System.out.println("\t Name");
        System.out.println("\t Budget");
        System.out.println("\t Priority");
    }

    //MODIFIES: this
    //EFFECTS: set a name to be the given name
    protected void addName(String name) {
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
        validPriority(highMediumLow);
        item.setPriority(highMediumLow + " " + "priority");
    }

    //EFFECTS: return valid priority
    private String validPriority(String highMediumLow) {
        while (!isThreeOption(highMediumLow, "high", "medium", "low")) {
            System.out.println("Enter Valid Priority");
            highMediumLow = input.next().toLowerCase();
        }
        return highMediumLow;
    }

    //MODIFIES: this
    //EFFECTS: add to the need or want list given the input
    private void addToList(String needOrWant) {
        notTwoOption(needOrWant, "need", "want");
        validName();
    }

    //EFFECTS: propmt user to enter another name if it is taken
    private void validName() {
        needList.addItem(item);

        if (!needList.containsItem(item)) {
            System.out.println("Sorry this name is already taken. Enter another name");
            String newName = input.next();
            item.setName(newName);
            validName();
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