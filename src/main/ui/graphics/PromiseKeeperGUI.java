package ui.graphics;

import model.BoughtWantList;
import model.NeedList;
import model.WantList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;


public class PromiseKeeperGUI extends JFrame implements ActionListener {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    public static final String NEED = "Need List";
    public static final String WANT = "Want List";
    public static final String BWANT = "Bought Wanted Items";

    private static final String COL1 = "Name";
    private static final String COL2 = "Budget";
    private static final String COL3 = "Priority";

    private NeedList needList;
    private WantList wantList;
    private BoughtWantList boughtWantList;

    JPanel menu;
    JPanel panel;

    private TableModel needDT;
    private TableModel wantDT;
    private TableModel boughtWantDT;


    public PromiseKeeperGUI() {
        super("Promise Keeper");
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        starterMenu();
        displayList();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void starterMenu() {
        menu = new JPanel(new GridLayout());

        JButton add = new JButton("Add Item");
        JButton remove = new JButton("Remove Item");
        JButton save = new JButton("Save");
        JButton load = new JButton("Load Previous Data");

        add.addActionListener(this);
        add.setActionCommand("add");
        remove.addActionListener(this);
        remove.setActionCommand("remove");
        save.addActionListener(this);
        save.setActionCommand("save");
        load.addActionListener(this);
        load.setActionCommand("load");

        menu.add(add);
        menu.add(remove);
        menu.add(save);
        menu.add(load);

        add(menu, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add")) {
            addProcessUserInput();
        } else {
            if (e.getActionCommand().equals("remove")) {
                JOptionPane.showInputDialog("Enter the name of item you want to remove");
                JOptionPane.showInputDialog("From which list do you want to remove from?: need, want");
            } else {
                if (e.getActionCommand().equals("save")) {
                    JOptionPane.showInputDialog("save");
                } else {
                    JOptionPane.showInputDialog("load");
                }
            }
        }
    }

    public void addProcessUserInput() {
        String listInput = JOptionPane.showInputDialog("Which list do you want to add it to? : need, want, bought")
                .toLowerCase();
        String nameInput = JOptionPane.showInputDialog("What is the name of the item?").toLowerCase();
        String priorityInput = JOptionPane.showInputDialog("Enter Priority: high, medium, low").toLowerCase();
        String budgetInput = JOptionPane.showInputDialog("What is your budget?");

        if ((listInput != null && nameInput != null) && (priorityInput != null && budgetInput != null)) {
            int budget = Integer.parseInt(budgetInput);
            addItem(listInput, nameInput, budget, priorityInput);
        }
    }

    public void addItem(String listInput, String name, int budget, String priority) {
        if (listInput.equals("need")) {
            needDT.add(name, budget, priority);
        }
        if (listInput.equals("want")) {
            wantDT.add(name, budget, priority);
        }
        if (listInput.equals("bought")) {
            String priceInput = JOptionPane.showInputDialog("How much Did you pay?").toLowerCase();
            if (priceInput != null) {
                int price = Integer.parseInt(priceInput);
                boughtWantDT.add(name, budget, priority, price);
            }
        }
    }


    public void displayList() {
        initTables();

        JPanel needPanel = needDT.displayNeedList(NEED);
        JPanel wantPanel = wantDT.displayWantList(WANT);
        JPanel boughtWantPanel = boughtWantDT.displayBoughtWantList(BWANT);

        panel = new JPanel(new GridLayout());
        panel.add(needPanel);
        panel.add(wantPanel);
        panel.add(boughtWantPanel);
        add(panel);
    }

    public void initTables() {
        needDT = new TableModel();
        wantDT = new TableModel();
        boughtWantDT = new TableModel();
    }

}
