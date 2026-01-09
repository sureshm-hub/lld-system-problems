package proj.hobby.lld.stackoverflow.strategy;

import proj.hobby.lld.stackoverflow.entities.Question;

import java.util.List;

public interface SearchStrategy {

    List<Question> filter(List<Question> questions);
}
