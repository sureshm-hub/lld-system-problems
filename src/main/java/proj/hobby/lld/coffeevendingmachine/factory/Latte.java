package proj.hobby.lld.coffeevendingmachine.factory;

import proj.hobby.lld.coffeevendingmachine.enums.CoffeeType;
import proj.hobby.lld.coffeevendingmachine.enums.Ingredient;
import proj.hobby.lld.coffeevendingmachine.template.Coffee;

import java.util.Map;

public class Latte extends Coffee {
    @Override
    public void addIngredients() {
        getRecipe();
        System.out.println("adding Ingredients & foam");
    }

    @Override
    public String getName() {
        return CoffeeType.LATTE.name();
    }

    @Override
    public int getPrice() {
        return 5;
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        return Map.of(Ingredient.SUGAR, 1, Ingredient.WATER, 10, Ingredient.MILK, 10, Ingredient.COFFEE_BEANS, 20);
    }
}
