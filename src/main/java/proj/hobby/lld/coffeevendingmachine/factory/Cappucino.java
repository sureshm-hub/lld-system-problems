package proj.hobby.lld.coffeevendingmachine.factory;

import proj.hobby.lld.coffeevendingmachine.enums.CoffeeType;
import proj.hobby.lld.coffeevendingmachine.enums.Ingredient;
import proj.hobby.lld.coffeevendingmachine.template.Coffee;

import java.util.Map;

public class Cappucino extends Coffee {
    @Override
    public void addIngredients() {
        getRecipe();
        System.out.println("adding Ingredients, steamed milk & foam");
    }

    @Override
    public String getName() {
        return CoffeeType.CAPUCCINO.name();
    }

    @Override
    public int getPrice() {
        return 4;
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        return Map.of(Ingredient.SUGAR, 1, Ingredient.WATER, 5, Ingredient.MILK, 15, Ingredient.COFFEE_BEANS, 15);
    }
}
