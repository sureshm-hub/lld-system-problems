package proj.hobby.lld.elevatorsystem;

import proj.hobby.lld.elevatorsystem.enums.Direction;

import java.util.Optional;

public class ElevatorSystemDemo {

    public static void main(String[] args) throws InterruptedException{

        // configure Elevator System
        ElevatorSystem elevatorSystem = ElevatorSystem.getInstance(4);
        elevatorSystem.start();


        // request floor call
        Optional<Elevator> elevator = elevatorSystem.requestElevator(1, Direction.UP);
        Optional<Elevator> elevator2 =elevatorSystem.requestElevator(1, Direction.UP);
        // request for floor
        elevatorSystem.selectFloor(elevator.get().getId(), 5);
        elevatorSystem.selectFloor(elevator2.get().getId(), 8);

        Thread.sleep(7000); // Elevator System working


        Optional<Elevator> elevator3 = elevatorSystem.requestElevator(4, Direction.DOWN);
        elevatorSystem.selectFloor(elevator3.get().getId(), 1);
        Thread.sleep(7000); // Elevator System working

        // shutdown
        elevatorSystem.shutdown();
    }
}
