package proj.hobby.lld.vendingmachine.entities;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private final Map<String, Item> items = new HashMap<>();
    private final Map<String, Integer> stock = new HashMap<>();

    public void addItem(String code, Item item, final int qty) {
        items.put(code, item);
        stock.merge(code, qty, Integer::sum);
    }

    public Item getItem(String code) {
        return items.get(code);
    }

    public boolean isAvailable(String code) {
         return stock.getOrDefault(code, 0) > 0 ;
    }

    public void reduceStock(String code) {
        stock.merge(code, -1, Integer::sum);
    }
}
