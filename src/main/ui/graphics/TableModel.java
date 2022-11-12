package ui.graphics;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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
}
