package proj.hobby.lld.coffeevendingmachine;

import proj.hobby.lld.coffeevendingmachine.decorator.ExtraSugar;
import proj.hobby.lld.coffeevendingmachine.decorator.ExtraSyrup;
import proj.hobby.lld.coffeevendingmachine.enums.CoffeeType;
import proj.hobby.lld.coffeevendingmachine.enums.ToppingType;
import proj.hobby.lld.coffeevendingmachine.factory.CoffeeFactory;
import proj.hobby.lld.coffeevendingmachine.state.CoffeeMachineState;
import proj.hobby.lld.coffeevendingmachine.state.SelectionState;
import proj.hobby.lld.coffeevendingmachine.template.Coffee;

import java.util.List;

public class CoffeeVendingMachine {

    private static final CoffeeVendingMachine INSTANCE = new CoffeeVendingMachine();
    private Coffee selectedCoffee;
    private CoffeeMachineState state;
    private int money;

    public CoffeeVendingMachine () {
        this.state = new SelectionState(this);
        this.money = 0;
    }

    public static CoffeeVendingMachine getInstance() {return INSTANCE;}

    public void selectState(CoffeeType coffeeType, List<ToppingType> toppings) {
        Coffee coffee = CoffeeFactory.get(coffeeType);

        for(ToppingType topping : toppings) {
            coffee = switch(topping) {
                case EXTRA_SUGAR -> new ExtraSugar(coffee);
                case EXTRA_SYRUP -> new ExtraSyrup(coffee);
            };
        }
        this.state.selectState(coffee);
    }
    public void pay(int amount) {
        this.state.pay(amount);
    }
    public void dispenseCoffee() {
        this.state.dispenseCoffee();
    }

    public void cancel() {
        this.state.cancel();
    }

    // --- Getters/Setters
    public void setSelectedCoffee(Coffee selectedCoffee) {
        this.selectedCoffee = selectedCoffee;
    }
    public Coffee getSelectedCoffee() {
        return selectedCoffee;
    }
    public void setState(CoffeeMachineState state) {
        this.state = state;
    }
    public CoffeeMachineState getState() {
        return state;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void reset() {
        this.selectedCoffee = null;
        this.money = 0;
    }
}
