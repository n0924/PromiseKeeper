package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WantListTest {
    private WantList wants;

    private Item w1;
    private Item w2;
    private Item w3;
    private Item w4;
    private Item w5;
    private Item w6;

    @BeforeEach
    void setup() {
        wants = new WantList();

        w1 = new Item("wanted item 1", 90);
        w2 = new Item("wanted item 2", 150);
        w3 = new Item("wanted item 3", 1);
        w4 = new Item("wanted item 1", 102);
        w5 = new Item("wanted item 2", 342);
        w6 = new Item("wanted item 3", 76);

        w1.setPriority("High Priority");
        w2.setPriority("Medium Priority");
        w3.setPriority("Low Priority");
        w4.setPriority("High Priority");
        w5.setPriority("Medium Priority");
        w6.setPriority("Low Priority");
    }

    @Test
    void constructorTest() {
        assertEquals(0, wants.size());
    }


    @Test
    void addWantTest() {
        wants.addItem(w3);
        assertEquals(w3, wants.get(0));

        wants.addItem(w2);
        assertEquals(w2, wants.get(0));
        assertEquals(w3, wants.get(1));

        wants.addItem(w1);
        assertEquals(w1, wants.get(0));
        assertEquals(w2, wants.get(1));
        assertEquals(w3, wants.get(2));
    }

    @Test
    void addWantNoDuplicationTest() {
        wants.addItem(w1);
        wants.addItem(w1);
        assertEquals(1, wants.size());

        wants.addItem(w2);
        assertEquals(2, wants.size());
        assertEquals(w2, wants.get(0));
        assertEquals(w1, wants.get(1));
    }

    @Test
    void removeWantTest() {
        wants.addItem(w1);
        wants.removeItem(w1);
        assertEquals(0, wants.size());
    }

    @Test
    void removeWant2Test() {
        wants.addItem(w1);
        wants.addItem(w2);
        wants.removeItem(w2);

        assertFalse(wants.contains(w2));
        assertTrue(wants.contains(w1));
        assertEquals(1, wants.size());

        wants.removeItem(w1);
        assertFalse(wants.contains(w1));
        assertFalse(wants.contains(w2));
        assertEquals(0, wants.size());
    }

    @Test
    void removeWant3Test() {
        wants.addItem(w1);
        wants.addItem(w2);
        wants.addItem(w3);
        wants.removeItem(w1);

        assertFalse(wants.contains(w1));
        assertTrue(wants.contains(w2));
        assertTrue(wants.contains(w3));
        assertEquals(w3, wants.get(0));
        assertEquals(w2, wants.get(1));
        assertEquals(2, wants.size());

        wants.removeItem(w2);
        wants.addItem(w1);
        assertTrue(wants.contains(w1));
        assertFalse(wants.contains(w2));
        assertTrue(wants.contains(w3));
        assertEquals(w1, wants.get(0));
        assertEquals(w3, wants.get(1));
        assertEquals(2, wants.size());
    }


    @Test
    void filterByPriorityWantHighPriorityTest() {
        wants.addItem(w1);
        wants.addItem(w2);
        wants.addItem(w3);

        wants.filterByPriorityItem("High Priority");

        assertEquals(w1, wants.get(0));
        assertEquals(1, wants.size());
    }

    @Test
    void filterByPriorityWantHighPriorityMultipleTest() {
        wants.addItem(w1);
        wants.addItem(w2);
        wants.addItem(w3);
        wants.addItem(w4);
        wants.addItem(w5);
        wants.addItem(w6);
        wants.filterByPriorityItem("High Priority");

        assertEquals(w4, wants.get(0));
        assertEquals(w1, wants.get(1));
        assertEquals(2, wants.size());
    }

    @Test
    void filterByPriorityWantNoHighPriorityTest() {
        wants.addItem(w6);
        wants.addItem(w3);
        wants.addItem(w5);
        wants.filterByPriorityItem("High Priority");

        assertEquals(0, wants.size());
    }

    @Test
    void filterByPriorityWantMediumPriorityTest() {
        wants.addItem(w1);
        wants.addItem(w2);
        wants.addItem(w3);

        wants.filterByPriorityItem("Medium Priority");
        assertEquals(w2, wants.get(0));
        assertEquals(1, wants.size());
    }

    @Test
    void filterByPriorityWantMediumPriorityMultipleTest() {
        wants.addItem(w1);
        wants.addItem(w2);
        wants.addItem(w3);
        wants.addItem(w4);
        wants.addItem(w5);
        wants.addItem(w6);

        wants.filterByPriorityItem("Medium Priority");

        assertEquals(w5, wants.get(0));
        assertEquals(w2, wants.get(1));
        assertEquals(2, wants.size());
    }

    @Test
    void filterByPriorityWantMediumPriorityNoTest() {
        wants.addItem(w1);
        wants.addItem(w3);
        wants.addItem(w4);

        wants.filterByPriorityItem("Medium Priority");
        assertEquals(0, wants.size());
    }


    @Test
    void filterByPriorityWantLowPriorityTest() {
        wants.addItem(w1);
        wants.addItem(w2);
        wants.addItem(w3);


        wants.filterByPriorityItem("Low Priority");

        assertEquals(w3, wants.get(0));
        assertEquals(1, wants.size());
    }

    @Test
    void filterByPriorityWantLowPriorityMultipleTest() {
        wants.addItem(w1);
        wants.addItem(w2);
        wants.addItem(w3);
        wants.addItem(w4);
        wants.addItem(w5);
        wants.addItem(w6);


        wants.filterByPriorityItem("Low Priority");

        assertEquals(w6, wants.get(0));
        assertEquals(w3, wants.get(1));
        assertEquals(2, wants.size());
    }

    @Test
    void filterByPriorityWantLowPriorityNoTest() {
        wants.addItem(w1);
        wants.addItem(w2);
        wants.addItem(w4);
        wants.addItem(w5);


        wants.filterByPriorityItem("Low Priority");

        assertEquals(0, wants.size());
    }

    @Test
    void containsWantTest() {
        wants.addItem(w4);

        assertTrue(wants.contains(w4));
        assertFalse(wants.contains(w1));
    }

    @Test
    void containsWantMultipleTest() {
        wants.addItem(w1);
        wants.addItem(w2);

        assertTrue(wants.contains(w1));
        assertTrue(wants.contains(w2));
        assertFalse(wants.contains(w3));
    }

    @Test
    void sizeWantTest() {
        wants.addItem(w4);
        assertEquals(1, wants.size());
        wants.addItem(w5);
        assertEquals(2, wants.size());
    }

    @Test
    void getWantTest() {
        wants.addItem(w1);
        assertEquals(w1, wants.get(0));
    }

    @Test
    void getWantMultipleTest() {
        wants.addItem(w5);
        wants.addItem(w6);

        assertEquals(w6, wants.get(0));
        assertEquals(w5, wants.get(1));
    }


}

