package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoughtWantListTest {
    private BoughtWant bwants;
    private Item bw1;
    private Item bw2;
    private Item bw3;



    @BeforeEach
    void setup() {
        bwants = new BoughtWant();
        bw1 = new Item("item 1", 900);
        bw2 = new Item("item 2", 10);
        bw3 = new Item("item 3", 1);
    }

    @Test
    void constructorTest() {
        assertEquals(0, bwants.sizeBought());
    }

    @Test
    void addBoughtTest() {
        bwants.addBought(bw1, 800);
        List<Item> bwants1 = bwants.getBoughtList();

        assertEquals(1, bwants1.size());
        assertTrue(bwants1.contains(bw1));

        assertEquals(800, bwants.getTotalPrice());
        assertEquals(900, bwants.getTotalBudget());
        assertEquals(0, bwants.getTotalOverspent());
    }

    @Test
    void addBoughtMultipleTest() {
        bwants.addBought(bw1, 1000);
        bwants.addBought(bw2, 80);
        List<Item> bwants2 = bwants.getBoughtList();

        assertEquals(2, bwants2.size());
        assertEquals(bw1, bwants2.get(1));
        assertEquals(bw2, bwants2.get(0));

        assertEquals(1086, bwants.getTotalPrice());
        assertEquals(910, bwants.getTotalBudget());
        assertEquals(170, bwants.getTotalOverspent());
    }

    @Test
    void filterOverspentTest1() {
        bwants.addBought(bw1, 899);
        List<Item> filterBwants = bwants.filterOverspent();

        assertEquals(0, filterBwants.size());
    }


    @Test
    void filterOverspentTest2() {
        bwants.addBought(bw1, 900);
        List<Item> filterBwants = bwants.filterOverspent();

        assertEquals(0, filterBwants.size());
    }

    @Test
    void filterOverspentTest3() {
        bwants.addBought(bw1, 901);
        List<Item> filterBwants = bwants.filterOverspent();

        assertEquals(1, filterBwants.size());
    }


    @Test
    void filterOverspentTwoOverspentTest() {
        bwants.addBought(bw1, 1000);
        bwants.addBought(bw2, 200);
        List<Item> filterBwants = bwants.filterOverspent();

        assertEquals(2, filterBwants.size());
        assertEquals(bw2, filterBwants.get(0));
        assertEquals(bw1, filterBwants.get(1));
    }

    @Test
    void filterOverspentOneOverspentTest() {
        bwants.addBought(bw1, 800);
        bwants.addBought(bw2, 20);
        List<Item> filterBwants = bwants.filterOverspent();

        assertEquals(1, filterBwants.size());
        assertTrue(filterBwants.contains(bw2));
        assertFalse(filterBwants.contains(bw1));
    }

    @Test
    void filterOverspentNoneOverspentTest() {
        bwants.addBought(bw1, 800);
        bwants.addBought(bw2, 9);
        List<Item> filterBwants = bwants.filterOverspent();

        assertEquals(0, filterBwants.size());
        assertFalse(filterBwants.contains(bw2));
        assertFalse(filterBwants.contains(bw1));
    }

    @Test
    void filterOverspentMultipleTest() {
        bwants.addBought(bw1, 903);
        bwants.addBought(bw2, 10);
        bwants.addBought(bw3, 5);
        List<Item> filterBwants = bwants.filterOverspent();

        assertEquals(2, filterBwants.size());
        assertEquals(bw3, filterBwants.get(0));
        assertEquals(bw1, filterBwants.get(1));
    }

    @Test
    void getTotalPriceTest() {
        bwants.addBought(bw1, 900);

        assertEquals(900, bwants.getTotalPrice());
    }

    @Test
    void getTotalPrice2Test() {
        bwants.addBought(bw1, 9);
        bwants.addBought(bw2,30);

        assertEquals(39, bwants.getTotalPrice());
    }

    @Test
    void getTotalPrice3Test() {
        bwants.addBought(bw1, 9);
        bwants.addBought(bw2,30);
        bwants.addBought(bw3, 104);

        assertEquals(143, bwants.getTotalPrice());
    }

    @Test
    void getTotalOverspentTest1() {
        bwants.addBought(bw2, 9);

        assertEquals(0, bwants.getTotalOverspent());
    }

    @Test
    void getTotalOverspentTest2() {
        bwants.addBought(bw2, 10);

        assertEquals(0, bwants.getTotalOverspent());
    }

    @Test
    void getTotalOverspentTest3() {
        bwants.addBought(bw2, 11);

        assertEquals(1, bwants.getTotalOverspent());
    }

    @Test
    void getTotalOverspentMultiple1() {
        bwants.addBought(bw1, 1000);
        bwants.addBought(bw1, 90);

        assertEquals(180, bwants.getTotalOverspent());
    }








}
