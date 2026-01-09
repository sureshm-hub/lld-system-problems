package proj.hobby.lld.stackoverflow.entities;

import java.time.LocalDateTime;

public abstract class Content {

    private String id;
    private String body;
    private User author;
    private final LocalDateTime creationTime;

    public Content(String id, String body, User author) {
        this.id = id;
        this.body = body;
        this.author = author;
        this.creationTime = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public User getAuthor() {
        return author;
    }
}
