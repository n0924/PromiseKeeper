package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Tests for the Item class
public class ItemTest {
    private Item item1;
    private Item item2;

    @BeforeEach
    void setup() {
        item1 = new Item("item 1", 1, "low priority");
        item2 = new Item("item 2", 500, "high priority");
    }

    @Test
    void constructorTest() {
        assertEquals("item 1", item1.getName());
        assertEquals(1, item1.getBudget());
        assertEquals("low priority", item1.getPriority());
        assertEquals(0, item1.getPrice());

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

    @Test
    void equalsTest() {
        Item item4 = new Item("item 1", 1, "low priority");
        String str = "name";

        assertEquals(item1, item4);
        assertEquals(item1, item1);
        assertNotEquals(item1, item2);
        assertFalse(item1.equals(str));
    }

    @Test
    void hashcodeTest() {
        Item item4 = new Item("item 1", 1, "low priority");
        Item item5 = new Item("item 1", 1, "low priority");
        Item item6 = new Item("item 1", 1, "low priority");

        assertEquals(item1.hashCode(), item4.hashCode());
        assertEquals(item1.hashCode(), item1.hashCode());
        assertEquals(item1.hashCode(), item5.hashCode());
        assertEquals(item1.hashCode(), item6.hashCode());

    }



}
