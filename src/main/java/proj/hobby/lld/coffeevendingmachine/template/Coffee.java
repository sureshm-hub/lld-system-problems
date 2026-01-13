package proj.hobby.lld.coffeevendingmachine.template;

import proj.hobby.lld.coffeevendingmachine.enums.Ingredient;

import java.util.Map;

public abstract class Coffee {

    public void prepare() {
        System.out.println("preparing "+ getName()+"...");
        grindBeans();
        brew();
        addIngredients();
        pourIntoCup();
        System.out.println(getName()+" is ready!");
    }

    public void grindBeans() {
        System.out.println("Grinding Beans.");
    }

    public void brew() {
        System.out.println("Brewing Coffee.");
    }

    public void pourIntoCup() {
        System.out.println("Pouring into Cup.");
    }

    // methods for subclasses to provide
    public abstract void addIngredients();
    public abstract String getName();
    public abstract int getPrice();
    public abstract Map<Ingredient, Integer> getRecipe();

}
