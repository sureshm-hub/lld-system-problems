package proj.hobby.lld.stackoverflow.entities;

import java.util.UUID;

public class Answer extends Post{

    private boolean isAccepted;

    public Answer(String id, String body, User author) {
        super(UUID.randomUUID().toString(), body, author);
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean isAccepted() {
        return isAccepted;
    }
}
