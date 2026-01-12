package proj.hobby.lld.elevatorsystem.strategy;

import proj.hobby.lld.elevatorsystem.Elevator;
import proj.hobby.lld.elevatorsystem.entities.Request;
import proj.hobby.lld.elevatorsystem.enums.Direction;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class NearestElevatorScheduler implements ElevatorSchedulingStrategy {

    @Override
    public Optional<Elevator> schedule(List<Elevator> elevators, Request userRequest) {

        Predicate<Elevator> sameDirection = elevator -> userRequest.getDirection().equals(elevator.getCurrentDirection());
        Predicate<Elevator> nearestGoingUp = elevator -> (Direction.UP.equals(userRequest.getDirection()) && userRequest.getFloor() > elevator.getCurrentFloor());
        Predicate<Elevator> nearestGoingDown = elevator -> (Direction.DOWN.equals(userRequest.getDirection()) && userRequest.getFloor() < elevator.getCurrentFloor());
        Optional<Elevator> nearest = elevators.stream()
                .filter(sameDirection )
                .filter( nearestGoingDown.or(nearestGoingUp))
                .findFirst();
        if(nearest.isEmpty()) {
            Stream<Elevator> idleElevators = elevators.stream()
                    .filter(elevator -> Direction.IDLE.equals(elevator.getCurrentDirection()));
            nearest = idleElevators.findFirst();
        }
        return nearest;
    }

}
