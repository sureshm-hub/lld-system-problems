package proj.hobby.lld.stackoverflow.entities;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class User {

    private String id;
    private String name;
    private AtomicInteger reputation;

    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.reputation = new AtomicInteger(0);
    }

    public void updateReputation(int change) {
        this.reputation.addAndGet(change);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AtomicInteger getReputation() {
        return reputation;
    }
}
