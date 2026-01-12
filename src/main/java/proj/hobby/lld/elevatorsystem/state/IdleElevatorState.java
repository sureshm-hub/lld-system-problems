package proj.hobby.lld.elevatorsystem.state;

import proj.hobby.lld.elevatorsystem.Elevator;
import proj.hobby.lld.elevatorsystem.entities.Request;
import proj.hobby.lld.elevatorsystem.enums.Direction;

public class IdleElevatorState implements ElevatorState{

    @Override
    public void move(Elevator elevator) {
        if(!elevator.getUpRequests().isEmpty()) {
            elevator.setCurrentState(new MovingUpState());
        } else if(!elevator.getDownRequests().isEmpty()){
            elevator.setCurrentState(new MovingDownState());
        }
        // stay idle
    }

    @Override
    public void addRequest(Elevator elevator, Request request) {
        if(request.getFloor() < elevator.getCurrentFloor()) {
            elevator.getDownRequests().add(request.getFloor());
        } else if(request.getFloor() > elevator.getCurrentFloor()){
            elevator.getUpRequests().add(request.getFloor());
        }
        // default case is Elevator is in the same floor as External Request - we don't have to add and process request
    }

    @Override
    public Direction getDirection() {
        return Direction.IDLE;
    }
}
