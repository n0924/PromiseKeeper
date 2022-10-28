package persistence;

import model.BoughtWantList;
import model.Item;
import model.NeedList;
import model.WantList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Tests the writer
public class JsonWriterTest {

    @Test
    void testWriteEmptyNeedList() {
        try {
            NeedList newNeeds = new NeedList();
            JsonWriter writer = new JsonWriter("./data/testEmptyNeedList.json");
            writer.open();
            writer.writeNeed(newNeeds);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyNeedList.json");
            NeedList newJsonNeeds = reader.readNeed();
            assertEquals(0, newJsonNeeds.sizeList());


        } catch (FileNotFoundException e) {
            System.out.println("Unexpected FileNotFoundException caught");
        } catch (IOException e) {
            System.out.println("Unexpected IOException caught");
        }
    }

    @Test
    void testWriteNeedList() {
        try {
            NeedList newNeed = new NeedList();
            Item item1 = new Item();
            Item item2 = new Item();
            Item item3 = new Item();

            item1.setName("need 1");
            item1.setBudget(800);
            item1.setPriority("high");

            item2.setName("need 2");
            item2.setBudget(90);
            item2.setPriority("low");

            item3.setName("need 3");
            item3.setBudget(1);
            item3.setPriority("medium");

            newNeed.addItem(item1);
            newNeed.addItem(item2);
            newNeed.addItem(item3);

            JsonWriter writer = new JsonWriter("./data/testNeedList.json");
            writer.open();
            writer.writeNeed(newNeed);
            writer.close();

            JsonReader reader = new JsonReader("./data/testNeedList.json");
            NeedList needs = reader.readNeed();

            assertEquals(3, needs.sizeList());

            assertEquals("need 3", needs.getItemIndex(0).getName());
            assertEquals(1, needs.getItemIndex(0).getBudget());
            assertEquals("medium", needs.getItemIndex(0).getPriority());
            assertEquals(0, needs.getItemIndex(0).getPrice());

            assertEquals("need 2", needs.getItemIndex(1).getName());
            assertEquals(90, needs.getItemIndex(1).getBudget());
            assertEquals("low", needs.getItemIndex(1).getPriority());
            assertEquals(0, needs.getItemIndex(1).getPrice());

            assertEquals("need 1", needs.getItemIndex(2).getName());
            assertEquals(800, needs.getItemIndex(2).getBudget());
            assertEquals("high", needs.getItemIndex(2).getPriority());
            assertEquals(0, needs.getItemIndex(2).getPrice());


        } catch (IOException e) {
            System.out.println("Unexpected IOException caught");
        }
    }

    @Test
    void testWriteEmptyWantList() {
        try {
            WantList newWants = new WantList();
            JsonWriter writer = new JsonWriter("./data/testEmptyWantList.json");
            writer.open();
            writer.writeWant(newWants);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyWantList.json");
            WantList wants = reader.readWant();
            assertEquals(0, wants.sizeList());
        } catch (IOException e) {
            System.out.println("Unexpected IOException caught");
        }
    }

    @Test
    void testWriteWantList() {
        try {
            WantList newWant = new WantList();
            Item item1 = new Item();
            Item item2 = new Item();
            Item item3 = new Item();

            item1.setName("want 1");
            item1.setBudget(1);
            item1.setPriority("high");

            item2.setName("want 2");
            item2.setBudget(47000);
            item2.setPriority("low");

            item3.setName("want 3");
            item3.setBudget(600);
            item3.setPriority("medium");

            newWant.addItem(item1);
            newWant.addItem(item2);
            newWant.addItem(item3);

            JsonWriter writer = new JsonWriter("./data/testWantList.json");
            writer.open();
            writer.writeWant(newWant);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWantList.json");
            WantList wants = reader.readWant();

            assertEquals(3, wants.sizeList());

            assertEquals("want 3", wants.getItemIndex(0).getName());
            assertEquals(600, wants.getItemIndex(0).getBudget());
            assertEquals("medium", wants.getItemIndex(0).getPriority());
            assertEquals(0, wants.getItemIndex(0).getPrice());

            assertEquals("want 2", wants.getItemIndex(1).getName());
            assertEquals(47000, wants.getItemIndex(1).getBudget());
            assertEquals("low", wants.getItemIndex(1).getPriority());
            assertEquals(0, wants.getItemIndex(1).getPrice());

            assertEquals("want 1", wants.getItemIndex(2).getName());
            assertEquals(1, wants.getItemIndex(2).getBudget());
            assertEquals("high", wants.getItemIndex(2).getPriority());
            assertEquals(0, wants.getItemIndex(2).getPrice());
        } catch (IOException e) {
            System.out.println("Unexpected IOException caught");
        }
    }

    @Test
    void testWriteEmptyBoughtWantList() {
        try {
            BoughtWantList boughtWantList = new BoughtWantList();
            JsonWriter writer = new JsonWriter("./data/testEmptyBoughtWantList.json");
            writer.open();
            writer.writeBoughtWant(boughtWantList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyBoughtWantList.json");
            BoughtWantList boughtWantListJson = reader.readBoughtWant();
            List<Item> boughtWantItem = boughtWantListJson.getBoughtList();
            assertEquals(0, boughtWantItem.size());
            assertEquals(0, boughtWantListJson.getTotalOverspent());
            assertEquals(0, boughtWantListJson.getTotalPrice());
        } catch (IOException e) {
            System.out.println("Unexpected IOException caught");
        }
    }

    @Test
    void testWriteBoughtWantList() {
        try {
            BoughtWantList newBoughtWantList = new BoughtWantList();
            Item item1 = new Item();
            Item item2 = new Item();
            Item item3 = new Item();

            item1.setName("want 1");
            item1.setBudget(820);
            item1.setPriority("low");

            item2.setName("want 2");
            item2.setBudget(1);
            item2.setPriority("high");

            item3.setName("want 3");
            item3.setBudget(90100);
            item3.setPriority("medium");

            newBoughtWantList.addBought(item1, 750);
            newBoughtWantList.addBought(item2, 10);
            newBoughtWantList.addBought(item3, 0);


            JsonWriter writer = new JsonWriter("./data/testBoughtWantList.json");
            writer.open();
            writer.writeBoughtWant(newBoughtWantList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testBoughtWantList.json");
            BoughtWantList boughtWantList = reader.readBoughtWant();
            List<Item> boughtWantItems = boughtWantList.getBoughtList();

            assertEquals(3, boughtWantItems.size());

            assertEquals("want 3", boughtWantItems.get(0).getName());
            assertEquals(90100, boughtWantItems.get(0).getBudget());
            assertEquals("medium", boughtWantItems.get(0).getPriority());
            assertEquals(0, boughtWantItems.get(0).getPrice());

            assertEquals("want 2", boughtWantItems.get(1).getName());
            assertEquals(1, boughtWantItems.get(1).getBudget());
            assertEquals("high", boughtWantItems.get(1).getPriority());
            assertEquals(10, boughtWantItems.get(1).getPrice());

            assertEquals("want 1", boughtWantItems.get(2).getName());
            assertEquals(820, boughtWantItems.get(2).getBudget());
            assertEquals("low", boughtWantItems.get(2).getPriority());
            assertEquals(750, boughtWantItems.get(2).getPrice());

            assertEquals(760, boughtWantList.getTotalPrice());
            assertEquals(9, boughtWantList.getTotalOverspent());

        } catch (IOException e) {
            System.out.println("Unexpected IOException caught");
        }
    }
}

