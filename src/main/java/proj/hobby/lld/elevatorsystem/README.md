## Problem Statement

Elevator System: Design & Implement an Elevator System for a multi-floor building.

----

## Requirements/Clarifying questions --> Clarify 

- **Functional:** 
  - How many elevators? and how many floors?
  - Are we implementing Hall Calls or Destination Dispatch?
  - Are there any capacity constraints, door states, or emergency stops?
  - Is the Elevator moved through simulation? or hardware interrupts?
  - **Edge Cases:**
    - Invalid Floor
    - Cancelling Selection
    - Requests from floor elevator is already on  
    - An elevator moving UP should generally not stop for a DOWN request on the
      same floor

- **Non-Functional:**
  - How System behaves? — focusing on quality attributes like efficiency, 
    safety, and maintainability rather than basic features
  - Performance & Efficiency:
    - Minimal Wait Time
    - Throughput
    - Energy Efficiency
  - Scalability & Extensibility:
    - variable loads: scale from 3 elevators and 10 floors to 30+ elevator bank 
      and 100 floors
    - Pluggable Strategies: FCFS, SCAN
    - Modular Design: decouple components so new features can be added like
      VIP priority or maintenance modes
  - Reliability & Fault Tolerance:
    - HA: Controller should be functional even if the individual elevators go 
      offline
    - Error Recovery: System to have a clearly defined state for 'Out Of 
      Service' to ensure faulty elevators are ignored by dispatcher
    - Durability: In the current event of system reboot, state of pending 
      requests should be recoverable (typically "in-memory" for interviews)
  - Safety & Compliance:
    - Safety Constraints: 
      - Doors must be closed before elevator moves
      - Ignore floors out of Building Range
    - Weight/Capacity Limits: prevent movement if over capacity
    - Emergency Mode: Built in logic to handle emergency stops or fire alarms 
      by directing cars to nearest or groundfloor
  - Concurrency & Thread Safety:
    - for multithreaded scenarios state transitions and request queues are 
      guarded by locks or atomic
    - low latency processing: time taken to decide which elevator to dispatch
      should be negligible compared to physical movement of car

---

## Core Objects --> Identify

- Building
- Elevator Controller
- Elevator
- Request

- Button: Abstract Base Class for Hall Button (External) & Car Button (Internal)

- Direction (UP, DOWN)
- SchedulingStrategy
- NearestElevatorScheduler

- ElevatorState (MovingUpState, MovingDownState, IdleState) 
- ElevatorObserver
- ElevatorDisplay

---

## High-Level Design --> Define
  
## Implementation Outline --> Implement
  - Elevator Controller uses ExecutorService to run each Elevator in a long lived
    thread
  - Each Elevator lives independently and process its own queue
  - Thread Per Entity: number of Threads match number of Elevators
  - Async Request Handling: from Controller (producer) to Elevators (consumer) internal queue
  - A Single controller thread managing multiple Elevators State is not scalable:
    - One Elevator Sleeping for 5 mins while doors open will hold up the entire system
  - **Design Patterns At Play:**
    - Active Object Pattern: Encapsulate a method execution in its own thread so
      caller doesn't have to wait for the result.
    - Producer/Consumer: Controller is the producer, Elevator is the consumer
    - Master-Worker: Controller acts as master that distributes work (requests) to the
      Elevator (Worker threads)
  - **Summary**: Elevator is super responsive and is not blocked by individual 
    Elevator's door delays or physical movements
  - **addRequest()** Because ElevatorController and Elevator are running in independent 
    threads, requests runs risk of synchronization or race condition issues. We need to 
    add synchronization:
    - synchronized method: least preferable
    - wait/notify pattern:
      - Use ReentrantLock or object as mutex
      - Only one thread enters guarded section and after adding request notify's worker thread
      - Elevator in its run loop wait's on lock (lock.wait()) if requests are empty
    - ConcurrentSkipListSet: Thread-safe version of TreeSet

## Handle Follow-Ups  --> Verify & Extend

---

## Design Notes

### Design Patterns
- Singleton: ElevatorSystem
- Facade: Elevator System
- Strategy: ElevatorSchedulingStrategy
- State: ElevatorState
  - it's typical to pass the "context" (Elevator) itself to the State object 
    so ElevatorState can access encapsulated Elevator methods including 
    synchronization methods
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
  - ?? Building - use ElevatorSystem 
  - User - use Elevator Display as an Observer
  - UserRequestProcessor - Use ExecutorService from ElevatorSystem
  - ElevatorPosition (PARKED, RUNNING, FLOOR_STOP, DOORS_OPEN, DOORS_CLOSED) - too complex!
  - FastElevatorScheduler & IdleElevatorScheduler - use 1 stratedy NearestElevator
  - IElevatorState -> ElevatorState -> (FloorStopState, ParkedState, DoorOpenState, DoorClosedState, MovingState)
  - ElevatorEvent - pass the Elevator directly (this may be a simplified approach)
  - Elevator Attributes: capacity, size, elevatorNum (use id), executor (belongs to ElevatorController), currentFloorNumber (vs currentFloor)
  - UserRequest (use Request)
  - Request - floor (vs targetFloor)