package proj.hobby.lld.stackoverflow.entities;

import java.util.*;

public class Question extends Post{

    private String title;
    private Set<Tag> tags = new HashSet<>();
    private List<Answer> answers = new ArrayList<>();
    private Answer acceptedAnswer;

    public Question(String id, String body, User author, String title, Set<Tag> tags) {
        super(UUID.randomUUID().toString(), body, author);
        this.title = title;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Answer getAcceptedAnswer() {
        return acceptedAnswer;
    }

    public void acceptAnswer(Answer acceptedAnswer) {
        // Business Rules
        // userId validation
        this.acceptedAnswer = acceptedAnswer;
        // notify event
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }
}
