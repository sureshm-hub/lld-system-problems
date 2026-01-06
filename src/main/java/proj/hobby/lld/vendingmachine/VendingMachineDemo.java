package proj.hobby.lld.vendingmachine;

import proj.hobby.lld.vendingmachine.entities.Coin;

public class VendingMachineDemo {

    public static void main(String[] args) {
        // Initialize & Setup VendingMachine
        VendingMachine machine = VendingMachine.getInstance();
        // Add Inventory
        machine.addItem("P1", "Candy", 10, 10);
        machine.addItem("P2", "Energy Bar",  25,30);
        machine.addItem("P3", "Chips", 20, 10);
        machine.addItem("P4", "Soda", 30, 5);

        // Buy Product
        machine.selectItem("P2");
        machine.pay(Coin.QUARTER);
        machine.dispense();
        machine.refundChage();

        // Buy Product with insufficient balance
        machine.selectItem("P1");
        machine.pay(Coin.NICKLE);
        machine.dispense();
        machine.pay(Coin.CENT);
        machine.dispense();

        // Update Inventory
        machine.addItem("P2", "Energy Bar", 20, 5);

        // Try invalid state
        machine.dispense();
    }
}
