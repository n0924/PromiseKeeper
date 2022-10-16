package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Tests for the Item class
public class ItemTest {
    private Item item1;
    private Item item2;

    @BeforeEach
    void setup() {
        item1 = new Item();
        item1.setName("item 1");
        item1.setBudget(1);
        item1.setPriority("low priority");

        item2 = new Item();
        item2.setName("item 2");
        item2.setBudget(500);
        item2.setPriority("high priority");
    }

    @Test
    void gettersItem1Test() {
        assertEquals("item 1", item1.getName());
        assertEquals(1, item1.getBudget());
        assertEquals("low priority", item1.getPriority());
        assertEquals(0, item1.getPrice());

    }

    @Test
    void gettersItem2Test() {
        assertEquals("item 2", item2.getName());
        assertEquals(500, item2.getBudget());
        assertEquals("high priority", item2.getPriority());
        assertEquals(0, item2.getPrice());
    }

    @Test
    void setBudgetTest() {
        item1.setBudget(60);
        assertEquals(60, item1.getBudget());
    }

    @Test
    void setBudgetTestMultiple() {
        item2.setBudget(100);
        assertEquals(100, item2.getBudget());
        item2.setBudget(600);
        assertEquals(600, item2.getBudget());
    }

    @Test
    void setBudgetTestNegative() {
        item1.setBudget(-70);
        assertEquals(1, item1.getBudget());

        item2.setBudget(-1);
        assertEquals(500, item2.getBudget());
        item2.setBudget(0);
        assertEquals(500, item2.getBudget());
        item2.setBudget(1);
        assertEquals(1, item2.getBudget());
    }

}
