package proj.hobby.lld.vendingmachine.entities;

public enum Coin {

    CENT(1),
    NICKLE(5),
    DIME(10),
    QUARTER(25);

    private int valueInCents;

    Coin(int valueInCents) {
        this.valueInCents = valueInCents;
    }

    public int getValueInCents() {
        return valueInCents;
    }
}
