package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NeedListTest {
    private NeedList needs;
    private NeedList wants;
    private Item n1;
    private Item n2;
    private Item n3;
    private Item n4;
    private Item n5;
    private Item n6;
    private Item w1;
    private Item w2;
    private Item w3;
    private Item w4;
    private Item w5;
    private Item w6;

    @BeforeEach
    void setup() {
        needs = new NeedList();
        wants = new NeedList();

        n1 = new Item("needed item 1", 400);
        n2 = new Item("needed item 2", 1);
        n3 = new Item("needed item 3", 5000);
        n4 = new Item("needed item 1", 66);
        n5 = new Item("needed item 2", 781);
        n6 = new Item("needed item 3", 9);
        w1 = new Item("wanted item 1", 90);
        w2 = new Item("wanted item 2", 150);
        w3 = new Item("wanted item 3", 1);
        w4 = new Item("wanted item 1", 102);
        w5 = new Item("wanted item 2", 342);
        w6 = new Item("wanted item 3", 76);

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
        assertEquals(0, wants.sizeNeed());
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
    void addWantTest() {
        wants.addNeed(w1);
        assertEquals(w1, wants.getWant(0));

        wants.addNeed(w2);
        assertEquals(w2, wants.getWant(0));
        assertEquals(w1, wants.getWant(1));

        needs.addNeed(n3);
        assertEquals(n3, needs.getWant(0));
        assertEquals(n2, needs.getWant(1));
        assertEquals(n1, needs.getWant(2));
    }

    @Test
    void addWantNoDuplicationTest() {
        wants.addWant(w1);
        wants.addWant(w1);
        assertEquals(1, wants.sizeWant());

        wants.addWant(w2);
        assertEquals(2, wants.sizeWant());
        assertEquals(w2, wants.getWant(0));
        assertEquals(w1, wants.getWant(1));
    }

    @Test
    void removeNeedTest() {
        needs.addNeed(n1);
        needs.removeNeed(n1);
        ;

        assertEquals(0, needs.sizeNeed());
    }


    @Test
    void removeNeed2Test() {
        needs.addNeed(n1);
        needs.addNeed(n2);
        needs.removeNeed(n1);

        assertEquals(n1, needs.getNeed(0));
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
    void removeWantTest() {
        wants.addWant(w1);
        wants.removeWant(w1);
        assertEquals(0, wants.sizeWant());
    }

    @Test
    void removeWant2Test() {
        wants.addWant(w1);
        wants.addWant(w2);
        wants.removeWant(w1);

        assertFalse(wants.containsWant(w1));
        assertTrue(wants.containsWant(w2));
        assertEquals(1, wants.sizeWant());

        wants.removeWant(w2);
        assertFalse(wants.containsWant(w1));
        assertFalse(wants.containsWant(w2));
        assertEquals(0, wants.sizeWant());
    }

    @Test
    void removeWant3Test() {
        wants.addWant(w1);
        wants.addWant(w2);
        wants.addWant(w3);
        wants.removeWant(w2);

        assertTrue(wants.containsWant(w1));
        assertFalse(wants.containsWant(w2));
        assertTrue(wants.containsWant(w3));
        assertEquals(w3, wants.getWant(0));
        assertEquals(w1, wants.getWant(1));
        assertEquals(1, wants.sizeWant());

        wants.removeWant(w1);
        wants.addWant(w2);
        assertFalse(wants.containsWant(w1));
        assertTrue(wants.containsWant(w2));
        assertTrue(wants.containsWant(w3));
        assertEquals(w2, wants.getWant(0));
        assertEquals(w3, wants.getWant(1));
        assertEquals(1, wants.sizeWant());
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
        needs.addNeed(n1);
        needs.addNeed(n2);
        needs.addNeed(n4);
        needs.filterByPriorityNeed("High Priority");

        assertEquals(0, needs.sizeNeed());
    }

    @Test
    void filterByPriorityNeedMediumPriorityTest() {
        needs.addNeed(n1);
        needs.addNeed(n2);
        needs.addNeed(n3);

        needs.filterByPriorityNeed("Medium Priority");
        ;
        assertEquals(n2, needs.getNeed(0));
        assertEquals(2, needs.sizeNeed());
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

}








