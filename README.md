# Break down an LLD problem into behavioral, structural & creational design patterns 

---

## What each category really means

- **Creational:** Patterns that focus on how objects are created and how that creation logic is encapsulated or controlled.
- **Structural:** Patterns that focus on how classes/objects are composed or wrapped to form larger structures (adapters, wrappers, composites, facades).
- **Behavioral:** Patterns that focus on how objects communicate, share responsibilities, and vary algorithms at runtime.

---

## Step 1: Extract creation hotspots (Creational)

Ask in the LLD problem: "What needs controlled or flexible creation?"

### Typical signals:

- Need only one instance globally => Singleton.
- Need to hide concrete types or choose implementation at runtime => Factory Method / Abstract Factory.
- Need to build complex objects step by step or with many optional fields => Builder.

### In an LLD like Parking Lot or Ride Sharing, you’d tag:

- "Create different vehicle types / rides without exposing concrete classes" => Factory (creational).
- "Global configuration / logger / metrics registry" => Singleton (creational).

---

## Step 2: Extract composition & wrapping (Structural)

Ask: "Where do I need to assemble objects into bigger structures or wrap one in another?"

### Typical signals:

- Need a single simple API over a complex subsystem => Facade.
- Need to adapt an existing interface to match a required one => Adapter.
- Need tree-like part–whole hierarchies (e.g., folder–file, menu–submenu) => Composite.

### In an LLD like "Design a chess game" or "Design a feed system," you’d tag:

- "One GameController exposing start(), move(), undo() hiding subsystems" => Facade (structural).
- "Different external payment APIs normalized behind a common interface" => Adapter (structural).

---

## Step 3: Extract behavior/algorithms & collaboration (Behavioral)

Ask: "Where do algorithms vary, and how do objects notify or coordinate with each other?"

### Typical signals:

- Many interchangeable algorithms with same input/output => Strategy.
- Some object changes and many others must react => Observer.
- You want to pass a request along a chain until one handler accepts it => Chain of Responsibility.

### In common LLDs:

- Parking, pricing, or sorting strategies in e‑commerce => Strategy (behavioral).
- Notifications when order status changes => Observer (behavioral).

---

# How to speak this in an interview

You can structure your thinking out loud like this:
- "First I’ll handle creation concerns using a Factory for vehicles and a Singleton for the configuration (creational patterns)."
- "Then I’ll introduce a Facade as a controller to hide internal subsystems from the client (structural pattern)."
- "Finally, for runtime behavior, I’ll use Strategy to plug in different pricing algorithms and Observer for notifications (behavioral patterns)."