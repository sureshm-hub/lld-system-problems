package proj.hobby.lld.vendingmachine.state;

import proj.hobby.lld.vendingmachine.VendingMachine;
import proj.hobby.lld.vendingmachine.entities.Coin;

public class AcceptingState extends VendingMachineState{

    public AcceptingState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void selectItem(String code) {
        if(!machine.isAvailable(code)) {
            System.out.printf("Item not available: %s", code);
            return;// stay in current state
        }
        machine.setSelectedItem(code);
        machine.setState(new ItemSelectedState(machine)); // state transition
        System.out.printf("Item selected: %s", code);
    }

    @Override
    public void dispenseItem() {
        System.out.println("Please select an item first.");
    }

    @Override
    public void pay(Coin coin) {
        System.out.println("Please select an item first.");
    }

    @Override
    public void refundChange() {
        System.out.println("Please select an item first.");
    }
}
