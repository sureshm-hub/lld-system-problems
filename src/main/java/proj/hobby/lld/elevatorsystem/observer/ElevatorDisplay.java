package proj.hobby.lld.elevatorsystem.observer;

import proj.hobby.lld.elevatorsystem.Elevator;

public class ElevatorDisplay implements ElevatorObserver{
    @Override
    public void handle(Elevator elevator) {
        System.out.printf("Elevator: %s CurrentFloor: %d Direction: %s%n", elevator.getId()
                , elevator.getCurrentFloor()
                , elevator.getCurrentDirection());
    }
}
