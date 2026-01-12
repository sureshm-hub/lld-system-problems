# Elevator 

---

## Problem Statement

Design & Implement an Elevator System for a multi-floor building.

----

## Requirements

- Multiple Elevators
- User Request Handling
- Direction Management
- State Management

---

## Entities

- Elevator
- Request
- Direction (UP, DOWN)
- SchedulingStrategy
- NearestElevatorScheduler
- ElevatorState (MovingUpState, MovingDownState, IdleState) 
- ElevatorObserver
- ElevatorDisplay
- ElevatorController
- ElevatorSystemDemo

---

## Design Notes

### Design Patterns

- Singleton: ElevatorSystem
- Facade: Elevator System
- Strategy: ElevatorSchedulingStrategy
- State: ElevatorState
- Observer: ElevatorObserver

### Do's

- Elevator implement Runnable
- Elevator separate requests for up & Down
- Elevator State: move(Elevator), currentDirection(), addRequest(Elevator, Request request) 
- ElevatorObserver: handle(Elevator)
- ElevatorSchedulingStrategy: 
- Elevator.notifyObserver() // 1. initial addition of observer 2. state change 3. on floor change
- Elevator isRunning defaults to true - so Elevator can move when submitted to Executor
- IdleState handle Current Floor Requests
- State Transitions: MoveUp/MoveDown -> Idle, Idle -> MoveUp/MoveDown

### Don'ts

- Floor - use floor number
- Building - use ElevatorSystem
- User - use Elevator Display as an Observer
- UserRequestProcessor - Use ExecutorService from ElevatorSystem
- ElevatorPosition (PARKED, RUNNING, FLOOR_STOP, DOORS_OPEN, DOORS_CLOSED) - too complex!
- FastElevatorScheduler & IdleElevatorScheduler - use 1 stratedy NearestElevator
- IElevatorState -> ElevatorState -> (FloorStopState, ParkedState, DoorOpenState, DoorClosedState, MovingState)
- ElevatorEvent - pass the Elevator directly (this may be a simplified approach)
- Elevator Attributes: capacity, size, elevatorNum (use id), executor (belongs to ElevatorController), currentFloorNumber (vs currentFloor)
- UserRequest (use Request)
- Request - floor (vs targetFloor)