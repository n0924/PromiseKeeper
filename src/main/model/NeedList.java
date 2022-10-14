package model;

import java.util.ArrayList;
import java.util.List;

// Represents a Need list, with no duplicated items
public class NeedList implements PlanList {
    private List<Item> needList;

    //EFFECTS: create empty need and want list
    public NeedList() {
        needList = new ArrayList<>();
    }

    //REQUIRES: item is needed
    //MODIFIES: this
    //EFFECTS: adds a needed item to the top of need list if not already in list.
    // otherwise, make no changes
    @Override
    public void addItem(Item item) {
        if (!needList.contains(item)) {
            needList.add(0, item);
        }
    }

    //REQUIRES: item is already in the need list, need list is non-empty
    //MODIFIES: this
    //EFFECTS: removes the given item from the need list, preserve order of list
    @Override
    public void removeItem(Item item) {
        needList.remove(item);
    }

    //REQUIRES: sizeNeed >= 1, priority is "High Priority", "Medium Priority" "Low Priority"
    //MODIFIES: this
    //EFFECTS: filter the needed list by the given priority, preserve order of original list
    // if no item with the given priority exists, return an empty list
    @Override
    public void filterByPriorityItem(String priority) {
        List<Item> filterOut = new ArrayList<>();

        for (Item item : needList) {
            if (!priority.equals(item.getPriority())) {
                filterOut.add(item);
            }
        }
        needList.removeAll(filterOut);

    }

    //REQUIRES: item is needed
    //EFFECTS: checks if an item already exists in needed list
    @Override
    public boolean containsItem(Item item) {
        return needList.contains(item);
    }


    //REQUIRES: item is needed
    //EFFECTS: checks how many elements are in the needed list
    @Override
    public int sizeList() {
        return needList.size();
    }

    //REQUIRES: item is needed, i>= 0
    // EFFECTS: get the element of need list at index i
    @Override
    public Item getItem(int i) {
        return needList.get(i);
    }

    @Override
    public List<Item> getList() {
        return needList;
    }
}