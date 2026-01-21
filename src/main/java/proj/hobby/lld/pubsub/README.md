# PubSub

---

## Problem Statement

Design & implement a pub/sub system with "topics", publish & subscribe to topics to send/receive message functionality.

---

## Requirements

- Create Topics
- Publish Messages
- Subscribe/Unsubscribe to topics
- Asynch Delivery
- Multiple topics, Multiple subscribers

## NFR's

- Extensibility
- Scalability
- Guaranteed Deliver -  At least once
- Deliver once & only once

---

## Entities

- Topic
  * **fields** name, Set<Subscribers> subscribers, ExecutorService executor
  * **methods** broadcast(message), addSubscriber(subscriber), removeSubscriber(subscriber)

- Message
  * **fields** payload, createdAt
  * **methods** getPayLoad()
  
- Subscriber <<Interface>>
  * getId()
  * onMessage(Message)

- AlertSubscriber implements Subscriber
  * **fields** id
  * **methods** onMessage(Message)

- Broker
  * **fields** private static final instance, Executor executor, Map<String, Topic> registry
  * **methods** static getInstance(), createTopic(topicName), subscribe(topicName, subscriber), 
                unSubscribe(topicName, subscriber), publish(topicName, message), shutdown()

- PubSubDemo
  1. Create Broker
  2. Create News Topic
  3. Create Sports Topic
  4. Create subscriber1, subscriber2
  5. subscriber1 Subscribe to News Topic
  6. subscriber2 Subscribe to Sports Topic
  7. publish 2 messages to Sports Topic
  8. publish a message to News Topic
  9. subscriber1 Unsubscribe to News Topic
  10. publish another message to News Topic
  11. shutdown Broker

---

## Design Notes

### Design Patterns

- Singleton/Facade - Broker
- Observer Topic to Subscriber notification asynchronously


### Do's

- current implementation uses "tightly coupled" Observer pattern

### Don't s

### Follow-ups:
- no Publisher Entity
- no message durability
- no ability to receive messages asynch for subscriber (have to register before message is published)
- decouple multiple clients on same topic using "app" so we can scale subscribers independently