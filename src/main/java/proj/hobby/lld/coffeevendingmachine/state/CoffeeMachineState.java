package proj.hobby.lld.coffeevendingmachine.state;

import proj.hobby.lld.coffeevendingmachine.template.Coffee;

// SelectionState -> Payment State -> DispensingState ->  -> OutOfServiceState
public interface CoffeeMachineState {
    void selectState(Coffee coffee);
    void pay(int amount);
    void dispenseCoffee();
    void cancel();
}