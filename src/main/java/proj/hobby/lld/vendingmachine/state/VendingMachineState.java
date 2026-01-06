package proj.hobby.lld.vendingmachine.state;

import proj.hobby.lld.vendingmachine.VendingMachine;
import proj.hobby.lld.vendingmachine.entities.Coin;

public abstract class VendingMachineState {

    protected VendingMachine machine;

    VendingMachineState(VendingMachine machine) {
        this.machine = machine;
    }

    public abstract void selectItem(String code);
    public abstract void dispenseItem();
    public abstract void pay(Coin coin);
    public abstract void refundChange();
}
