package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(1, item1.getBudget());
        assertEquals("Low Priority", item1.getPriority());
        assertEquals(0, item1.getPrice());

    }

    @Test
    void constructorItem2Test() {
        assertEquals("item 2", item2.getName());
        assertEquals(500, item2.getBudget());
        assertEquals("Low Priority", item2.getPriority());
        assertEquals(0, item2.getPrice());
    }

    @Test
    void setNewBudgetTest() {
        item1.setNewBudget(60);
        assertEquals(60, item1.getBudget());
    }

    @Test
    void setNewBudgetTestMultiple() {
        item2.setNewBudget(100);
        assertEquals(100, item2.getBudget());
        item2.setNewBudget(600);
        assertEquals(600, item2.getBudget());
    }

    @Test
    void setNewBudgetTestNegative() {
        item1.setNewBudget(-70);
        assertEquals(1, item1.getBudget());

        item2.setNewBudget(-1);
        assertEquals(500, item2.getBudget());
        item2.setNewBudget(0);
        assertEquals(500, item2.getBudget());
        item2.setNewBudget(1);
        assertEquals(1, item2.getBudget());
    }

}
