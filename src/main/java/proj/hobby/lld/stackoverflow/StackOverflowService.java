package proj.hobby.lld.stackoverflow;

import proj.hobby.lld.stackoverflow.entities.*;
import proj.hobby.lld.stackoverflow.observer.PostObserver;
import proj.hobby.lld.stackoverflow.observer.ReputationManager;
import proj.hobby.lld.stackoverflow.strategy.SearchStrategy;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class StackOverflowService {
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Question> questions = new ConcurrentHashMap<>();
    private final Map<String, Answer> answers = new ConcurrentHashMap<>();
    private final PostObserver reputationManager = new ReputationManager();

    public User createUser(String name) {
        User user = new User(name);
        users.put(name, user);
        return user;
    }

    public  void postQuestion(String userId, String title, String body, Set<Tag> tags) {

    }

    public void postAnswer(String userId, String questionId, String body) {

    }

    public void voteOnPost(String userId, VoteType voteType, String postId) {
    }

    public void acceptAnswer(String questionId, String answerId) {

    }

    public List<Question> searchQuestions(List<SearchStrategy> searchStrategies) {
        return List.of();
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

}
