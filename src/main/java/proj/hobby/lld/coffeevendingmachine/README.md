# Coffee Vending Machine

---

## Problem Statement

Design & Implement a coffee vending machine. User should be able to select coffee & pay

---

## Requirements

- Select Coffee Types
- Pay & Receive change
- Refill ingredients

---

## Entities

- Coffee
- CoffeeFactory
- CoffeeType
- Inventory
- CoffeeVendingMachine
- CoffeeVendingMachineDemo

---

## Design Notes

### Design Pattern

- Template Method: Coffee
- Singleton: VendingMachine
- Factory: Tea, Coffee
- State: VendingMachineState
- Decorator: ExtraSugar, ExtraMilk

### Do's

- States: SelectionState -> PaymentState -> DispensingState -> OutOfServiceState
- StateMethods: selectCoffee(), pay(), dispense(), cancel()
- OutOfServiceState
- Coffee - templateMethod
- coffeeFactory
- VendingMachine reset()
- selectCoffee() in VendingMachine vs SelectionState.selectCoffee()
- Inventory 

### Don'ts