package proj.hobby.lld.elevatorsystem.state;

import proj.hobby.lld.elevatorsystem.Elevator;
import proj.hobby.lld.elevatorsystem.entities.Request;
import proj.hobby.lld.elevatorsystem.enums.Direction;

public interface ElevatorState {

    void move(Elevator elevator);
    void addRequest(Elevator elevator, Request request);
    Direction getDirection();


}
