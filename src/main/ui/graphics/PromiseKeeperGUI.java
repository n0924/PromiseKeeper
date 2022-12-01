package ui.graphics;

import model.*;
import model.Event;
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
        initLists();
        splashScreen();
        designLayout();
        displayList();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        remove(splashScreen);
    }

    //MODIFIES: this
    //EFFECTS: design the layout of the main panel
    public void designLayout() {
        JPanel menu = new JPanel(new GridLayout());
        menu.setBackground(Color.lightGray);

        createButtons(menu);

        add(menu, BorderLayout.NORTH);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventLog events = EventLog.getInstance();
                for (Event event : events) {
                    System.out.println(event.getDescription() + " at " + event.getDate());
                }
                System.exit(0);
            }
        });
    }

    //MODIFIES: this
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

    //MODIFIES: TableModel Objects, this
    //EFFECTS: Process user input when adding an item, no duplicated name is allowed
    public void addProcessUserInput() {
        String nameInput;

        String tableInput = JOptionPane.showInputDialog("Which table do you want to add it to? : need, want, bought")
                .toLowerCase();
        nameInput = JOptionPane.showInputDialog("What is the name of the item?").toLowerCase();

        try {
            verifyValidName(nameInput, tableInput);
            String priorityInput = JOptionPane.showInputDialog("Enter Priority: high, medium, low").toLowerCase();
            String budgetInput = JOptionPane.showInputDialog("What is/was your budget?");

            if ((tableInput != null && nameInput != null) && (priorityInput != null && budgetInput != null)) {
                int budget = Integer.parseInt(budgetInput);
                addItem(tableInput, nameInput, budget, priorityInput);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Sorry, the name is already taken.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    //MODIFIES: TableModel objects, this
    //EFFECTS: process user input to remove item
    public void removeProcessUserInput() {
        String listInput = JOptionPane.showInputDialog("Which list does this item belong to?: need, want")
                .toLowerCase();
        String indexInput = JOptionPane.showInputDialog("Enter the index of the item you want to remove");
        if (listInput != null && indexInput != null) {
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
            activateJsonWriter();
        }
    }

    //MODIFIES: TableModel Object, this
    //EFFECTS: process user input to load item
    public void loadProcessUserInput() {
        int yesOrNo = JOptionPane.showConfirmDialog(null,
                "Load previous data?", "Load Option", JOptionPane.OK_CANCEL_OPTION);
        if (yesOrNo == JOptionPane.OK_OPTION) {
            activateJsonReader();
        }
    }

    //MODIFIES: this
    //EFFECTS: creates the buttons on JPanel
    public void createButtons(JPanel menu) {
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
    }

    //MODIFIES: TabelModel objects, List<Item>
    //EFFECT: add an item to the given table
    public void addItem(String tableInput, String name, int budget, String priority) {
        Item item = new Item(name, budget, priority);

        if (tableInput.equals("need")) {
            needDT.add(name, budget, priority);
            needList.addLast(item);
        }
        if (tableInput.equals("want")) {
            wantDT.add(name, budget, priority);
            wantList.addLast(item);
        }
        if (tableInput.equals("bought")) {
            String priceInput = JOptionPane.showInputDialog("How much did you pay?").toLowerCase();
            if (priceInput != null) {
                int price = Integer.parseInt(priceInput);
                item.setPrice(price);
                boughtWantDT.add(name, budget, priority, price);
                boughtWantList.addLast(item);
            }
        }
    }

    //MODIFIES: TabelModel objects
    //EFFECTS: remove an item from the given list
    public void removeItem(int index, String list) {

        if (list.equals("need")) {
            Item item = needDT.rowToItem(index);
            needDT.removeRow(index);
            needList.removeItem(item);
        }
        if (list.equals("want")) {
            Item item = wantDT.rowToItem(index);
            wantDT.removeRow(index);
            wantList.removeItem(item);
        }
    }

    //EFFECTS: activates the Json writers for each lists
    public void activateJsonWriter() {
        try {
            initJson();

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

    //EFFECTS: activates the Json readers for each lists
    public void activateJsonReader() {
        try {
            initJson();
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

    //EFFECTS: throws Exception if the input name is alreadly used.
    public void verifyValidName(String inputName, String list) throws Exception {
        if (list.equals("need")) {
            if (needList.inList(inputName)) {
                throw new Exception();
            }
        }
        if (list.equals("want")) {
            if (wantList.inList(inputName)) {
                throw new Exception();
            }
        }
    }

    //EFFECTS: initialize the lists and its json reader/writer
    public void initJson() {
        jsonReaderNeed = new JsonReader(needListFile);
        jsonReaderWant = new JsonReader(wantListFile);
        jsonReaderBoughtWant = new JsonReader(boughtWantListFile);
        jsonWriterNeed = new JsonWriter(needListFile);
        jsonWriterWant = new JsonWriter(wantListFile);
        jsonWriterBoughtWant = new JsonWriter(boughtWantListFile);
    }

    //EFFECTS: constructs instances of TableModel
    public void initTables() {
        needDT = new TableModel();
        wantDT = new TableModel();
        boughtWantDT = new TableModel();
    }

    //EFFECTS: constructs instances of Lists
    public void initLists() {
        wantList = new WantList();
        needList = new NeedList();
        boughtWantList = new BoughtWantList();
    }
}
