package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoughtWantListTest {
    private BoughtWantList bwants;
    private Item bw1;
    private Item bw2;
    private Item bw3;

    @BeforeEach
    void setup() {
        bwants = new BoughtWantList();
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
        assertEquals(1, bwants.sizeBought());
        assertTrue(bwants.containsBought());

        assertEquals(800, bwants.getTotalPrice());
        assertEquals(900, bwants.getTotalBudget());
        assertEquals(0, bwants.getTotalOverspent())
    }

    @Test
    void addBoughtMultipleTest() {
        bwants.addBought(bw1, );

    }






}
