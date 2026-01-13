package proj.hobby.lld.coffeevendingmachine.decorator;

import proj.hobby.lld.coffeevendingmachine.enums.Ingredient;
import proj.hobby.lld.coffeevendingmachine.template.Coffee;

import java.util.HashMap;
import java.util.Map;

public class ExtraSyrup extends CoffeeDecorator {
    public ExtraSyrup(Coffee coffee) {
        super(coffee);
    }

    public static final int COST = 3;
    public static final Map<Ingredient, Integer> ingredients = Map.of(Ingredient.SYRUP, 1);

    // Decorate Coffe methods
    @Override
    public void prepare() {
        decoratedCoffee.prepare();
        System.out.println("Mixing Extra Syrup.");
    }

    @Override
    public String getName() {
        return super.getName()+" with Extra Syrup";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + COST;
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        Map<Ingredient, Integer> recipe = new HashMap<>(decoratedCoffee.getRecipe());
        ingredients.forEach((ingredient, qty) -> recipe.merge(ingredient, qty, Integer::sum));
        return recipe;
    }
}
