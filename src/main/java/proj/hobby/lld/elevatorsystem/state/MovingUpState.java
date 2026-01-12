package proj.hobby.lld.elevatorsystem.state;

import proj.hobby.lld.elevatorsystem.Elevator;
import proj.hobby.lld.elevatorsystem.entities.Request;
import proj.hobby.lld.elevatorsystem.enums.Direction;
import proj.hobby.lld.elevatorsystem.enums.RequestSource;

public class MovingUpState implements ElevatorState{

    @Override
    public void move(Elevator elevator) {
        if(elevator.getUpRequests().isEmpty()) {
            elevator.setCurrentState(new IdleElevatorState());
            return;
        }

        Integer nextFloor = elevator.getUpRequests().first();
        elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);

        if(nextFloor == elevator.getCurrentFloor()) {
            System.out.println("Elevator "+elevator.getId()+" stopped at floor "+ elevator.getCurrentFloor());
            elevator.getUpRequests().pollFirst();
        }

        if(elevator.getUpRequests().isEmpty()) {
            elevator.setCurrentState(new IdleElevatorState());
        }

    }

    @Override
    public void addRequest(Elevator elevator, Request request) {

        // INTERNAL
        if(RequestSource.INTERNAL.equals(request.getSource())) {
            if (request.getFloor() > elevator.getCurrentFloor()) {
                elevator.getUpRequests().add(request.getFloor());
            } else {
                elevator.getDownRequests().add(request.getFloor());
            }
            return;
        }

        // EXTERNAL
        if(request.getDirection().equals(Direction.UP) && request.getFloor() >= elevator.getCurrentFloor()) {
            elevator.getUpRequests().add(request.getFloor());
        } else if(request.getDirection().equals(Direction.DOWN)) {
            elevator.getDownRequests().add(request.getFloor());
        }

    }

    @Override
    public Direction getDirection() {
        return Direction.UP;
    }
}
