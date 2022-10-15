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

    //Some parts of this code was inspired by the TellerApp()
    //EFFECTS: run the PromiseKeeper app
    public PromiseKeeper() {
        runApp();
    }

    //CITE: CPSC210/TellerApp()
    //MODIFES: this
    //EFFECTS: process user input
    private void runApp() {
        boolean keepGoing = true;
        String input = null;

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
        System.out.println("Bye");
    }


    //CITE: CPSC210/TellerApp()
    //MODIFIES: process user input
    private void processInput(String input) {
        if (input.equals("Add")) {
            addItem();
        }
        //if (input.equals("Edit")) {
        //    editItem();
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
        System.out.println("\tEdit");
        System.out.println("\tDisplay");
        System.out.println("\tQuit");
    }

    //MODIFIES: this
    //EFFECTS: adds an item to appriopriate list
    private void addItem() {
        System.out.println("Enter the name of the item");
        String name = input.nextLine();
        item.setName(name);

        System.out.println("Enter the budget ($)");
        addBudget();

        System.out.println("\n Select Priority from");
        System.out.println("\t High Priority");
        System.out.println("\t Medium Priority");
        System.out.println("\t Low Priority");
        selectPriority();

        System.out.println("Do you need the item or want the item?");
        selectPriority();

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

    //MODIFIES: this
    //EFFECTS: add a budget to an item
    private void addBudget() {
        int budget = input.nextInt();
        while (budget <= 0) {
            System.out.println("Budget must be a positive number");
        }
        item.setBudget(budget);
    }

    //MODIFIES: this
    //EFFECTS: select a priority for the item
    private void selectPriority() {

        String priority = "";
        while (!(priority.equals("high priority") || priority.equals("medium priority")
                || priority.equals("low priority"))) {
            System.out.println("\n Please select Priority from");
            System.out.println("\t High Priority");
            System.out.println("\t Medium Priority");
            System.out.println("\t Low Priority");
            priority = input.nextLine();
            priority = priority.toLowerCase();
        }

        item.setPriority(priority);
    }

    //MODIFIES: this
    //EFFECTS: select whether its needed or wanted
    private void selectNeedOrWant() {
        String needOrWant = "";
        while (!(needOrWant.equals("Need") || needOrWant.equals("Want"))) {
            System.out.println("Enter need or want ");
            needOrWant = input.next();
            needOrWant = needOrWant.toLowerCase();
        }

        if (needOrWant.equals("need")) {
            needlist.addItem(item);
        } else {
            wantlist.addItem(item);
        }
    }



        //MODIFIES: this
        // edit an item
        //private void editItem() {
        //    System.out.println("\n Need or Want?");
        // }

        //MODIFIES: this
        //EFFECTS: display the selected list
        //private void displayList () {

        //}


        //}


    }