package ui.graphics;

import model.BoughtWantList;
import model.Item;
import model.NeedList;
import model.WantList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

//Represents the tables for the need, want, bought want lists
public class TableModel extends DefaultTableModel {
    private static final String COL1 = "Name";
    private static final String COL2 = "Budget";
    private static final String COL3 = "Priority";
    private static final String COL4 = "Price";

    //MODIFIES: this
    //EFFECTS: return the graphical representation of need list
    public JPanel displayNeedList(String list) {
        JPanel needPanel = new JPanel(new GridLayout());
        addColumn(COL1);
        addColumn(COL2);
        addColumn(COL3);
        JTable needTable = new JTable(this);
        JScrollPane sp = new JScrollPane(needTable);
        needPanel.add(sp);
        needPanel.setBorder(BorderFactory.createTitledBorder(list));

        return needPanel;
    }

    //MODIFIES: this
    //EFFECTS: return the graphical representation of want list
    public JPanel displayWantList(String list) {
        JPanel wantPanel = new JPanel(new GridLayout());
        addColumn(COL1);
        addColumn(COL2);
        addColumn(COL3);
        JTable wantTable = new JTable(this);
        JScrollPane sp = new JScrollPane(wantTable);
        wantPanel.add(sp);
        wantPanel.setBorder(BorderFactory.createTitledBorder(list));

        return wantPanel;
    }

    //MODIFIES: this
    //EFFECTS: return the graphical representation of bought-want list
    public JPanel displayBoughtWantList(String list) {
        JPanel boughtWantPanel = new JPanel(new GridLayout());
        addColumn(COL1);
        addColumn(COL2);
        addColumn(COL3);
        addColumn(COL4);
        JTable boughtWantTable = new JTable(this);
        JScrollPane sp = new JScrollPane(boughtWantTable);
        boughtWantPanel.add(sp);
        boughtWantPanel.setBorder(BorderFactory.createTitledBorder(list));

        return boughtWantPanel;
    }

    //MODIFIES: this
    //EFFECTS: add an item with name, budget, priority
    public void add(String name, int budget, String priority) {
        Object[] newItem = new Object[]{name, budget, priority};
        addRow(newItem);
    }

    //MODIFIES: this
    //EFFECTS: add an item name, budget, priority, price
    public void add(String name, int budget, String priority, int price) {
        Object[] newItem = new Object[]{name, budget, priority, price};
        addRow(newItem);
    }


    //REQUIRES: 'this' is a need table
    //EFFECT: convert the needlist object to TableModel object
    public void convertNeedListToTableModel(NeedList needList) {
        List<Item> needs = needList.getList();
        for (Item item : needs) {
            String name = item.getName();
            int budget = item.getBudget();
            String priority = item.getPriority();

            add(name, budget, priority);
        }
    }

    //REQUIRES: 'this' is a want table
    //EFFECT: convert the needlist object to TableModel object
    public void convertWantListToTableModel(WantList wantList) {
        List<Item> wants = wantList.getList();
        for (Item item : wants) {
            String name = item.getName();
            int budget = item.getBudget();
            String priority = item.getPriority();

            add(name, budget, priority);
        }
    }

    //REQUIRES: 'this' is a bought-wanted table
    //EFFECT: convert the needlist object to TableModel object
    public void convertBoughtWantListToTableModel(BoughtWantList boughtWantList) {
        List<Item> bwants = boughtWantList.getBoughtList();
        for (Item item : bwants) {
            String name = item.getName();
            int budget = item.getBudget();
            String priority = item.getPriority();
            int price = item.getPrice();

            add(name, budget, priority, price);
        }
    }

    //REQUIRES: 'this' is a need table
    //EFFECTS: convert the need table to a needList
    public void convertNeedTableToList(NeedList needList) {
        int numRow = getRowCount() - 1;

        for (int row = 0; row <= numRow; row++) {
            Item item = rowToItem(row);
            needList.addItem(item);
        }
    }

    //REQUIRES: 'this' is a want table
    //EFFECTS: convert the want table to a wantList
    public void convertWantTableToList(WantList wantList) {
        int numRow = getRowCount() - 1;

        for (int row = 0; row <= numRow; row++) {
            Item item = rowToItem(row);
            wantList.addItem(item);
        }
    }

    //REQUIRES: 'this' is a boughtWant table
    //EFFECTS: convert the bought Want table to a boughtWantList
    public void convertBoughtWantTableToList(BoughtWantList boughtWantList) {
        int numRow = getRowCount() - 1;

        for (int row = 0; row <= numRow; row++) {
            int price = (Integer) getValueAt(row, 3);
            Item item = rowToItem(row);
            boughtWantList.addBought(item, price);
        }
    }


    //EFFECTS: converts a row of a table into a Item object
    public Item rowToItem(int row) {
        String name = (String) getValueAt(row, 0);
        int budget = (Integer) getValueAt(row, 1);
        String priority = (String) getValueAt(row, 2);

        Item item = new Item();
        item.setName(name);
        item.setBudget(budget);
        item.setPriority(priority);

        return item;
    }
}



