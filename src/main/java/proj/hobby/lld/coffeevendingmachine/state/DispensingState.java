package proj.hobby.lld.coffeevendingmachine.state;

import proj.hobby.lld.coffeevendingmachine.CoffeeVendingMachine;
import proj.hobby.lld.coffeevendingmachine.Inventory;
import proj.hobby.lld.coffeevendingmachine.template.Coffee;

public class DispensingState implements CoffeeMachineState{

    private CoffeeVendingMachine coffeeMachine;

    public DispensingState(CoffeeVendingMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    @Override
    public void selectState(Coffee coffee) {
        System.out.println("Payment made, new coffee selection not allowed.");
    }

    @Override
    public void pay(int amount) {
        System.out.println("Payment made, wait for coffee.");
    }

    @Override
    public void dispenseCoffee() {
        // Manage inventory
        Inventory inventory = Inventory.getInstance();
        Coffee coffee = coffeeMachine.getSelectedCoffee();
        // If not enough ingredients - refund payment and move to ready state
        if(!inventory.hasIngredients(coffee.getRecipe())) {
            OutOfServiceState outOfServiceState = new OutOfServiceState(coffeeMachine);
            coffeeMachine.setState(outOfServiceState);
            outOfServiceState.dispenseCoffee();
            outOfServiceState.cancel();
            return;
        }
        // prepare coffee
        inventory.deductIngredients(coffee.getRecipe());
        coffee.prepare();
        // Return Change
        int balance = coffeeMachine.getMoney() - coffee.getPrice();
        System.out.println("Refund Balance: "+balance);
        // set state
        coffeeMachine.reset();// reset machine
        coffeeMachine.setState(new SelectionState(coffeeMachine));
    }

    @Override
    public void cancel() {
        System.out.println("Coffee preparation in progress. Cannot cancel now.");
    }
}
