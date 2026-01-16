# ATM

---

## Problem Statement

Design & Implement ATM to check balance, deposit/withdraw cash and print statements.

---

## Requirements

---

## Entities

ATMSystem: currentCard, Facade for authentication, user operations + integrate with bankingService
***
ATMState: insertCard(), enterPin(), selectOperation(), eject()
ReadyState
AuthenticatingState
OperationState

ReadyState -> AuthenticatingState -> OperationState

***
DispenseChain: setNext(), canDispense(), dispense()
NoteDispenser: Abstract class
NoteDispenser100
NoteDispenser50
NoteDispenser20
***
Account
Card
BankingService
CashDispenser
OperationType: DEPOSIT, WITHDRAW, CHECK_BALANCE

---

## Design Notes

### Design Pattern

Singleton: ATMSystem
Facade: ATMSystem
State: ATMState
ChainOfResponsibility: DispenseChain

### Do's

### Don'ts

### Follow-Ups:

- **Concurrency & Consistency:** The system handles concurrent access and ensures data consistency.
