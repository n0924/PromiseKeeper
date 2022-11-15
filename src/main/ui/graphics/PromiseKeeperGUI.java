package ui.graphics;

import model.BoughtWantList;
import model.NeedList;
import model.WantList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


//Graphical User Interface of PromiseKeeper App
public class PromiseKeeperGUI extends JFrame implements ActionListener {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;

    public static final String NEED = "Need List";
    public static final String WANT = "Want List";
    public static final String BWANT = "Bought Wanted Items";

    private static final String needListFile = "./data/needList.json";
    private static final String wantListFile = "./data/wantList.json";
    private static final String boughtWantListFile = "./data/boughtWantList.json";

    private JsonReader jsonReaderNeed;
    private JsonReader jsonReaderWant;
    private JsonReader jsonReaderBoughtWant;
    private JsonWriter jsonWriterNeed;
    private JsonWriter jsonWriterWant;
    private JsonWriter jsonWriterBoughtWant;

    private NeedList needList;
    private WantList wantList;
    private BoughtWantList boughtWantList;

    private TableModel needDT;
    private TableModel wantDT;
    private TableModel boughtWantDT;


    //EFFECTS: run the graphical promise keeper
    public PromiseKeeperGUI() {
        super("Promise Keeper");
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        splashScreen();
        initJson();
        designLayout();
        displayList();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: shows a splash screen
    public void splashScreen() {
        ImageIcon icon = new ImageIcon("data/icon.png");

        JLabel splashScreen = new JLabel("Welcome to PromiseKeeper!", icon, JLabel.CENTER);
        splashScreen.setFont(new Font(Font.MONOSPACED, Font.BOLD, WIDTH / 50));
        splashScreen.setVerticalTextPosition(JLabel.BOTTOM);
        splashScreen.setHorizontalTextPosition(JLabel.CENTER);
        add(splashScreen);
        setVisible(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setVisible(false);
        dispose();
    }


    //EFFECTS: initialize json reader/writer for each lists and the lists
    public void initJson() {
        jsonReaderNeed = new JsonReader(needListFile);
        jsonReaderWant = new JsonReader(wantListFile);
        jsonReaderBoughtWant = new JsonReader(boughtWantListFile);
        jsonWriterNeed = new JsonWriter(needListFile);
        jsonWriterWant = new JsonWriter(wantListFile);
        jsonWriterBoughtWant = new JsonWriter(boughtWantListFile);

        wantList = new WantList();
        needList = new NeedList();
        boughtWantList = new BoughtWantList();
    }


    //MODIFIES: this
    //EFFECTS: design the layout of the main panel
    public void designLayout() {
        JPanel menu = new JPanel(new GridLayout());
        menu.setBackground(Color.lightGray);

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

    //EFFECTS: respond to user clicking buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add")) {
            addProcessUserInput();
        } else {
            if (e.getActionCommand().equals("remove")) {
                removeProcessUserInput();
            } else {
                if (e.getActionCommand().equals("save")) {
                    saveProcessUserInput();
                } else {
                    if (e.getActionCommand().equals("load")) {
                        loadProcessUserInput();
                    }
                }
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: Process user input when adding an item
    public void addProcessUserInput() {
        String listInput = JOptionPane.showInputDialog("Which list do you want to add it to? : need, want, bought")
                .toLowerCase();
        String nameInput = JOptionPane.showInputDialog("What is the name of the item?").toLowerCase();
        String priorityInput = JOptionPane.showInputDialog("Enter Priority: high, medium, low").toLowerCase();
        String budgetInput = JOptionPane.showInputDialog("What is/was your budget?");

        if ((listInput != null && nameInput != null) && (priorityInput != null && budgetInput != null)) {
            int budget = Integer.parseInt(budgetInput);
            addItem(listInput, nameInput, budget, priorityInput);
        }
    }

    //EFFECTS: process user input to remove item
    public void removeProcessUserInput() {
        String listInput = JOptionPane.showInputDialog("Which list does this item belong to?: need, want")
                .toLowerCase();
        String indexInput = JOptionPane.showInputDialog("Enter the index of the item you want to remove");
        if (indexInput != null && listInput != null) {
            int index = Integer.parseInt(indexInput) - 1;
            if (index >= 0) {
                removeItem(index, listInput);
            }
        }
    }

    //EFFECTS: proces user input to save item
    public void saveProcessUserInput() {
        int yesOrNo = JOptionPane.showConfirmDialog(null,
                "Save data?", "Save Option", JOptionPane.OK_CANCEL_OPTION);
        if (yesOrNo == JOptionPane.OK_OPTION) {
            try {
                needDT.convertNeedTableToList(needList);
                wantDT.convertWantTableToList(wantList);
                boughtWantDT.convertBoughtWantTableToList(boughtWantList);

                jsonWriterNeed.open();
                jsonWriterNeed.writeNeed(needList);
                jsonWriterNeed.close();

                jsonWriterWant.open();
                jsonWriterWant.writeWant(wantList);
                jsonWriterWant.close();

                jsonWriterBoughtWant.open();
                jsonWriterBoughtWant.writeBoughtWant(boughtWantList);
                jsonWriterBoughtWant.close();

                JOptionPane.showMessageDialog(null,
                        "Saved successfully", "Save Option", JOptionPane.PLAIN_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Failed saving data", "Save Option", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: process user input to load item
    public void loadProcessUserInput() {
        int yesOrNo = JOptionPane.showConfirmDialog(null,
                "Load previous data?", "Load Option", JOptionPane.OK_CANCEL_OPTION);
        if (yesOrNo == JOptionPane.OK_OPTION) {
            try {
                needList = jsonReaderNeed.readNeed();
                wantList = jsonReaderWant.readWant();
                boughtWantList = jsonReaderBoughtWant.readBoughtWant();
                needDT.convertNeedListToTableModel(needList);
                wantDT.convertWantListToTableModel(wantList);
                boughtWantDT.convertBoughtWantListToTableModel(boughtWantList);
                JOptionPane.showMessageDialog(null,
                        "Loaded successfully", "Load Option", JOptionPane.PLAIN_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Failed loading data", "Load Option", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    //MODIFIES: this
    //EFFECT: add an item to the given list
    public void addItem(String listInput, String name, int budget, String priority) {
        if (listInput.equals("need")) {
            needDT.add(name, budget, priority);
        }
        if (listInput.equals("want")) {
            wantDT.add(name, budget, priority);
        }
        if (listInput.equals("bought")) {
            String priceInput = JOptionPane.showInputDialog("How much did you pay?").toLowerCase();
            if (priceInput != null) {
                int price = Integer.parseInt(priceInput);
                boughtWantDT.add(name, budget, priority, price);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: remove an item from the given list
    public void removeItem(int index, String list) {
        if (list.equals("need")) {
            needDT.removeRow(index);
        }
        if (list.equals("want")) {
            wantDT.removeRow(index);
        }
    }

    //EFFECTS:display the need/want/bought want items list
    public void displayList() {
        initTables();

        JPanel needPanel = needDT.displayNeedList(NEED);
        JPanel wantPanel = wantDT.displayWantList(WANT);
        JPanel boughtWantPanel = boughtWantDT.displayBoughtWantList(BWANT);

        JPanel panel = new JPanel(new GridLayout());
        panel.add(needPanel);
        panel.add(wantPanel);
        panel.add(boughtWantPanel);
        add(panel);
    }

    //EFFECTS: constructs instances of TableModel
    public void initTables() {
        needDT = new TableModel();
        wantDT = new TableModel();
        boughtWantDT = new TableModel();
    }

}
