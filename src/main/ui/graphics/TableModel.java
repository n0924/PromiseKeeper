package ui.graphics;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TableModel extends DefaultTableModel {
    private static final String COL1 = "Name";
    private static final String COL2 = "Budget";
    private static final String COL3 = "Priority";
    private static final String COL4 = "Price";

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

    public void add(String name, int budget, String priority) {
        Object[] newItem = new Object[]{name, budget, priority};
        addRow(newItem);
    }

    public void add(String name, int budget, String priority, int price) {
        Object[] newItem = new Object[]{name, budget, priority, price};
        addRow(newItem);
    }
}
