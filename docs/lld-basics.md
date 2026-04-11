# Why LLD?
 - LLD is not just about writing code that works, 
 - it’s about writing code that’s modular, testable, extensible, and easy to 
   reason about as the system grows.

# Components of LLD
 - Classes & Objects
 - Interfaces & Abstractions
 - Relationships between classes
 - Method Signatures
 - Design Patterns

# HLD vs LLD
 - HLD focuses on System, Architecture, Infra and Components - What?
 - LLD focuses on Implementation - how ?

# LLD 'abilities
 - maintainability: clean, debug, test, collaboration, extension (low coupling, high cohesion)
 - reusability
 - scalability

# LLD trade-off's
  - Deep inheritance vs Composition
  - Encapsulation / Rich Domain model vs Anemic model + Service Layer
  - Interface abstraction vs Direct concrete class
  - Normalized vs Denormalized data structures
  - Thread Safety vs Performance/Simple Design
  - Precomputation/caching vs On demand calculation

# OOP fundamentals
  - classes & objects
  - enums
  - interfaces
  - encapsulation
  - abstraction
  - inheritance
  - polymorphism

# class relationships
  - association
  - aggregation
  - composition
  - dependency
  - realization

# Design Principles
  - DRY
  - KISS
  - SOLID 
    - Single Responsibility
    - Open/Closed
    - Liskov's
    - Interface Segregation
    - Dependency Injection 
  - YAGNI: Implement things when you actually need them, never when you just foresee that you need them.
  - Law of Demeter: talk to your friends only, Avoid chaining objects & properties in a single statement
  - Single Responsibility: A class should have one, and only one, reason to change. 
  - GRASP: "General Responsibility Assignment Software Patterns"
    - creator
    - information expert
    - low coupling
    - high cohesion
    - controller
    - pure fabrication
    - indirection
    - polymorphism
  - Composition Principle: Composition over inheritance
  - Separation Of Concerns
  - Coupling vs Cohesion
  - What are they for? When can you violate?

# UML
 - Class Diagram 
 - Use Case Diagram
 - Sequence Diagram
 - Activity Diagram

# Design Patterns
## creational
  - Singelton
  - Factory Method
  - Builder
  - Abstract Factory
  - Prototype    
## structural
  - Adapter
  - Facade
  - Decorator
  - Composite
  - Proxy
  - Bridge
  - Flyweight
## behavioral
  - Iterator
  - Observer
  - Strategy
  - Command
  - State
  - Template Method
  - Visitor
  - Mediator
  - Momento
  - Chain Of Responsibility

# LLD interview template
  - Clarify (Functional Requirements/Use Cases)
  - Identify Entities (Core Objects & Relationships)
  - Define Responsibilities/Behavior
  - Implement
      - Class Design
      - API's/Interface/Interaction
      - Add Extensions
      - Design Patterns
      - Concurrency
      - Edge Cases/Exceptional Scenarios: No product to dispense in Vending M/C or No Parking available in Parking Lot
  - Summarize Trade-off's
      - performance vs readability
      - flexibility vs simplicity
      - When interviewer asks - Why you choose X? 
          - state alternatives - we could do Y instead of X.
          - prod - picked X because it optimizes A & B which match current requirements
          - cons - X comes at cost C & D
          - switch/mitigate - if system later needs E I will revisit Y or mitigate C/D using Z
  - What are they checking for? 
      - structure, build (maintainable, extensible) code
      - following design principles/patterns
      - can other developers trust?
      - Use 2–3 such trade-offs per problem; depth matters more than listing many.

# Mental "LLD checklist"
  - Requirements > Use cases > Core entities > Relationships
  - Class diagram > APIs > Interactions
  - Extensibility > Design patterns > Trade-offs
  - Error handling > Concurrency > Caching > Rate limits (if needed)

# LLD Problems:
## Easy:
- [X] Stack Overflow: https://blog.algomaster.io/p/how-to-answer-a-lld-interview-problem
- [X] Parking Lot
- [X] Vending Machine
- [X] Logging Framework
- [X] Traffic Signal Control System
- [X] Coffee Vending Machine
- Task Management System

## Medium:
- [X] ATM
- [X] LinkedIn
- LRU Cache
- Tic Tac Toe Game
- [X] Pub Sub System
- [X] Elevator System
- [...] Car Rental System
- Online Auction System
- Hotel Management System
- Digital Wallet Service
- Airline Management System
- Library Management System
- Social Network like Facebook
- Restaurant Management System
- Concert Ticket Booking System

## Hard: Booking/Reservation workflows (transactions + availability)
- Splitwise
- MovieTicketBookingDemo (seat map, locks/holds, expiry)
- FoodDeliveryServiceDemo (order workflow + dispatch + tracking)

# References:
- trade-off's: https://www.perplexity.ai/search/give-me-few-concrete-examples-oDvRQ.zIRpy08iXx3lQYaQ
- hld: https://algomaster.io/learn/lld/lld-vs-hld
- awesome-lld: https://github.com/sureshm-hub/awesome-low-level-design