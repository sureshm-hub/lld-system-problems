package proj.hobby.lld.coffeevendingmachine.factory;

import proj.hobby.lld.coffeevendingmachine.enums.CoffeeType;
import proj.hobby.lld.coffeevendingmachine.enums.Ingredient;
import proj.hobby.lld.coffeevendingmachine.template.Coffee;

import java.util.Map;

public class Espresso extends Coffee {
    @Override
    public void addIngredients() {
        getRecipe();
        System.out.println("adding Ingredients");
    }

    @Override
    public String getName() {
        return CoffeeType.ESPRESSO.name();
    }

    @Override
    public int getPrice() {
        return 7;
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        return Map.of(Ingredient.SUGAR, 0, Ingredient.WATER, 10, Ingredient.MILK, 5, Ingredient.COFFEE_BEANS, 40);
    }
}
