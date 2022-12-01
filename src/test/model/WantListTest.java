package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


//Tests for the WantList test
public class WantListTest {
    private WantList wants;

    private Item w1;
    private Item w2;
    private Item w3;
    private Item w4;
    private Item w5;
    private Item w6;

    private EventLog events;
    private List<String> descriptions;
    private List<Date> dates;
    private List<Event> eventList;

    @BeforeEach
    void setup() {
        wants = new WantList();

        w1 = new Item("wanted item 1", 90, "High Priority");
        w2 = new Item("wanted item 2", 150, "Medium Priority");
        w3 = new Item("wanted item 3", 1, "Low Priority");
        w4 = new Item("wanted item 4", 80, "High Priority");
        w5 = new Item("wanted item 5", 1091, "Medium Priority");
        w6 = new Item("wanted item 6", 10901, "Low Priority");

        descriptions = new ArrayList<>();
        dates = new ArrayList<>();
        eventList = new ArrayList<>();

        events = EventLog.getInstance();
        events.clear();
    }

    @Test
    void constructorTest() {
        assertEquals(0, wants.sizeList());
    }


    @Test
    void addTest() {
        wants.addItem(w3);
        assertEquals(w3, wants.getItemIndex(0));

        wants.addItem(w2);
        assertEquals(w2, wants.getItemIndex(0));
        assertEquals(w3, wants.getItemIndex(1));

        wants.addItem(w1);
        assertEquals(w1, wants.getItemIndex(0));
        assertEquals(w2, wants.getItemIndex(1));
        assertEquals(w3, wants.getItemIndex(2));
    }

    @Test
    void addEventTest() {
        wants.addItem(w3);
        wants.addItem(w2);
        wants.addItem(w1);

        for (Event event : events) {
            String description = event.getDescription();
            descriptions.add(description);
        }

        assertEquals("wanted item 3 added to want list", descriptions.get(1));
        assertEquals("wanted item 2 added to want list", descriptions.get(2));
        assertEquals("wanted item 1 added to want list", descriptions.get(3));
    }

    @Test
    void addNoDuplicationTest() {
        wants.addItem(w1);
        wants.addItem(w1);
        assertEquals(1, wants.sizeList());

        wants.addItem(w2);
        assertEquals(2, wants.sizeList());
        assertEquals(w2, wants.getItemIndex(0));
        assertEquals(w1, wants.getItemIndex(1));

        for (Event event : events) {
            String description = event.getDescription();
            descriptions.add(description);
        }
        assertEquals("wanted item 1 added to want list", descriptions.get(1));
        assertEquals("wanted item 2 added to want list", descriptions.get(2));
        assertEquals(3, descriptions.size());
    }

    @Test
    void removeTest() {
        wants.addItem(w1);
        wants.removeItem(w1);
        assertEquals(0, wants.sizeList());

        for (Event event : events) {
            String description = event.getDescription();
            descriptions.add(description);
        }

        assertEquals("wanted item 1 added to want list", descriptions.get(1));
        assertEquals("wanted item 1 removed from want list", descriptions.get(2));
    }

    @Test
    void remove2Test() {
        wants.addItem(w1);
        wants.addItem(w2);
        wants.removeItem(w2);

        assertFalse(wants.containsItem(w2));
        assertTrue(wants.containsItem(w1));
        assertEquals(1, wants.sizeList());

        wants.removeItem(w1);
        assertFalse(wants.containsItem(w1));
        assertFalse(wants.containsItem(w2));
        assertEquals(0, wants.sizeList());
    }

