package proj.hobby.lld.coffeevendingmachine.factory;

import proj.hobby.lld.coffeevendingmachine.enums.CoffeeType;
import proj.hobby.lld.coffeevendingmachine.template.Coffee;

public class CoffeeFactory {

    public static Coffee get(CoffeeType coffeeType) {
        return switch(coffeeType) {
            case LATTE -> new Latte();
            case ESPRESSO -> new Espresso();
            case CAPUCCINO -> new Cappucino();
            case null ->  new Latte();
        };
    }
}
