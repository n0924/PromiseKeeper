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
        assertEquals(0, needs.sizeNeed());
    }

    @Test
    void addNeedTest() {
        needs.addNeed(n1);
        assertEquals(n1, needs.getNeed(0));

        needs.addNeed(n2);
        assertEquals(n2, needs.getNeed(0));
        assertEquals(n1, needs.getNeed(1));

        needs.addNeed(n3);
        assertEquals(n3, needs.getNeed(0));
        assertEquals(n2, needs.getNeed(1));
        assertEquals(n1, needs.getNeed(2));
    }

    @Test
    void addNeedNoDuplicationTest() {
        needs.addNeed(n1);
        needs.addNeed(n1);
        assertEquals(1, needs.sizeNeed());

        needs.addNeed(n2);
        assertEquals(2, needs.sizeNeed());
        assertEquals(n2, needs.getNeed(0));
        assertEquals(n1, needs.getNeed(1));
    }


    @Test
    void removeNeedTest() {
        needs.addNeed(n1);
        needs.removeNeed(n1);

        assertEquals(0, needs.sizeNeed());
    }


    @Test
    void removeNeed2Test() {
        needs.addNeed(n1);
        needs.addNeed(n2);
        needs.removeNeed(n1);

        assertEquals(n2, needs.getNeed(0));
        assertTrue(needs.containsNeed(n2));
        assertFalse(needs.containsNeed(n1));

        needs.removeNeed(n2);
        assertEquals(0, needs.sizeNeed());
        assertFalse(needs.containsNeed(n1));
        assertFalse(needs.containsNeed(n2));
    }

    @Test
    void removeNeed3Test() {
        needs.addNeed(n1);
        needs.addNeed(n2);
        needs.addNeed(n3);
        needs.removeNeed(n2);
        assertTrue(needs.containsNeed(n1));
        assertFalse(needs.containsNeed(n2));
        assertTrue(needs.containsNeed(n3));
        assertEquals(n3, needs.getNeed(0));
        assertEquals(n1, needs.getNeed(1));
        assertEquals(2, needs.sizeNeed());

        needs.removeNeed(n1);
        needs.addNeed(n2);
        assertFalse(needs.containsNeed(n1));
        assertTrue(needs.containsNeed(n2));
        assertTrue(needs.containsNeed(n3));
        assertEquals(n2, needs.getNeed(0));
        assertEquals(n3, needs.getNeed(1));
        assertEquals(2, needs.sizeNeed());

    }



    @Test
    void filterByPriorityNeedHighPriorityTest() {
        needs.addNeed(n1);
        needs.addNeed(n2);
        needs.addNeed(n3);

        needs.filterByPriorityNeed("High Priority");

        assertEquals(n1, needs.getNeed(0));
        assertEquals(1, needs.sizeNeed());
    }

    @Test
    void filterByPriorityNeedHighPriorityMultipleTest() {
        needs.addNeed(n1);
        needs.addNeed(n2);
        needs.addNeed(n3);
        needs.addNeed(n4);
        needs.addNeed(n5);
        needs.addNeed(n6);
        needs.filterByPriorityNeed("High Priority");

        assertEquals(n4, needs.getNeed(0));
        assertEquals(n1, needs.getNeed(1));
        assertEquals(2, needs.sizeNeed());
    }

    @Test
    void filterByPriorityNeedNoHighPriorityTest() {
        needs.addNeed(n2);
        needs.addNeed(n3);
        needs.addNeed(n5);
        needs.filterByPriorityNeed("High Priority");

        assertEquals(0, needs.sizeNeed());
    }

    @Test
    void filterByPriorityNeedMediumPriorityTest() {
        needs.addNeed(n1);
        needs.addNeed(n2);
        needs.addNeed(n3);

        needs.filterByPriorityNeed("Medium Priority");

        assertEquals(n2, needs.getNeed(0));
        assertEquals(1, needs.sizeNeed());
    }

    @Test
    void filterByPriorityNeedMediumPriorityMultipleTest() {
        needs.addNeed(n1);
        needs.addNeed(n2);
        needs.addNeed(n3);
        needs.addNeed(n4);
        needs.addNeed(n5);
        needs.addNeed(n6);

        needs.filterByPriorityNeed("Medium Priority");

        assertEquals(n5, needs.getNeed(0));
        assertEquals(n2, needs.getNeed(1));
        assertEquals(2, needs.sizeNeed());
    }

    @Test
    void filterByPriorityNeedMediumPriorityNoTest() {
        needs.addNeed(n1);
        needs.addNeed(n3);
        needs.addNeed(n4);
        needs.addNeed(n6);

        needs.filterByPriorityNeed("Medium Priority");
        assertEquals(0, needs.sizeNeed());
    }


    @Test
    void filterByPriorityNeedLowPriorityTest() {
        needs.addNeed(n1);
        needs.addNeed(n2);
        needs.addNeed(n3);


        needs.filterByPriorityNeed("Low Priority");

        assertEquals(n3, needs.getNeed(0));
        assertEquals(1, needs.sizeNeed());
    }

    @Test
    void filterByPriorityNeedLowPriorityMultipleTest() {
        needs.addNeed(n1);
        needs.addNeed(n2);
        needs.addNeed(n3);
        needs.addNeed(n4);
        needs.addNeed(n5);
        needs.addNeed(n6);

        needs.filterByPriorityNeed("Low Priority");

        assertEquals(n6, needs.getNeed(0));
        assertEquals(n3, needs.getNeed(1));
        assertEquals(2, needs.sizeNeed());
    }

    @Test
    void filterByPriorityNeedLowPriorityNoTest() {
        needs.addNeed(n1);
        needs.addNeed(n2);
        needs.addNeed(n4);
        needs.addNeed(n5);


        needs.filterByPriorityNeed("Low Priority");

        assertEquals(0, needs.sizeNeed());
    }

    @Test
    void containsNeedTest() {
        needs.addNeed(n1);
        assertTrue(needs.containsNeed(n1));
        assertFalse(needs.containsNeed(n6));
    }

    @Test
    void containsNeedMultipleTest() {
        needs.addNeed(n1);
        needs.addNeed(n2);

        assertTrue(needs.containsNeed(n1));
        assertTrue(needs.containsNeed(n2));
        assertFalse(needs.containsNeed(n5));
    }

    @Test
    void sizeNeed() {
        needs.addNeed(n1);
        assertEquals(1, needs.sizeNeed());

        needs.addNeed(n2);
        assertEquals(2, needs.sizeNeed());
    }

    @Test
    void getNeedTest() {
        needs.addNeed(n1);
        assertEquals(n1, needs.getNeed(0));
    }

    @Test
    void getNeedMultipleTest() {
        needs.addNeed(n1);
        needs.addNeed(n2);
        needs.addNeed(n3);
        assertEquals(n3, needs.getNeed(0));
        assertEquals(n2, needs.getNeed(1));
        assertEquals(n1, needs.getNeed(2));
    }

}








