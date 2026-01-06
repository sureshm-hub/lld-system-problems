package proj.hobby.lld.vendingmachine;

import proj.hobby.lld.vendingmachine.entities.Coin;
import proj.hobby.lld.vendingmachine.entities.Inventory;
import proj.hobby.lld.vendingmachine.entities.Item;
import proj.hobby.lld.vendingmachine.state.AcceptingState;
import proj.hobby.lld.vendingmachine.state.VendingMachineState;

public class VendingMachine {

    private static final VendingMachine INSTANCE = new VendingMachine();
    private final Inventory inventory = new Inventory();
    private VendingMachineState currentState;
    private int balance = 0;
    private String selectedItem;

    private VendingMachine() {
        currentState = new AcceptingState(this);
    }


    public static VendingMachine getInstance() {
        return INSTANCE;
    }

    public void pay(Coin coin) {
        currentState.pay(coin);
    }

    public void selectItem(String item) {
//        this.selectedItem = item; --- should be done from state
        currentState.selectItem(item);
    }


    public void dispense() {
        currentState.dispenseItem();
    }

    public void refundChage() {
        currentState.refundChange();
    }

    public void addItem(String code, String name, int price, int qty){
        // add to Inventory
        inventory.addItem(code, new Item(code, name, price), qty);
    }

    public void dispenseItem(String code) {
        // manage Inventory
    }

    public boolean isAvailable(String code) {
        return inventory.isAvailable(code);
    }

    public void reset() {
        this.selectedItem = null;
        this.balance = 0;
    }

    public void addBalance() {
        // manage balance
    }

    public Item getSelectedItem(String code) {
        return inventory.getItem(code);
    }

    public void setSelectedItem(String code) {
        this.selectedItem = code;
    }

    public void setState(VendingMachineState next) {
        this.currentState = next;
    }

    // getters for inventory, balance

    public int getBalance() {
        return balance;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public Inventory getInventory() {
        return inventory;
    }

}