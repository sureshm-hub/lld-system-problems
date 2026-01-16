# LinkedIn

---

## Problem Statement

Design & Implement a Linked in like networking service that allows users to create profile, manage connections, 
receive notifications in feed.

---

## Requirements

- Member Registration + Profile (Education, Skills, Experience) Building
- Connection Requests (send + accept)
- Posting + view news feed
- Post interaction (like/comment) -> notifications
- Basic Member Search ("String Search")

---

## Entities:

- Comment
- Connection
- Education
- Experience
- Like
- Member
- NewsFeed
- Notification
- Post
- Profile
- ConnectionStatus: PENDING, ACCEPTED, REJECTED, WITHDRAWN
- NotificationType: CONNECTION_REQUEST, POST_LIKE, POST_COMMENT

***

- NotificationObserver
- Subject

***

- ConnectionService
- NewsFeedService
- SearchService
- NotificationService

***

- FeedStrategy
- TimeSortStrategy

***

- LinkedInSystem
- LinkedInDemo

---

## Design Notes

### Design Patterns

- Singleton/Facade -> LinkedinSystem
- Builder -> Member
- Observer -> Notifications
- Strategy -> NotificationFeed
- Service -> SearchService, ConnectionService etc.

### Do's

### Don'ts

### Follow-ups:

- LinkedInSystem = Facade + Singleton 
  * “This is an orchestration layer / façade that exposes system-level APIs. In production,
  I’d avoid a hard Singleton and prefer DI, but for LLD it keeps a single entry point.”
- Member + Profile = Domain model (Builder Pattern)
  * “Builder keeps construction readable and prevents huge constructors as the profile grows.”
- ConnectionService = connection workflow + state
  * “This is the ‘transaction boundary’ for connection workflow. In real systems it would be persisted, 
  with idempotency and race handling.”
- NewsFeedService + NewsFeed = feed generation + display (Strategy Pattern)
  * “Strategy pattern keeps feed ranking extensible—swap chronological for ‘relevance’ 
  later without rewriting feed.”
- Post + Subject + NotificationObserver = notifications on interactions (Observer Pattern)
  * “Observer cleanly models event-driven notifications. In production, I’d replace direct calls with 
  an event bus/queue.”

#### Call out/unrealistic
- Data/persistence is in-memory
  * “I’d add repository interfaces (MemberRepo, PostRepo, ConnectionRepo) so storage can swap (SQL/NoSQL/graph).”
- Feed is naïve fan-out on read
  * For a user with 5k connections, reading feed = scan many lists
  * At scale, I’d use fan-out on write (precomputed timelines) for normal users, and hybrid for celebrities.
- Notifications are synchronous in-process calls
  * In production: publish event → queue (Kafka/SQS) → notification workers → push via websockets/APNs/email.
- Concurrency/idempotency is not handled
  * I’d make accept idempotent, enforce state transitions, and store request status with optimistic locking.
- Search is substring scan
  * I’d use an inverted index / Elasticsearch and support prefix + relevance.
- If the interviewer asks: “Design LinkedIn” — what do you implement in 45–60 mins?
  * Don’t try to build all of LinkedIn. Implement one vertical slice well:
  * Slice: connections + posts + feed + notifications
  * Domain: Member, Connection, Post, Notification
  * Services: ConnectionService, FeedService, NotificationPublisher
  * Patterns: Strategy (feed ranking), Observer/Event (notifications)
  * Storage: repository interfaces + in-memory impl
  * That maps 1:1 to what this repo demonstrates—so you can practice explaining it.

### Key Flows:

Flow A: Send connection request

``` java
LinkedInSystem.sendConnectionRequest(from,to)
ConnectionService.sendRequest(from,to)
create Connection(PENDING), store by requestId
create Notification(CONNECTION_REQUEST)
NotificationService.sendNotification(to, notification) → to.update(notification)
```

Flow B: Create post and view feed

```java
LinkedInSystem.createPost(memberId, content)
create Post(author, content)
NewsFeedService.addPost(author, post)
Viewing:
LinkedInSystem.viewNewsFeed(viewerId)
NewsFeedService.displayFeedForMember(viewer, strategy)
gather posts from viewer.getConnections()
NewsFeed.display(strategy) sorts + prints
```
* current approach is fan-out on read (build feed at read time).

Flow C: Like/comment triggers notification
```java
bobsPost.addLike(alice)
Post creates Notification(POST_LIKE, “…”)
notifyObservers(notification) → calls author.update(notification)
author sees it via viewNotifications() (marks read)
```

- I’ll design an MVP LinkedIn slice: members, connections, posts, feed, notifications, and search.
- I’ll separate domain objects (Member/Profile/Post/Connection) from services (ConnectionService/NewsFeedService/SearchService).
- Notifications are event-driven using Observer so interactions on posts don’t tightly couple to delivery.
- Feed sorting is Strategy-based so we can evolve ranking.
- Then I’ll call out scaling: persistence, graph queries for connections, feed fanout strategy, and async notifications.