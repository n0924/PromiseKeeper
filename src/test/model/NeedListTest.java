package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NeedListTest {
    private NeedList needs;

    private Item n1;
    private Item n12;
    private Item n13;
    private Item n2;
    private Item n3;
    private Item n4;
    private Item n5;
    private Item n6;


    @BeforeEach
    void setup() {
        needs = new NeedList();

        n1 = new Item("needed item 1", 400);
        n12 = new Item("needed item 1", 800);
        n13 = new Item("needed item 1", 900);

        n2 = new Item("needed item 2", 1);
        n3 = new Item("needed item 3", 5000);
        n4 = new Item("needed item 4", 66);
        n5 = new Item("needed item 5", 781);
        n6 = new Item("needed item 6", 9);

        n1.setPriority("High Priority");
        n2.setPriority("Medium Priority");
        n3.setPriority("Low Priority");
        n4.setPriority("High Priority");
        n5.setPriority("Medium Priority");
        n6.setPriority("Low Priority");
    }

    @Test
    void constructorTest() {
        assertEquals(0, needs.sizeItem());
    }

    @Test
    void addNeedTest() {
        needs.addItem(n1);
        assertEquals(n1, needs.getItemIndex(0));

        needs.addItem(n2);
        assertEquals(n2, needs.getItemIndex(0));
        assertEquals(n1, needs.getItemIndex(1));

        needs.addItem(n3);
        assertEquals(n3, needs.getItemIndex(0));
        assertEquals(n2, needs.getItemIndex(1));
        assertEquals(n1, needs.getItemIndex(2));
    }

    @Test
    void addNeedNoDuplicationTest() {
        needs.addItem(n1);
        needs.addItem(n12);
        assertEquals(1, needs.sizeItem());
        assertTrue(needs.containsItem(n1));
        assertFalse(needs.containsItem(n12));

        needs.addItem(n1);
        assertEquals(1, needs.sizeItem());
        assertTrue(needs.containsItem(n1));

        needs.addItem(n13);
        needs.addItem(n12);
        needs.addItem(n1);
        needs.addItem(n12);

        assertEquals(1, needs.sizeItem());
        assertTrue(needs.containsItem(n1));
        assertFalse(needs.containsItem(n12));
        assertFalse(needs.containsItem(n13));

    }


    @Test
    void removeNeedTest() {
        needs.addItem(n1);
        needs.removeItem(n1);

        assertEquals(0, needs.sizeItem());
    }


    @Test
    void removeNeed2Test() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.removeItem(n1);

        assertEquals(n2, needs.getItemIndex(0));
        assertTrue(needs.containsItem(n2));
        assertFalse(needs.containsItem(n1));

        needs.removeItem(n2);
        assertEquals(0, needs.sizeItem());
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
        assertEquals(n3, needs.getItemIndex(0));
        assertEquals(n1, needs.getItemIndex(1));
        assertEquals(2, needs.sizeItem());

        needs.removeItem(n1);
        needs.addItem(n2);
        assertFalse(needs.containsItem(n1));
        assertTrue(needs.containsItem(n2));
        assertTrue(needs.containsItem(n3));
        assertEquals(n2, needs.getItemIndex(0));
        assertEquals(n3, needs.getItemIndex(1));
        assertEquals(2, needs.sizeItem());

    }



    @Test
    void filterByPriorityNeedHighPriorityTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);

        List<Item> filteredNeeds = needs.filterByPriorityItem("High Priority");

        assertEquals(n1, filteredNeeds.get(0));
        assertEquals(1, filteredNeeds.size());
    }

    @Test
    void filterByPriorityNeedHighPriorityMultipleTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);
        needs.addItem(n4);
        needs.addItem(n5);
        needs.addItem(n6);
        List<Item> filteredNeeds = needs.filterByPriorityItem("High Priority");

        assertEquals(n4, filteredNeeds.get(0));
        assertEquals(n1, filteredNeeds.get(1));
        assertEquals(2, filteredNeeds.size());
    }

    @Test
    void filterByPriorityNeedNoHighPriorityTest() {
        needs.addItem(n2);
        needs.addItem(n3);
        needs.addItem(n5);
        List<Item> filteredNeeds = needs.filterByPriorityItem("High Priority");

        assertEquals(0, filteredNeeds.size());
    }

    @Test
    void filterByPriorityNeedMediumPriorityTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);

        List<Item> filteredNeeds = needs.filterByPriorityItem("Medium Priority");

        assertEquals(n2, filteredNeeds.get(0));
        assertEquals(1, filteredNeeds.size());
    }

    @Test
    void filterByPriorityNeedMediumPriorityMultipleTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);
        needs.addItem(n4);
        needs.addItem(n5);
        needs.addItem(n6);

        List<Item> filteredNeeds = needs.filterByPriorityItem("Medium Priority");

        assertEquals(n5, filteredNeeds.get(0));
        assertEquals(n2, filteredNeeds.get(1));
        assertEquals(2, filteredNeeds.size());
    }

    @Test
    void filterByPriorityNeedMediumPriorityNoTest() {
        needs.addItem(n1);
        needs.addItem(n3);
        needs.addItem(n4);
        needs.addItem(n6);

        List<Item> filteredNeeds = needs.filterByPriorityItem("Medium Priority");
        assertEquals(0, filteredNeeds.size());
    }


    @Test
    void filterByPriorityNeedLowPriorityTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);


        List<Item> filteredNeeds = needs.filterByPriorityItem("Low Priority");

        assertEquals(n3, filteredNeeds.get(0));
        assertEquals(1, filteredNeeds.size());
    }

    @Test
    void filterByPriorityNeedLowPriorityMultipleTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);
        needs.addItem(n4);
        needs.addItem(n5);
        needs.addItem(n6);

        List<Item> filteredNeeds = needs.filterByPriorityItem("Low Priority");

        assertEquals(n6, filteredNeeds.get(0));
        assertEquals(n3, filteredNeeds.get(1));
        assertEquals(2, filteredNeeds.size());
    }

    @Test
    void filterByPriorityNeedLowPriorityNoTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n4);
        needs.addItem(n5);


        List<Item> filteredNeeds = needs.filterByPriorityItem("Low Priority");

        assertEquals(0, filteredNeeds.size());
    }

    @Test
    void inListTest() {
        needs.addItem(n1);
        boolean success = needs.inList("needed item 1");
        boolean fail = needs.inList("item 1");

        assertTrue(success);
        assertFalse(fail);
    }

    @Test
    void inListMultipleTest() {
        needs.addItem(n1);
        needs.addItem(n2);

        boolean success1 = needs.inList("needed item 1");
        boolean success2 = needs.inList("needed item 2");
        boolean fail1 = needs.inList("item 1");
        boolean fail2 = needs.inList("tiger");
        boolean fail3 = needs.inList("hello");

        assertTrue(success1);
        assertTrue(success2);
        assertFalse(fail1);
        assertFalse(fail2);
        assertFalse(fail3);
    }

    @Test
    void getItemTest() {
        needs.addItem(n1);
        needs.addItem(n3);

        assertEquals(n1, needs.getItem("needed item 1"));
        assertEquals(n3, needs.getItem("needed item 3"));
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
        assertEquals(1, needs.sizeItem());

        needs.addItem(n2);
        assertEquals(2, needs.sizeItem());
    }

    @Test
    void getNeedTest() {
        needs.addItem(n1);
        assertEquals(n1, needs.getItemIndex(0));
    }

    @Test
    void getNeedMultipleTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);
        assertEquals(n3, needs.getItemIndex(0));
        assertEquals(n2, needs.getItemIndex(1));
        assertEquals(n1, needs.getItemIndex(2));
    }
}








