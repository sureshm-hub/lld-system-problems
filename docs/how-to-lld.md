To solve any Low-Level Design (LLD) problem in a tech interview, use a
repeatable, phased framework. Most interviews last 45–60 minutes, so managing
your time across these steps is critical.

**Mnemonic:** Clarify -> Identify -> Define -> Implement -> Verify

**Phase 1:** Clarify & Gather Requirements (~5 min)
Don't start coding immediately. The goal is to **turn a vague prompt into a
concrete specification.**

- **Primary Capabilities:** Identify what the system must do (e.g., for a Parking
Lot: "Park a vehicle," "Generate a ticket").
- **Constraints:** Ask about rules like capacity, multi-threading needs, or
concurrency.
- **Scope:** Define what is "in" vs "out" (e.g., do we need to handle payments or 
just the parking logic?).

**Phase 2:** Define Entities & Relationships (~5 min)
Scan your requirements for the main **"nouns"** that _**maintain state or enforce
rules.**_

- **Identify Objects:** List core entities like User, Vehicle, or Booking.
Find the Orchestrator: Identify the "manager" class that coordinates the
workflow (e.g., ParkingLotManager, ElevatorSystem).
- **Establish Relationships:** Determine "has-a" vs "is-a" connections. Prefer
composition over inheritance to keep the design flexible.
- **Define Functionalities (Verbs):** Identify key actions like moveUp(), 
moveDown(), openDoor(), closeDoor(), sendRequest(), and handleRequest().


**Phase 3:** Detailed Class Design (~15-20 min)
_**Move from high-level entities to defining fields and methods for each.**_

**Assign Responsibilities:** Use SOLID Principles to ensure each class has a 
single, clear purpose.
**Apply Design Patterns:** Only use them if they naturally solve 
a problem. Common interview-ready patterns include:
- Strategy: For interchangeable algorithms (e.g., different pricing models).
- Factory: To decouple object creation logic from usage.
- Observer: For event-driven updates (e.g., notifying users when a driver is
    found).
- Singleton: For shared global resources (use sparingly).

**Phase 4:** Implementation & Execution (~15 min)
_**Focus on implementing the most complex or "interesting" methods first.**_

**Clean Code:** Write modular, readable code that handles edge cases and invalid
inputs.
**State Transitions:** Ensure your methods correctly update the internal state of
your objects (e.g., marking a parking spot as "Occupied").
**Concurrency and Thread Safety:** using locks or thread-safe queues

**Phase 5:** Verification & Trade-offs (~5 min)
**Walkthrough:** Pick one non-trivial scenario and step through it 
"tick-by-tick" to prove the logic works.
**Discuss Extensibility:** Explain how your design could handle future changes, 
such as adding a new vehicle type or a new search algorithm.

Would you like to apply this framework to a specific problem like a Parking Lot
or Movie Booking system?

**Key Takeaways for Success:**
- **"Why" > "What":** Explain your choices—why use a specific design pattern or 
  data structure (TreeSet over Queue)?
- **Structure:** Follow the 4-step framework of Clarify, Identify Entities, 
  Model Relationships, and Apply Patterns.

**Common Pitfalls:**
- Ignoring concurrency issues.
- Over-engineering/Overcomplicating the design; start with basic functionality 
  and iterate.
- Not clarifying constraints like maximum capacity.

https://logiclayer.medium.com/how-i-solve-any-lld-problem-in-20-minutes-using-a-4-step-framework-85ac1dc0631b