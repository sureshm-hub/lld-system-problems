package proj.hobby.lld.coffeevendingmachine.decorator;

import proj.hobby.lld.coffeevendingmachine.enums.Ingredient;
import proj.hobby.lld.coffeevendingmachine.template.Coffee;

import java.util.Map;

public abstract class CoffeeDecorator extends Coffee {

    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    // Decorate Coffe methods
    @Override
    public void prepare() {
        decoratedCoffee.prepare();
    }

    @Override
    public void addIngredients() {
        decoratedCoffee.addIngredients();
    }

    @Override
    public String getName() {
        return decoratedCoffee.getName();
    }

    @Override
    public int getPrice() {
        return decoratedCoffee.getPrice();
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        return decoratedCoffee.getRecipe();
    }

}
