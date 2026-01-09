package proj.hobby.lld.stackoverflow.strategy;

import proj.hobby.lld.stackoverflow.entities.Question;

import java.security.Key;
import java.util.List;

public class KeywordSearchStrategy implements  SearchStrategy{

    private String keyword;

    public KeywordSearchStrategy(String keyword){
        this.keyword = keyword;
    }

    @Override
    public List<Question> filter(List<Question> questions) {
        return questions.stream()
                .filter(q ->
                        q.getTitle().toLowerCase().contains(keyword) ||
                        q.getBody().toLowerCase().contains(keyword))
                .toList();
    }
}
