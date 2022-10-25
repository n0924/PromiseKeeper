package persistence;

import model.BoughtWantList;
import model.Item;
import model.NeedList;
import model.WantList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Test reader
public class JsonReaderTest {

    @Test
    void testNonExistentFile() {
        JsonReader reader = new JsonReader("./data/dontExist.json");
        try {
            reader.readNeed();
            fail("Did not catch IOException");
        } catch (IOException e) {
            //expected
        }

    }

    @Test
    void testEmptyNeedListFile() {
        JsonReader reader = new JsonReader("./data/testEmptyNeedList.json");
        try {
            NeedList needs = reader.readNeed();
            assertEquals(0, needs.sizeList());

        } catch (IOException e) {
            System.out.println("Unexpected IOException caught");
        }
    }

    @Test
    void testNeedListFile() {
        JsonReader reader = new JsonReader("./data/testNeedList.json");
        try {
            NeedList needs = reader.readNeed();
            assertEquals(3, needs.sizeList());

            assertEquals("need 3", needs.getItemIndex(0).getName());
            assertEquals(1, needs.getItemIndex(0).getBudget());
            assertEquals("high", needs.getItemIndex(0).getPriority());

            assertEquals("need 2", needs.getItemIndex(1).getName());
            assertEquals(90, needs.getItemIndex(1).getBudget());
            assertEquals("low", needs.getItemIndex(1).getPriority());

            assertEquals("need 1", needs.getItemIndex(2).getName());
            assertEquals(800, needs.getItemIndex(2).getBudget());
            assertEquals("medium", needs.getItemIndex(2).getPriority());
        } catch (IOException e) {
            System.out.println("Unexpected IOException caught");
        }
    }

    @Test
    void testEmptyWantListFile() {
        JsonReader reader = new JsonReader("./data/testEmptyWantList.json");
        try {
            WantList wants = reader.readWant();
            assertEquals(0, wants.sizeList());

        } catch (IOException e) {
            System.out.println("Unexpected IOException caught");
        }
    }

    @Test
    void testWantListFile() {
        JsonReader reader = new JsonReader("./data/testWantList.json");
        try {
            WantList wants = reader.readWant();
            assertEquals(3, wants.sizeList());

            assertEquals("want 3", wants.getItemIndex(0).getName());
            assertEquals(600, wants.getItemIndex(0).getBudget());
            assertEquals("medium", wants.getItemIndex(0).getPriority());

            assertEquals("want 2", wants.getItemIndex(1).getName());
            assertEquals(47000, wants.getItemIndex(1).getBudget());
            assertEquals("low", wants.getItemIndex(1).getPriority());

            assertEquals("want 1", wants.getItemIndex(2).getName());
            assertEquals(1, wants.getItemIndex(2).getBudget());
            assertEquals("high", wants.getItemIndex(2).getPriority());
        } catch (IOException e) {
            System.out.println("Unexpected IOException caught");
        }
    }

    @Test
    void testEmptyBoughtWantFile() {
        JsonReader reader = new JsonReader("./data/testEmptyBoughtWantList.json");
        try {
            BoughtWantList boughtWant = reader.readBoughtWant();
            List<Item> boughtWantItem = boughtWant.getBoughtList();
            assertEquals(0, boughtWantItem.size());
        } catch (IOException e) {
            System.out.println("Unexpected IOException caught");
        }
    }

    @Test
    void testBoughtWantListFile() {
        JsonReader reader = new JsonReader("./data/testBoughtWantList.json");
        try {
            BoughtWantList boughtWants = reader.readBoughtWant();
            List<Item> boughtWantItems = boughtWants.getBoughtList();
            assertEquals(3, boughtWantItems.size());

            assertEquals("want 3", boughtWantItems.get(0).getName());
            assertEquals(820, boughtWantItems.get(0).getBudget());
            assertEquals("low", boughtWantItems.get(0).getPriority());
            assertEquals(750, boughtWantItems.get(0).getPrice());

            assertEquals("want 2", boughtWantItems.get(1).getName());
            assertEquals(1, boughtWantItems.get(1).getBudget());
            assertEquals("high", boughtWantItems.get(1).getPriority());
            assertEquals(10, boughtWantItems.get(1).getPrice());

            assertEquals("want 1", boughtWantItems.get(2).getName());
            assertEquals(90100, boughtWantItems.get(2).getBudget());
            assertEquals("medium", boughtWantItems.get(2).getPriority());
            assertEquals(0, boughtWantItems.get(2).getPrice());

            assertEquals(760, boughtWants.getTotalPrice());
            assertEquals(9, boughtWants.getTotalOverspent());

        } catch (IOException e) {
            System.out.println("Unexpected IOException caught");
        }
    }


}
