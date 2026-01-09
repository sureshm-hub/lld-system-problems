package proj.hobby.lld.stackoverflow.observer;

import proj.hobby.lld.stackoverflow.entities.Event;

public interface PostObserver {

    void handle(Event postEvent);
}
