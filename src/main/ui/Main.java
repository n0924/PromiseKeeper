package ui;

import model.Item;
import model.NeedList1;

public class Main {
    public static void main(String[] args) {
        String name = "apple";
        int budget = 12;
        Item item = new Item(name,budget);
        NeedList1 wantList = new NeedList1();
        wantList.addItem(item);
    }
}
