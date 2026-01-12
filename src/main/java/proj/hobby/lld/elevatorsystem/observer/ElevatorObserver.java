package proj.hobby.lld.elevatorsystem.observer;

import proj.hobby.lld.elevatorsystem.Elevator;

public interface ElevatorObserver {

    void handle(Elevator elevator);
}
