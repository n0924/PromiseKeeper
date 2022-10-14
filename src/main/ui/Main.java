package ui;

import model.Item;
import model.NeedList;

public class Main {
    public static void main(String[] args) {
        String name = "apple";
        int budget = 12;
        Item item = new Item(name,budget);
        NeedList wantList = new NeedList();
        wantList.addItem(item);
    }
}
