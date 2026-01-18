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

- Member (profile, Set<Members> connections , List<Notification> notifications, addConnection(), displayProfile(), 
          viewNotifications(), notify(), Builder) implements NotificationObserver
---
- Profile (summary, education, experience)
- Education
- Experience
---
- Connection (from, to, status, requestedAt)
- ConnectionStatus: PENDING, ACCEPTED, REJECTED, WITHDRAWN
---
- Notification (id, memberId, message, notificationType, createdAt)
- Post extends Subject (id, content, member, createdAt, List<Like> likes, List<Comment> comments)
- NotificationObserver (notify(Notification))
- Subject a.k.aObservable (addNotificationObserver() removeNotificationObserver(), notify(Notification))
- NotificationType: CONNECTION_REQUEST, POST_LIKE, POST_COMMENT
---
- Like (member, createdAt)
- Comment (member, text, createdAt)
---
- NewsFeed (List<Post> feed, display(FeedSortingStrategy))

***

- ConnectionService (NotificationService, Map<String, Connection> connectionRequests, sendRequest(Member from, to), 
                      acceptRequest(requestId))
- NewsFeedService (Map<String, List<Post>> allPosts, addPost(member, post), getMemberPosts(member), 
                   displayFeedForMember(member, FeedSortingStrategy))
- SearchService (List<Memeber> members, searchByName(name))
- NotificationService (sendNotification(member, notification))

***

- FeedStrategy (List<Post> sort(List<Post> posts))
- TimeSortStrategy

***

- LinkedInSystem
  - instance
  - Map<String, Member> members
  - connectionService
  - newsFeedService
  - searchService
  - getInstance()
  - registerMember(member)
  - sendConnectionRequest()
  - acceptConnectionRequest()
  - createPost()
  - getLatestPostByMember()
  - viewNewsFeed()
  - searchMemberByName()
- LinkedInDemo
  1. Member registrations
  2. Connection Management
  3. Posting & NewsFeed
  4. Interacting with Post (like, comment)
  5. SearchMembers
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
- Notification -> replace isRead with Set<Members> readMembers

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

---

"Fan-out" - refers to how a system distributes data from a single source to multiple destinations
e.g., distributing a tweet to thousands of followers

* Fan-out on Write (Push Model):
  When a user creates a post, the system immediately pushes that data to all of their followers' feeds.
  Best for: Systems with a high read-to-write ratio (e.g., social media timelines for normal users).

* Fan-out on Read (Pull Model):
  When a user opens their app, the system aggregates the posts from the people they follow and generates the feed on-the-fly.
  Systems with extremely high follower counts or where data is rarely accessed.

* Hybrid Approach
  A combination of both methods
  Fan-out-on-write is used for normal users, while fan-out-on-read is used for celebrities/high-followers to avoid massive, immediate write spikes.

---

APN - Apple Push Notification service
FCM - Android's Firebase Cloud Messaging

---
substring scanning reads every single document
Inverted Index - tokenize/pre-processes data on write to create an index of word to document/row speeding up querying

---

### Key Flows:

- If the interviewer asks: “Design LinkedIn” — what do you implement in 45–60 mins?
  * Don’t try to build all of LinkedIn. Implement one vertical slice well:
  * Slice: connections + posts + feed + notifications
  * Domain: Member, Connection, Post, Notification
  * Services: ConnectionService, FeedService, NotificationPublisher
  * Patterns: Strategy (feed ranking), Observer/Event (notifications)
  * Storage: repository interfaces + in-memory impl
  * That maps 1:1 to what this repo demonstrates—so you can practice explaining it.

- I’ll design an MVP LinkedIn slice: members, connections, posts, feed, notifications, and search.
- I’ll separate domain objects (Member/Profile/Post/Connection) from services (ConnectionService/NewsFeedService/SearchService).
- Notifications are event-driven using Observer so interactions on posts don’t tightly couple to delivery.
- Feed sorting is Strategy-based so we can evolve ranking.
- Then I’ll call out scaling: persistence, graph queries for connections, feed fanout strategy, and async notifications.


---

**Flow A:** Send connection request

``` java
LinkedInSystem.sendConnectionRequest(from,to)
ConnectionService.sendRequest(from,to)
create Connection(PENDING), store by requestId
create Notification(CONNECTION_REQUEST)
NotificationService.sendNotification(to, notification) → to.update(notification)
```

**Flow B:** Create post and view feed

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

**Flow C:** Like/comment triggers notification
```java
bobsPost.addLike(alice)
Post creates Notification(POST_LIKE, “…”)
notifyObservers(notification) → calls author.update(notification)
author sees it via viewNotifications() (marks read)
```