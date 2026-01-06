package proj.hobby.lld.vendingmachine.state;

import proj.hobby.lld.vendingmachine.VendingMachine;
import proj.hobby.lld.vendingmachine.entities.Coin;

public class PaidState extends VendingMachineState {

    public PaidState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void selectItem(String code) {

    }

    @Override
    public void dispenseItem() {

    }

    @Override
    public void pay(Coin coin) {

    }

    @Override
    public void refundChange() {

    }
}
