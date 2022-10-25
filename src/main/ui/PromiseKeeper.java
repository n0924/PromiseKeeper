package ui;

import model.BoughtWantList;
import model.Item;
import model.NeedList;
import model.WantList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// User Interaction of Promise Keepr
//Some parts of this code was inspired by the TellerApp()

public class PromiseKeeper {
    private static final String needListFile = "./data/needList.json";
    private static final String wantListFile = "./data/wantList.json";
    private static final String boughtWantListFile = "./data/boughtWantList.json";

    private WantList wantList;
    private NeedList needList;
    private BoughtWantList boughtWantList;
    private Item item;
    private Scanner input;
    private JsonReader jsonReaderNeed;
    private JsonReader jsonReaderWant;
    private JsonReader jsonReaderBoughtWant;
    private JsonWriter jsonWriterNeed;
    private JsonWriter jsonWriterWant;
    private JsonWriter jsonWriterBoughtWant;

    //EFFECTS: run the PromiseKeeper app
    public PromiseKeeper() {
        jsonReaderNeed = new JsonReader(needListFile);
        jsonReaderWant = new JsonReader(wantListFile);
        jsonReaderBoughtWant = new JsonReader(boughtWantListFile);
        jsonWriterNeed = new JsonWriter(needListFile);
        jsonWriterWant = new JsonWriter(wantListFile);
        jsonWriterBoughtWant = new JsonWriter(boughtWantListFile);
        runApp();
    }


    //CITE:Inspired by CPSC210/TellerApp()
    //MODIFIES: this
    //EFFECTS: process user inputs
    private void runApp() {
        boolean keepGoing = true;
        input = new Scanner(System.in);

        wantList = new WantList();
        needList = new NeedList();
        boughtWantList = new BoughtWantList();

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
                        if (command.equals("load")) {
                            load();
                        } else {
                            if (command.equals("save")) {
                                save();
                            }

                        }
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
        System.out.println("\tLoad");
        System.out.println("\tSave");
        System.out.println("\tQuit");
    }

