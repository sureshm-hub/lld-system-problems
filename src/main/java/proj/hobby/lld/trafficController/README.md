# Traffic Control System

---

## Problem Statement

Design & Implement a traffic control system that manages traffic lights at an Intersection. System should 
provide configurable signal timings with manual override option as well as automatic cycling of Signals.

---

## Requirements

- manage traffic lights
- signal timings
- override signal
- auto signal management

---

## Entities

- Intersection
- IntersectionState
- NorthSouthGreenState
- EastWestGreenState
- TrafficLight
- TrafficSignal
- SignalState
- YellowState
- RedState
- GreenState
- TrafficObservers
- CentralMonitor
- TrafficControlSystem
- TrafficControllerDemo
- enums: Direction, SignalColor

---

## Design Notes

### Design Patterns
- Singleton: TrafficControllerSystem
- Builder: Intersection
- State: SignalState, IntersectionState
- Observer: TrafficObserver

### Do's
- Intersection implements Runnable to loosely couple with Executor Framework
- State Transition for SignalState handled from Intersection
- RedState
- TrafficLight
- Intersection
- TrafficLight taking part in Observer pattern

### Don'ts
- SignalStateStrategy - too early to add to lld
