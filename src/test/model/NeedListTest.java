package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NeedListTest {
    private NeedList needs;

    private Item n1;
    private Item n2;
    private Item n3;
    private Item n4;
    private Item n5;
    private Item n6;


    @BeforeEach
    void setup() {
        needs = new NeedList();

        n1 = new Item("needed item 1", 400);
        n2 = new Item("needed item 2", 1);
        n3 = new Item("needed item 3", 5000);
        n4 = new Item("needed item 1", 66);
        n5 = new Item("needed item 2", 781);
        n6 = new Item("needed item 3", 9);

        n1.setPriority("High Priority");
        n2.setPriority("Medium Priority");
        n3.setPriority("Low Priority");
        n4.setPriority("High Priority");
        n5.setPriority("Medium Priority");
        n6.setPriority("Low Priority");
    }

    @Test
    void constructorTest() {
        assertEquals(0, needs.sizeList());
    }

    @Test
    void addNeedTest() {
        needs.addItem(n1);
        assertEquals(n1, needs.getItem(0));

        needs.addItem(n2);
        assertEquals(n2, needs.getItem(0));
        assertEquals(n1, needs.getItem(1));

        needs.addItem(n3);
        assertEquals(n3, needs.getItem(0));
        assertEquals(n2, needs.getItem(1));
        assertEquals(n1, needs.getItem(2));
    }

    @Test
    void addNeedNoDuplicationTest() {
        needs.addItem(n1);
        needs.addItem(n1);
        assertEquals(1, needs.sizeList());

        needs.addItem(n2);
        assertEquals(2, needs.sizeList());
        assertEquals(n2, needs.getItem(0));
        assertEquals(n1, needs.getItem(1));
    }


    @Test
    void removeNeedTest() {
        needs.addItem(n1);
        needs.removeItem(n1);

        assertEquals(0, needs.sizeList());
    }


    @Test
    void removeNeed2Test() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.removeItem(n1);

        assertEquals(n2, needs.getItem(0));
        assertTrue(needs.containsItem(n2));
        assertFalse(needs.containsItem(n1));

        needs.removeItem(n2);
        assertEquals(0, needs.sizeList());
        assertFalse(needs.containsItem(n1));
        assertFalse(needs.containsItem(n2));
    }

    @Test
    void removeNeed3Test() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);
        needs.removeItem(n2);
        assertTrue(needs.containsItem(n1));
        assertFalse(needs.containsItem(n2));
        assertTrue(needs.containsItem(n3));
        assertEquals(n3, needs.getItem(0));
        assertEquals(n1, needs.getItem(1));
        assertEquals(2, needs.sizeList());

        needs.removeItem(n1);
        needs.addItem(n2);
        assertFalse(needs.containsItem(n1));
        assertTrue(needs.containsItem(n2));
        assertTrue(needs.containsItem(n3));
        assertEquals(n2, needs.getItem(0));
        assertEquals(n3, needs.getItem(1));
        assertEquals(2, needs.sizeList());

    }



    @Test
    void filterByPriorityNeedHighPriorityTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);

        needs.filterByPriorityItem("High Priority");

        assertEquals(n1, needs.getItem(0));
        assertEquals(1, needs.sizeList());
    }

    @Test
    void filterByPriorityNeedHighPriorityMultipleTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);
        needs.addItem(n4);
        needs.addItem(n5);
        needs.addItem(n6);
        needs.filterByPriorityItem("High Priority");

        assertEquals(n4, needs.getItem(0));
        assertEquals(n1, needs.getItem(1));
        assertEquals(2, needs.sizeList());
    }

    @Test
    void filterByPriorityNeedNoHighPriorityTest() {
        needs.addItem(n2);
        needs.addItem(n3);
        needs.addItem(n5);
        needs.filterByPriorityItem("High Priority");

        assertEquals(0, needs.sizeList());
    }

    @Test
    void filterByPriorityNeedMediumPriorityTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);

        needs.filterByPriorityItem("Medium Priority");

        assertEquals(n2, needs.getItem(0));
        assertEquals(1, needs.sizeList());
    }

    @Test
    void filterByPriorityNeedMediumPriorityMultipleTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);
        needs.addItem(n4);
        needs.addItem(n5);
        needs.addItem(n6);

        needs.filterByPriorityItem("Medium Priority");

        assertEquals(n5, needs.getItem(0));
        assertEquals(n2, needs.getItem(1));
        assertEquals(2, needs.sizeList());
    }

    @Test
    void filterByPriorityNeedMediumPriorityNoTest() {
        needs.addItem(n1);
        needs.addItem(n3);
        needs.addItem(n4);
        needs.addItem(n6);

        needs.filterByPriorityItem("Medium Priority");
        assertEquals(0, needs.sizeList());
    }


    @Test
    void filterByPriorityNeedLowPriorityTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);


        needs.filterByPriorityItem("Low Priority");

        assertEquals(n3, needs.getItem(0));
        assertEquals(1, needs.sizeList());
    }

    @Test
    void filterByPriorityNeedLowPriorityMultipleTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);
        needs.addItem(n4);
        needs.addItem(n5);
        needs.addItem(n6);

        needs.filterByPriorityItem("Low Priority");

        assertEquals(n6, needs.getItem(0));
        assertEquals(n3, needs.getItem(1));
        assertEquals(2, needs.sizeList());
    }

    @Test
    void filterByPriorityNeedLowPriorityNoTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n4);
        needs.addItem(n5);


        needs.filterByPriorityItem("Low Priority");

        assertEquals(0, needs.sizeList());
    }

    @Test
    void containsNeedTest() {
        needs.addItem(n1);
        assertTrue(needs.containsItem(n1));
        assertFalse(needs.containsItem(n6));
    }

    @Test
    void containsNeedMultipleTest() {
        needs.addItem(n1);
        needs.addItem(n2);

        assertTrue(needs.containsItem(n1));
        assertTrue(needs.containsItem(n2));
        assertFalse(needs.containsItem(n5));
    }

    @Test
    void sizeNeed() {
        needs.addItem(n1);
        assertEquals(1, needs.sizeList());

        needs.addItem(n2);
        assertEquals(2, needs.sizeList());
    }

    @Test
    void getNeedTest() {
        needs.addItem(n1);
        assertEquals(n1, needs.getItem(0));
    }

    @Test
    void getNeedMultipleTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);
        assertEquals(n3, needs.getItem(0));
        assertEquals(n2, needs.getItem(1));
        assertEquals(n1, needs.getItem(2));
    }

}








