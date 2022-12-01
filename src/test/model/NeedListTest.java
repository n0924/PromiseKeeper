package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Tests for the NeedList class
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

    private EventLog events;
    private List<String> descriptions;
    private List<Date> dates;
    private List<Event> eventList;

    @BeforeEach
    void setup() {
        needs = new NeedList();

        n1 = new Item("needed item 1", 400, "high priority");
        n12 = new Item("needed item 1", 800, "medium priority");
        n13 = new Item("needed item 1", 900, "low priority");


        n2 = new Item("needed item 2", 1, "medium priority");
        n3 = new Item("needed item 3", 5000, "low priority");
        n4 = new Item("needed item 4", 80, "high priority");
        n5 = new Item("needed item 5", 4, "medium priority");
        n6 = new Item("needed item 6", 803, "low priority");

        descriptions = new ArrayList<>();
        dates = new ArrayList<>();
        eventList = new ArrayList<>();
        events = EventLog.getInstance();
        events.clear();
    }

    @Test
    void constructorTest() {
        assertEquals(0, needs.sizeList());
    }

    @Test
    void addTest() {
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
    void addEventTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);

        for (Event event : events) {
            String description = event.getDescription();
            descriptions.add(description);

            Date date = event.getDate();
            dates.add(date);

            eventList.add(event);
        }

        assertEquals("needed item 1 added to need list",descriptions.get(1));
        assertEquals("needed item 2 added to need list", descriptions.get(2));
        assertEquals("needed item 3 added to need list", descriptions.get(3));

        assertEquals(4, dates.size());

        assertEquals(dates.get(1) + "\n" + descriptions.get(1), eventList.get(1).toString());
        assertEquals(dates.get(2) + "\n" + descriptions.get(2), eventList.get(2).toString());
        assertEquals(dates.get(3) + "\n" + descriptions.get(3), eventList.get(3).toString());

        assertEquals(eventList.get(1), eventList.get(1));
        assertNotEquals(eventList.get(1), eventList.get(2));
        assertNotEquals(eventList.get(1), null);
        assertNotEquals(eventList.get(1), dates.get(1));
        assertEquals(eventList.get(1). hashCode(), eventList.get(1).hashCode());

        Event event1 = new Event(eventList.get(2).getDescription());
        assertNotEquals(event1, eventList.get(2));
    }

    @Test
    void addNoDuplicationTest() {
        needs.addItem(n1);
        needs.addItem(n12);
        assertEquals(1, needs.sizeList());
        assertTrue(needs.containsItem(n1));
        assertFalse(needs.containsItem(n12));

        needs.addItem(n1);
        assertEquals(1, needs.sizeList());
        assertTrue(needs.containsItem(n1));

        needs.addItem(n13);
        needs.addItem(n12);
        needs.addItem(n1);
        needs.addItem(n12);

        assertEquals(1, needs.sizeList());
        assertTrue(needs.containsItem(n1));
        assertFalse(needs.containsItem(n12));
        assertFalse(needs.containsItem(n13));

    }

    @Test
    void addNoDuplicationEventTest() {
        needs.addItem(n1);
        needs.addItem(n12);
        needs.addItem(n1);
        needs.addItem(n13);
        needs.addItem(n12);
        needs.addItem(n1);
        needs.addItem(n12);

        for (Event event : events) {
            String description = event.getDescription();
            descriptions.add(description);
        }

        assertEquals("needed item 1 added to need list", descriptions.get(1));
        assertEquals(2, descriptions.size());
    }


    @Test
    void removeTest() {
        needs.addItem(n1);
        needs.removeItem(n1);

        assertEquals(0, needs.sizeList());

        for (Event event :events) {
            String description = event.getDescription();
            descriptions.add(description);
        }

        assertEquals("needed item 1 added to need list", descriptions.get(1));
        assertEquals("needed item 1 removed from need list", descriptions.get(2));
    }


    @Test
    void remove2Test() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.removeItem(n1);

        assertEquals(n2, needs.getItemIndex(0));
        assertTrue(needs.containsItem(n2));
        assertFalse(needs.containsItem(n1));

        needs.removeItem(n2);
        assertEquals(0, needs.sizeList());
        assertFalse(needs.containsItem(n1));
        assertFalse(needs.containsItem(n2));
    }

    @Test
    void remove2EventTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.removeItem(n1);
        needs.removeItem(n2);

        for (Event event :events) {
            String description = event.getDescription();
            descriptions.add(description);
        }

        assertEquals("needed item 1 added to need list", descriptions.get(1));
        assertEquals("needed item 2 added to need list", descriptions.get(2));
        assertEquals("needed item 1 removed from need list", descriptions.get(3));
        assertEquals("needed item 2 removed from need list", descriptions.get(4));
    }

    @Test
    void remove3Test() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);
        needs.removeItem(n2);
        assertTrue(needs.containsItem(n1));
        assertFalse(needs.containsItem(n2));
        assertTrue(needs.containsItem(n3));
        assertEquals(n3, needs.getItemIndex(0));
        assertEquals(n1, needs.getItemIndex(1));
        assertEquals(2, needs.sizeList());

        needs.removeItem(n1);
        needs.addItem(n2);
        assertFalse(needs.containsItem(n1));
        assertTrue(needs.containsItem(n2));
        assertTrue(needs.containsItem(n3));
        assertEquals(n2, needs.getItemIndex(0));
        assertEquals(n3, needs.getItemIndex(1));
        assertEquals(2, needs.sizeList());

    }


    @Test
    void filterByPriorityHighPriorityTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);

        List<Item> filteredNeeds = needs.filterByPriority("high priority");

        assertEquals(n1, filteredNeeds.get(0));
        assertEquals(1, filteredNeeds.size());
    }

    @Test
    void filterByPriorityHighPriorityMultipleTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);
        needs.addItem(n4);
        needs.addItem(n5);
        needs.addItem(n6);
        List<Item> filteredNeeds = needs.filterByPriority("high priority");

        assertEquals(n4, filteredNeeds.get(0));
        assertEquals(n1, filteredNeeds.get(1));
        assertEquals(2, filteredNeeds.size());
    }

    @Test
    void filterByPriorityNoHighPriorityTest() {
        needs.addItem(n2);
        needs.addItem(n3);
        needs.addItem(n5);
        List<Item> filteredNeeds = needs.filterByPriority("high priority");

        assertEquals(0, filteredNeeds.size());
    }

    @Test
    void filterByPriorityMediumPriorityTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);

        List<Item> filteredNeeds = needs.filterByPriority("medium priority");

        assertEquals(n2, filteredNeeds.get(0));
        assertEquals(1, filteredNeeds.size());
    }

    @Test
    void filterByPriorityMediumPriorityMultipleTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);
        needs.addItem(n4);
        needs.addItem(n5);
        needs.addItem(n6);

        List<Item> filteredNeeds = needs.filterByPriority("medium priority");

        assertEquals(n5, filteredNeeds.get(0));
        assertEquals(n2, filteredNeeds.get(1));
        assertEquals(2, filteredNeeds.size());
    }

    @Test
    void filterByPriorityMediumPriorityNoTest() {
        needs.addItem(n1);
        needs.addItem(n3);
        needs.addItem(n4);
        needs.addItem(n6);

        List<Item> filteredNeeds = needs.filterByPriority("medium priority");
        assertEquals(0, filteredNeeds.size());
    }


    @Test
    void filterByPriorityLowPriorityTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);


        List<Item> filteredNeeds = needs.filterByPriority("low priority");

        assertEquals(n3, filteredNeeds.get(0));
        assertEquals(1, filteredNeeds.size());
    }

    @Test
    void filterByPriorityLowPriorityMultipleTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);
        needs.addItem(n4);
        needs.addItem(n5);
        needs.addItem(n6);

        List<Item> filteredNeeds = needs.filterByPriority("low priority");

        assertEquals(n6, filteredNeeds.get(0));
        assertEquals(n3, filteredNeeds.get(1));
        assertEquals(2, filteredNeeds.size());
    }

    @Test
    void filterByPriorityLowPriorityNoTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n4);
        needs.addItem(n5);


        List<Item> filteredNeeds = needs.filterByPriority("low priority");

        assertEquals(0, filteredNeeds.size());
    }

    @Test
    void toName() {
        needs.addItem(n1);
        List<String> names = needs.toName();
        assertTrue(names.contains("needed item 1"));

        needs.addItem(n2);
        List<String> names2 = needs.toName();
        assertTrue(names2.contains("needed item 2"));
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
    void getItemTestMultiple() {
        needs.addItem(n3);
        needs.addItem(n4);
        needs.addItem(n1);
        needs.addItem(n2);

        assertEquals(n1, needs.getItem("needed item 1"));
        assertEquals(n2, needs.getItem("needed item 2"));
        assertEquals(n3, needs.getItem("needed item 3"));
        assertEquals(n4, needs.getItem("needed item 4"));
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
        needs.addItem(n3);
        needs.addItem(n2);
        needs.addItem(n4);
        assertEquals(4, needs.sizeList());
    }

    @Test
    void getItemIndex() {
        needs.addItem(n1);
        assertEquals(n1, needs.getItemIndex(0));
    }


    @Test
    void getItemIndexMultipleTest() {
        needs.addItem(n1);
        needs.addItem(n2);
        needs.addItem(n3);
        assertEquals(n3, needs.getItemIndex(0));
        assertEquals(n2, needs.getItemIndex(1));
        assertEquals(n1, needs.getItemIndex(2));
    }

    @Test
    void getList() {
        needs.addItem(n1);
        needs.addItem(n2);

        List<Item> needItems = needs.getList();
        assertEquals(2, needItems.size());
        assertEquals(n2, needItems.get(0));
        assertEquals(n1, needItems.get(1));

        needs.addItem(n3);
        assertEquals(3, needItems.size());
        assertEquals(n3, needItems.get(0));
        assertEquals(n2, needItems.get(1));
        assertEquals(n1, needItems.get(2));
    }

}








