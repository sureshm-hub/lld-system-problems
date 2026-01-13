package proj.hobby.lld.coffeevendingmachine.state;

import proj.hobby.lld.coffeevendingmachine.CoffeeVendingMachine;
import proj.hobby.lld.coffeevendingmachine.template.Coffee;

public class SelectionState implements CoffeeMachineState{

    private CoffeeVendingMachine coffeeMachine;

    public SelectionState(CoffeeVendingMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }
    @Override
    public void selectState(Coffee coffee) {
        coffeeMachine.setSelectedCoffee(coffee);
        coffeeMachine.setState(new PaymentState(coffeeMachine));
        System.out.println("Selected Coffee "+coffee.getName()+". Please pay: "+coffee.getPrice());
    }

    @Override
    public void pay(int amount) {
        System.out.println("Select a coffee first.");
    }

    @Override
    public void dispenseCoffee() {
        System.out.println("Select a coffee first.");
    }

    @Override
    public void cancel() {
        System.out.println("you haven't selecte a coffee.");
    }
}
