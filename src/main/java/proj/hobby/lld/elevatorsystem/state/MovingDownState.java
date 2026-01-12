package proj.hobby.lld.elevatorsystem.state;

import proj.hobby.lld.elevatorsystem.Elevator;
import proj.hobby.lld.elevatorsystem.entities.Request;
import proj.hobby.lld.elevatorsystem.enums.Direction;
import proj.hobby.lld.elevatorsystem.enums.RequestSource;

public class MovingDownState implements ElevatorState {
    @Override
    public void move(Elevator elevator) {
        if(elevator.getDownRequests().isEmpty()){
            elevator.setCurrentState(new IdleElevatorState());
            return;
        }

        Integer nextFloor = elevator.getDownRequests().first();
        elevator.setCurrentFloor(elevator.getCurrentFloor() -  1);

        if(elevator.getCurrentFloor() == nextFloor) {
            System.out.println(" Elevator "+elevator.getId()+" stopped at floor "+elevator.getCurrentFloor());
            elevator.getDownRequests().pollFirst();
        }

        if(elevator.getDownRequests().isEmpty()) {
            elevator.setCurrentState(new IdleElevatorState());
        }

    }

    @Override
    public void addRequest(Elevator elevator, Request request) {
        // Internal requests don't need floor call - gets added based on selected floor
        if(request.getSource().equals(RequestSource.INTERNAL)) {
            if(request.getFloor() > elevator.getCurrentFloor()) {
                elevator.getUpRequests().add(request.getFloor());
            } else {
                elevator.getDownRequests().add(request.getFloor());
            }
            return;
        }

        // External requests need floor call - check Elevator Direction
        if (request.getDirection().equals(Direction.DOWN) && request.getFloor() <= elevator.getCurrentFloor()) {
            elevator.getDownRequests().add(request.getFloor());
        } else if (request.getDirection().equals(Direction.UP)) {
            elevator.getUpRequests().add(request.getFloor());
        }

    }

    @Override
    public Direction getDirection() {
        return Direction.DOWN;
    }
}
