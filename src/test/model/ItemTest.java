package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemTest {
    private Item item1;
    private Item item2;

    @BeforeEach
    void setup() {
        item1 = new Item("item 1", 1);
        item2 = new Item("item 2", 500);
    }

    @Test
    void constructorItem1Test() {
        assertEquals("item 1", item1.getName());
        assertTrue(item1.isNeeded());
        assertEquals(1, item1.getBudget());
        assertEquals("No Priority", item1.getPriority());
        assertEquals("Food", item1.getCategory());

    }

    @Test
    void constructorItem2Test() {
        assertEquals("item 2", item2.getName());
        assertTrue(item2.isNeeded());
        assertEquals(500, item2.getBudget());
        assertEquals("No Priority", item2.getPriority());
        assertEquals("Food", item2.getCategory());
    }

}
