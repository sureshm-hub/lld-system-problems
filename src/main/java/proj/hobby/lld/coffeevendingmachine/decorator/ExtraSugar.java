package proj.hobby.lld.coffeevendingmachine.decorator;

import proj.hobby.lld.coffeevendingmachine.enums.Ingredient;
import proj.hobby.lld.coffeevendingmachine.template.Coffee;

import java.util.HashMap;
import java.util.Map;

public class ExtraSugar extends CoffeeDecorator {

    public static final int COST = 2;
    public static final Map<Ingredient, Integer> ingredients = Map.of(Ingredient.SUGAR, 1);

    public ExtraSugar(Coffee coffee) {
        super(coffee);
    }

    // Decorate Coffe methods
    @Override
    public void prepare() {
        decoratedCoffee.prepare();
        System.out.println("Mixing Extra Sugar.");
    }

    @Override
    public String getName() {
        return super.getName()+" with Extra Sugar";
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
