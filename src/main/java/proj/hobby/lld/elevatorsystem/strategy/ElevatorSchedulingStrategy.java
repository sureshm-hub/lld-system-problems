package proj.hobby.lld.elevatorsystem.strategy;

import proj.hobby.lld.elevatorsystem.Elevator;
import proj.hobby.lld.elevatorsystem.entities.Request;

import java.util.List;
import java.util.Optional;

public interface ElevatorSchedulingStrategy {

    Optional<Elevator> schedule(List<Elevator> elevators, Request userRequest);
}
