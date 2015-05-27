package com.example.phill.bechvinapp.dummy;

import com.example.phill.bechvinapp.Model.Order;
import com.example.phill.bechvinapp.Model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */

    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        // Add 3 sample items.
        addItem(new DummyItem("Fine Wine", "Spain"));
        addItem(new DummyItem("Decent Wine", "France"));
        addItem(new DummyItem("Bad Wine", "Poland"));
    }

    public static ArrayList<Order> orders = new ArrayList<>();
    static

    {
        Product p1 = new Product("Vin1","1",2.1);
        Product p2 = new Product("Vin2","2",2.2);
        Product p3 = new Product("Vin3","3",2.3);
        Product p4 = new Product("Vin4","4",2.4);

        HashMap<Product, Integer> dummyHashMap = new HashMap<>();
        dummyHashMap.put(p1,1);
        dummyHashMap.put(p2, 2);
        dummyHashMap.put(p3, 3);
        dummyHashMap.put(p4, 4);

        java.util.Date date = new java.util.Date();

        Order o1 = new Order("1",dummyHashMap,date,"");
        Order o2 = new Order("2",dummyHashMap,date,"");
        Order o3 = new Order("3",dummyHashMap,date,"");
        Order o4 = new Order("4",dummyHashMap,date,"");

        orders.add(o1);
        orders.add(o2);
        orders.add(o3);
        orders.add(o4);
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String content;

        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
