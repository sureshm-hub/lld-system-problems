package proj.hobby.lld.coffeevendingmachine;

import proj.hobby.lld.coffeevendingmachine.enums.CoffeeType;
import proj.hobby.lld.coffeevendingmachine.enums.Ingredient;
import proj.hobby.lld.coffeevendingmachine.enums.ToppingType;

import java.util.List;
import java.util.Map;

public class CoffeeVendingMachineDemo {

    public static void main(String[] args) throws InterruptedException {
        // 1. Setup Coffe Vending Machine & Inventory
        CoffeeVendingMachine coffeeMachine = CoffeeVendingMachine.getInstance();
        Inventory inventory = Inventory.getInstance();
        inventory.addIngredients(Map.of(Ingredient.SUGAR, 50, Ingredient.WATER, 100, Ingredient.MILK, 100, Ingredient.COFFEE_BEANS, 200, Ingredient.SYRUP, 25));
        System.out.println("==== Inventory set ====");

        // 2. Select Coffee
        coffeeMachine.selectState(CoffeeType.ESPRESSO, List.of(ToppingType.EXTRA_SUGAR));
        coffeeMachine.pay(50);
        coffeeMachine.dispenseCoffee();
        System.out.println("==== Espresso Dispensed ====");

        // 3. Select Coffee & Cancel
        coffeeMachine.selectState(CoffeeType.LATTE, List.of(ToppingType.EXTRA_SYRUP));
        coffeeMachine.pay(100);
        coffeeMachine.cancel();
        coffeeMachine.dispenseCoffee();
        System.out.println("==== Latte Dispensed ====");

        // 4. check inventory
        inventory.printInventory();
        System.out.println("==== Inventory Printed ====");

        // 5. Select Coffee & Cancel
        coffeeMachine.selectState(CoffeeType.CAPUCCINO, List.of(ToppingType.EXTRA_SYRUP));
        coffeeMachine.cancel();
        System.out.println("==== Capuccino Canceled ====");

        Thread.sleep(1000);
    }
}