    //MODIFIES: this
    //EFFECTS: adds an item to appropriate list
    private void addItem() {
        item = new Item();

        System.out.println("Enter the name of the item");
        String name = input.next();

        System.out.println("Do you need the item or want the item?");
        String needOrWant = input.next().toLowerCase();
        addToList(needOrWant, name);

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
    //EFFECTS: add a bought wanted item to boughWant list, and remove bought need and want item
    // from need and want lists, respectively
    private void bought() {
        System.out.println("Which item did you buy?");
        String inputName = input.next();
        System.out.println("Was the item needed or wanted?");
        String inputNeedOrWant = input.next().toLowerCase();
        String validNeedorWant = notTwoOption(inputNeedOrWant, "need", "want");
        String validName = nameExistInList(validNeedorWant, inputName);

        System.out.println("How much did you pay?");
        int inputPrice = input.nextInt();
        int validPrice = validPrice(inputPrice);

        if (validNeedorWant.equals("want")) {
            boughtWantList.addBought(wantList.getItem(validName), validPrice);
        }

        remove(validNeedorWant, validName);
    }


    //MODIFIES: this
    //EFFECTS: edits an item description or remove from lists
    private void edit() {
        System.out.println("Is the item needed or wanted?");
        String needOrWant = input.next().toLowerCase();
        String validNeedOrWant = notTwoOption(needOrWant, "need", "want");

        System.out.println("Which item do you want to edit?");
        String inputName = input.next();
        String validName = nameExistInList(validNeedOrWant, inputName);

        System.out.println("Do you want to remove or change this item?");
        String removeOrChange = input.next().toLowerCase();
        String validRemoveOrChange = notTwoOption(removeOrChange, "remove", "change");
        toRemoveOrChange(validRemoveOrChange, validNeedOrWant, validName);
    }

    //EFFECTS: display lists or amounts spent on wanted items
    private void display() {
        displayMenu();
        String selection = input.next().toLowerCase();
        validSelection(selection, "need", "want", "bought");


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

    //MODFIES: this
    //EFFECTS: load data that was previously saved
    private void load() {
        try {
            needList = jsonReaderNeed.readNeed();
            wantList = jsonReaderWant.readWant();
            boughtWantList = jsonReaderBoughtWant.readBoughtWant();
            System.out.println("Loaded" + "\n Need list from " + needListFile + ","
                    + "\n Want list from " + wantListFile + ","
                    + "\n Bought want list from " + boughtWantListFile);
        } catch (IOException e) {
            System.out.println("Unable to load data");
        }
    }


    //MODIFIES: this
    //EFFECTS: save data as JSON2
    private void save() {
        try {
            jsonWriterNeed.open();
            jsonWriterNeed.writeNeed(needList);
            jsonWriterNeed.close();

            jsonWriterWant.open();
            jsonWriterWant.writeWant(wantList);
            jsonWriterWant.close();

            jsonWriterBoughtWant.open();
            jsonWriterBoughtWant.writeBoughtWant(boughtWantList);
            jsonWriterBoughtWant.close();

            System.out.println("Saved" + "\n Need list to " + needListFile + ","
                    + "\n Want list to " + wantListFile + ","
                    + "\n Bought wanted list to " + boughtWantListFile);
        } catch (IOException e) {
            System.out.println("Unable to save data");
        }
    }

    //EFFECTS: show the need list
    private void displayNeed() {
        System.out.println("Do you want to filter by priority?");
        String yesOrNo = input.next().toLowerCase();
        String validYesOrNo = notTwoOption(yesOrNo, "yes", "no");
        if (validYesOrNo.equals("yes")) {
            System.out.println("Select priority");
            String priority = input.next().toLowerCase();
            String validPriority = validPriority(priority);
            List<Item> filterList = needList.filterByPriority(validPriority);
            List<String> itemsString = convertToString(filterList);
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
        String validYesOrNo = notTwoOption(yesOrNo, "yes", "no");
        if (validYesOrNo.equals("yes")) {
            System.out.println("Select priority");
            String priority = input.next().toLowerCase();
            String validPriority = validPriority(priority);
            List<Item> filteredWantList = wantList.filterByPriority(validPriority);
            List<String> itemsString = convertToString(filteredWantList);
            System.out.println(itemsString);
        } else {
            List<String> itemsString = convertToString(wantList.getList());
            System.out.println(itemsString);
        }
    }

    //EFFECTS: convert all elements in given list to strings
    private List<String> convertToString(List<Item> list) {
        List<String> itemDescriptions = new ArrayList<>();
        for (Item item : list) {
            String priority = item.getPriority();
            String name = item.getName();
            int budget = item.getBudget();
            String itemDescription =
                    "[" + priority + " priority: " + name + ", " + "$" + budget + "]";
            itemDescriptions.add(itemDescription);
        }
        return itemDescriptions;
    }

    //EFFECTS: show the Bought list
    private void displayBought() {
        System.out.println("I want to see..  ");
        System.out.println("\t list: List of wanted items bought");
        System.out.println("\t total: Total amount spent on wanted items");
        System.out.println("\t overspent: Amount spent above budget on wanted items");
        String selection = input.next().toLowerCase();
        String validSelection = validSelection(selection, "list", "total", "overspent");
        if (validSelection.equals("list")) {
            List<Item> boughtWants = boughtWantList.getBoughtList();
            List<String> boughtStrings = convertToString(boughtWants);
            System.out.println(boughtStrings);
        } else {
            if (validSelection.equals("total")) {
                System.out.println(boughtWantList.getTotalPrice());
            } else {
                System.out.println(boughtWantList.getTotalOverspent());
            }
        }
    }


    //EFFECTS: process invalid input not shown on menu
    private String validSelection(String selection, String option1, String option2, String option3) {
        while (!isThreeOption(selection, option1, option2, option3)) {
            System.out.println("Please enter a valid option");
            selection = input.next().toLowerCase();
        }
        return selection;
    }

    //EFFECTS: show the display menu
    private void displayMenu() {
        System.out.println("Select from:");
        System.out.println("\t Need");
        System.out.println("\t Want");
        System.out.println("\t Bought (wanted items)");
    }


    //EFFECTS: process invalid price input
    private int validPrice(int price) {
        while (price < 0) {
            System.out.println("Price must be positive");
            price = input.nextInt();
        }
        return price;
    }

    //EFFECTS: produce name that exists in list
    private String nameExistInList(String needOrWant, String itemName) {
        String validName;

        if (needOrWant.equals("need")) {
            validName = notInNeedList(itemName);
        } else {
            validName = notInWantList(itemName);
        }
        return validName;
    }


    //EFFECTS: process when item name does not exist in the need list
    private String notInNeedList(String itemName) {
        while (!needList.inList(itemName)) {
            System.out.println("The item does not exist. Try another name");
            itemName = input.next();
        }
        return itemName;
    }

    //EFFECTS: process when item name does not exist in the want list
    private String notInWantList(String itemName) {
        while (!wantList.inList(itemName)) {
            System.out.println("The item does not exist. Try another name");
            itemName = input.next();
        }
        return itemName;
    }

    //EFFECTS: process whether to remove or change from need or want list
    private void toRemoveOrChange(String removeOrChange, String needOrWant, String editItemName) {
        if (removeOrChange.equals("remove")) {
            remove(needOrWant, editItemName);
            System.out.println("Item " + editItemName + " was successfully removed");
        } else {
            changeOptions(needOrWant, editItemName);
        }
    }


    //MODIFIES: this
    //EFFECTS: remove an item from a list
    private void remove(String needOrWant, String editItemName) {
        if (needOrWant.equals("need")) {
            Item itemRemoveN = needList.getItem(editItemName);
            needList.removeItem(itemRemoveN);
        } else {
            Item itemRemoveW = wantList.getItem(editItemName);
            wantList.removeItem(itemRemoveW);
        }
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
        while (!isThreeOption(changeWhat, "name", "budget", "priority")) {
            System.out.println("Enter valid option");
            changeWhat = input.next().toLowerCase();
        }

        if (changeWhat.equals("name")) {
            changeName(foundItem, needOrWant);
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
    private void changeName(Item foundItem, String needOrWant) {
        String validName;
        System.out.println("Enter new name");
        String newName = input.next();
        validName = nonDuplicatedName(needOrWant, newName);
        foundItem.setName(validName);
        System.out.println("Name changed to: " + validName);
    }


    //MODIFIES: foundItem
    //EFFECTS: change budget to inputted budget
    private void changeBudget(Item foundItem) {
        System.out.println("Enter new budget");
        int budget = input.nextInt();
        foundItem.setBudget(budget);
        System.out.println("Budget changed to: " + budget);
    }

    //MODIFIES: foundItem
    //EFFECTS: change priority to inputted priority
    private void changePriority(Item foundItem) {
        System.out.println("Select new priority");
        String priority = input.next().toLowerCase();
        String validPriority = validPriority(priority);
        foundItem.setPriority(validPriority);
        System.out.println("Priority changed to: " + validPriority + " priority");
    }


    //EFFECTS: display options for changing an item description
    private void changeMenu() {
        System.out.println("\n What do you want to change? Choose from");
        System.out.println("\t Name");
        System.out.println("\t Budget");
        System.out.println("\t Priority");
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
        String validPriority = validPriority(highMediumLow);
        item.setPriority(validPriority);
    }

    //EFFECTS: return valid priority
    private String validPriority(String priority) {
        while (!isThreeOption(priority, "high", "medium", "low")) {
            System.out.println("Enter Valid Priority");
            priority = input.next().toLowerCase();
        }
        return priority;
    }

    //MODIFIES: this
    //EFFECTS: add to the need or want list given the input
    private void addToList(String needOrWant, String name) {
        String validNeedOrWant = notTwoOption(needOrWant, "need", "want");
        addNonDuplicatedItem(validNeedOrWant, name);
    }

    //EFFECTS: add item with a non-duplicated name
    private void addNonDuplicatedItem(String needOrWant, String name) {
        item.setName(nonDuplicatedName(needOrWant, name));
        if (needOrWant.equals("need")) {
            needList.addItem(item);
        } else {
            wantList.addItem(item);
        }
    }

    //EFFECTS: process name input
    private String nonDuplicatedName(String needOrWant, String name) {
        if (needOrWant.equals("need")) {
            List<String> needNames = needList.toName();
            while (needNames.contains(name)) {
                System.out.println("Sorry this name is already taken. Enter another name");
                name = input.next();
            }
        } else {
            List<String> wantNames = wantList.toName();
            while (wantNames.contains(name)) {
                System.out.println("Sorry this name is already taken. Enter another name");
                name = input.next();
            }
        }

        return name;
    }


    //EFFECT: show the added item
    private void showAddedItem() {
        String name = item.getName();
        String priority = item.getPriority();
        int budget = item.getBudget();

        if (priority.equals("high")) {
            System.out.println("High Priority:" + " " + name + "," + " $" + budget);
        } else {
            if (priority.equals("medium")) {
                System.out.println(
                        "Medium Priority:" + " " + name + "," + " $" + budget);
            } else {
                System.out.println("Low Priority:" + " " + name + "," + " $" + budget);
            }
        }
    }


    //EFFECTS: process invalid user input when two options were given
    private String notTwoOption(String selection, String option1, String option2) {
        while (!(selection.equals(option1) || selection.equals(option2))) {
            String capitalOption1 = capitalizeFirstLetter(option1);
            String capitalOption2 = capitalizeFirstLetter(option2);

            System.out.println("Enter " + capitalOption1 + " or " + capitalOption2);
            selection = input.next().toLowerCase();
        }
        return selection;
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