package proj.hobby.lld.coffeevendingmachine.state;

import proj.hobby.lld.coffeevendingmachine.CoffeeVendingMachine;
import proj.hobby.lld.coffeevendingmachine.template.Coffee;

public class OutOfServiceState implements  CoffeeMachineState {
    private CoffeeVendingMachine coffeeMachine;
    public OutOfServiceState(CoffeeVendingMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    @Override
    public void selectState(Coffee coffee) {
        System.out.println("Machine is out of ingredients, please try after sometime.");
    }

    @Override
    public void pay(int amount) {
        System.out.println("Machine is out of ingredients, please try after sometime.");
    }

    @Override
    public void dispenseCoffee() {
        System.out.println("Machine is out of ingredients, please try after sometime.");
    }

    @Override
    public void cancel() {
        System.out.println("Transaction Cancelled, Refunding payment: "+ coffeeMachine.getMoney());
        coffeeMachine.reset();
        coffeeMachine.setState(new SelectionState(coffeeMachine));
    }
}
