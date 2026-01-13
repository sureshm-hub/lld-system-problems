package proj.hobby.lld.coffeevendingmachine;

import proj.hobby.lld.coffeevendingmachine.enums.Ingredient;

import java.util.Map;

public class Inventory {

    private static Inventory INSTANCE = new Inventory();

    private Inventory(){
    }

    public static Inventory getInstance() { return INSTANCE; }

    public void addIngredients(Map<Ingredient, Integer> stock) {
        System.out.println("adding ingredients to inventory");
    }

    public boolean hasIngredients(Map<Ingredient, Integer> recipe){
        System.out.println("checking ingredients in inventory");
        return true;
    }

    public synchronized void deductIngredients(Map<Ingredient, Integer> stock) {
        System.out.println("deducting ingredients");
    }

    public void printInventory(){
        System.out.println("printing inventory");
    }

}
