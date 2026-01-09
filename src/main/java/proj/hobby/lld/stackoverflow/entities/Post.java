package proj.hobby.lld.stackoverflow.entities;

import proj.hobby.lld.stackoverflow.observer.PostObserver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Post extends Content {

    private final AtomicInteger votes = new AtomicInteger(0);
    private final List<Comment> comments = new CopyOnWriteArrayList<>();
    private final Map<String, VoteType> voters = new ConcurrentHashMap<>();
    private final List<PostObserver> observers = new CopyOnWriteArrayList<>();

    public Post(String id, String body, User author) {
        super(id, body, author);
    }

    public void notify(Event event) {
        this.observers.forEach(o -> o.handle(event));
    }

    public void addObserver(PostObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(PostObserver observer) {
        this.observers.remove(observer);
    }

    public synchronized void vote(User user, VoteType voteType) {
    }


}