    @Test
    void remove2EventTest() {
        wants.addItem(w1);
        wants.addItem(w2);
        wants.removeItem(w2);
        wants.removeItem(w1);

        for (Event event : events) {
            String description = event.getDescription();
            descriptions.add(description);

            Date date = event.getDate();
            dates.add(date);

            eventList.add(event);
        }

        assertEquals("wanted item 1 added to want list", descriptions.get(1));
        assertEquals("wanted item 2 added to want list", descriptions.get(2));
        assertEquals("wanted item 2 removed from want list", descriptions.get(3));
        assertEquals("wanted item 1 removed from want list", descriptions.get(4));

        assertEquals(5, dates.size());

        assertEquals(dates.get(1) + "\n" + descriptions.get(1), eventList.get(1).toString());
        assertEquals(dates.get(2) + "\n" + descriptions.get(2), eventList.get(2).toString());
        assertEquals(dates.get(3) + "\n" + descriptions.get(3), eventList.get(3).toString());
        assertEquals(dates.get(4) + "\n" + descriptions.get(4), eventList.get(4).toString());

        assertEquals(eventList.get(4), eventList.get(4));
        assertNotEquals(eventList.get(1), eventList.get(4));
        assertNotEquals(eventList.get(4), null);
        assertNotEquals(eventList.get(4), w1);
        assertEquals(eventList.get(4). hashCode(), eventList.get(4).hashCode());

        Event event1 = new Event(eventList.get(3).getDescription());
        assertNotEquals(event1, eventList.get(3));
    }

    @Test
    void removeWant3Test() {
        wants.addItem(w1);
        wants.addItem(w2);
        wants.addItem(w3);
        wants.removeItem(w1);

        assertFalse(wants.containsItem(w1));
        assertTrue(wants.containsItem(w2));
        assertTrue(wants.containsItem(w3));
        assertEquals(w3, wants.getItemIndex(0));
        assertEquals(w2, wants.getItemIndex(1));
        assertEquals(2, wants.sizeList());

        wants.removeItem(w2);
        wants.addItem(w1);
        assertTrue(wants.containsItem(w1));
        assertFalse(wants.containsItem(w2));
        assertTrue(wants.containsItem(w3));
        assertEquals(w1, wants.getItemIndex(0));
        assertEquals(w3, wants.getItemIndex(1));
        assertEquals(2, wants.sizeList());
    }


    @Test
    void filterByPriorityHighPriorityTest() {
        wants.addItem(w1);
        wants.addItem(w2);
        wants.addItem(w3);

        List<Item> filtered = wants.filterByPriority("High Priority");

        assertEquals(w1, filtered.get(0));
        assertEquals(1, filtered.size());
    }

    @Test
    void filterByPriorityHighPriorityMultipleTest() {
        wants.addItem(w1);
        wants.addItem(w2);
        wants.addItem(w3);
        wants.addItem(w4);
        wants.addItem(w5);
        wants.addItem(w6);
        List<Item> filtered = wants.filterByPriority("High Priority");

        assertEquals(w4, filtered.get(0));
        assertEquals(w1, filtered.get(1));
        assertEquals(2, filtered.size());
    }

    @Test
    void filterByPriorityNoHighPriorityTest() {
        wants.addItem(w6);
        wants.addItem(w3);
        wants.addItem(w5);
        List<Item> filtered = wants.filterByPriority("High Priority");

        assertEquals(0, filtered.size());
    }

    @Test
    void filterByPriorityMediumPriorityTest() {
        wants.addItem(w1);
        wants.addItem(w2);
        wants.addItem(w3);

        List<Item> filtered = wants.filterByPriority("Medium Priority");
        assertEquals(w2, filtered.get(0));
        assertEquals(1, filtered.size());
    }

    @Test
    void filterByPriorityMediumPriorityMultipleTest() {
        wants.addItem(w1);
        wants.addItem(w2);
        wants.addItem(w3);
        wants.addItem(w4);
        wants.addItem(w5);
        wants.addItem(w6);

        List<Item> filtered = wants.filterByPriority("Medium Priority");

        assertEquals(w5, filtered.get(0));
        assertEquals(w2, filtered.get(1));
        assertEquals(2, filtered.size());
    }

    @Test
    void filterByPriorityMediumPriorityNoTest() {
        wants.addItem(w1);
        wants.addItem(w3);
        wants.addItem(w4);

        List<Item> filtered = wants.filterByPriority("Medium Priority");
        assertEquals(0, filtered.size());
    }


    @Test
    void filterByPriorityLowPriorityTest() {
        wants.addItem(w1);
        wants.addItem(w2);
        wants.addItem(w3);


        List<Item> filtered = wants.filterByPriority("Low Priority");

        assertEquals(w3, filtered.get(0));
        assertEquals(1, filtered.size());
    }

