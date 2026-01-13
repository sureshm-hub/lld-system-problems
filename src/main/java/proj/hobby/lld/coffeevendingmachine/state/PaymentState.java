package proj.hobby.lld.coffeevendingmachine.state;

import proj.hobby.lld.coffeevendingmachine.CoffeeVendingMachine;
import proj.hobby.lld.coffeevendingmachine.template.Coffee;

public class PaymentState implements CoffeeMachineState{

    private CoffeeVendingMachine coffeeMachine;
    public PaymentState(CoffeeVendingMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    @Override
    public void selectState(Coffee coffee) {
        System.out.println("Coffee already selected, continue to payment.");
    }

    @Override
    public void pay(int amount) {
        int totalPaid = coffeeMachine.getMoney() + amount;
        coffeeMachine.setMoney(totalPaid);
        int balance = coffeeMachine.getSelectedCoffee().getPrice() - totalPaid;
        if(balance <= 0) {
            coffeeMachine.setState(new DispensingState(coffeeMachine));
        } else {
            System.out.println("Pay the remaining balance for Coffee: "+ balance);
        }
    }

    @Override
    public void dispenseCoffee() {
        System.out.println("Coffee already selected, finish payment.");
    }

    @Override
    public void cancel() {
        System.out.println("Transaction Cancelled, Refunding payment: "+ coffeeMachine.getMoney());
        coffeeMachine.reset();
        coffeeMachine.setState(new SelectionState(coffeeMachine));
    }
}
