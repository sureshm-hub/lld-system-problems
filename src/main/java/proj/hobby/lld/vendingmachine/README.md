# Vending Machine

---

## Problem Statement

Design & Implement a Vending Machine for users to buy, pay Products. The system should manage inventory & handle payments.

---

## Requirements

- Product Management
- Inventory Management
- Payment Handling
- State Management
- User Interaction

---

## Entities:

- Item
- Coin
- VendingMachine
- VendingMachineState
- AcceptingState
- SelectProductState
- DispensingState
- ReturnChangeState

---

## Design Notes

### Design Patterns

- **singelton** VendingMachine
- **state** VendingMachineState

### Do's 

- Inventory loosely couples Item/Product to VendingMachine
- VendingMachine selectItem() & VendingMachine setSelectedItem()
- State Transition: AcceptingState -> ItemSelectedState -> PaidState -> AcceptingState
-  reset