    @Test
    void filterByPriorityLowPriorityMultipleTest() {
        wants.addItem(w1);
        wants.addItem(w2);
        wants.addItem(w3);
        wants.addItem(w4);
        wants.addItem(w5);
        wants.addItem(w6);


        List<Item> filtered = wants.filterByPriority("Low Priority");

        assertEquals(w6, filtered.get(0));
        assertEquals(w3, filtered.get(1));
        assertEquals(2, filtered.size());
    }

    @Test
    void filterByPriorityLowPriorityNoTest() {
        wants.addItem(w1);
        wants.addItem(w2);
        wants.addItem(w4);
        wants.addItem(w5);


        List<Item> filtered = wants.filterByPriority("Low Priority");

        assertEquals(0, filtered.size());
    }

    @Test
    void toName() {
        wants.addItem(w1);
        List<String> names = wants.toName();
        assertTrue(names.contains("wanted item 1"));

        wants.addItem(w2);
        List<String> names2 = wants.toName();
        assertTrue(names2.contains("wanted item 2"));
    }

    @Test
    void inListTest() {
        wants.addItem(w1);
        boolean success = wants.inList("wanted item 1");
        boolean fail = wants.inList("wanted 1");

        assertTrue(success);
        assertFalse(fail);
    }

    @Test
    void inListMultipleTest() {
        wants.addItem(w1);
        wants.addItem(w2);

        boolean success1 = wants.inList("wanted item 1");
        boolean success2 = wants.inList("wanted item 2");
        boolean fail1 = wants.inList("item 1");
        boolean fail2 = wants.inList("tiger");
        boolean fail3 = wants.inList("hello");

        assertTrue(success1);
        assertTrue(success2);
        assertFalse(fail1);
        assertFalse(fail2);
        assertFalse(fail3);
    }


    @Test
    void getItemTest() {
        wants.addItem(w1);
        wants.addItem(w2);
        assertEquals(w1, wants.getItem("wanted item 1"));
        assertEquals(w2, wants.getItem("wanted item 2"));
    }

    @Test
    void getItemTestMultiple() {
        wants.addItem(w4);
        wants.addItem(w1);
        wants.addItem(w3);
        wants.addItem(w2);

        assertEquals(w1, wants.getItem("wanted item 1"));
        assertEquals(w2, wants.getItem("wanted item 2"));
        assertEquals(w3, wants.getItem("wanted item 3"));
        assertEquals(w4, wants.getItem("wanted item 4"));
    }


    @Test
    void containsWantTest() {
        wants.addItem(w4);

        assertTrue(wants.containsItem(w4));
        assertFalse(wants.containsItem(w1));
    }

    @Test
    void containsWantMultipleTest() {
        wants.addItem(w1);
        wants.addItem(w2);

        assertTrue(wants.containsItem(w1));
        assertTrue(wants.containsItem(w2));
        assertFalse(wants.containsItem(w3));
    }

    @Test
    void sizeWantTest() {
        wants.addItem(w4);
        assertEquals(1, wants.sizeList());
        wants.addItem(w5);
        assertEquals(2, wants.sizeList());
    }

    @Test
    void getItemIndexTest() {
        wants.addItem(w1);
        assertEquals(w1, wants.getItemIndex(0));
    }

    @Test
    void getItemIndexMultipleTest() {
        wants.addItem(w5);
        wants.addItem(w6);

        assertEquals(w6, wants.getItemIndex(0));
        assertEquals(w5, wants.getItemIndex(1));
    }


    @Test
    void getList() {
        wants.addItem(w1);
        wants.addItem(w2);

        List<Item> wantItems = wants.getList();
        assertEquals(2, wantItems.size());
        assertEquals(w2, wantItems.get(0));
        assertEquals(w1, wantItems.get(1));

        wants.addItem(w3);
        assertEquals(3, wantItems.size());
        assertEquals(w3, wantItems.get(0));
        assertEquals(w2, wantItems.get(1));
        assertEquals(w1, wantItems.get(2));
    }

}

