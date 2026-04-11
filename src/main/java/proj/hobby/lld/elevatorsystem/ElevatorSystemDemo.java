package proj.hobby.lld.elevatorsystem;

import proj.hobby.lld.elevatorsystem.enums.Direction;

import java.util.Optional;

public class ElevatorSystemDemo {

    public static void main(String[] args) throws InterruptedException{

        // configure Elevator System
        ElevatorController elevatorController = ElevatorController.getInstance(4);
        elevatorController.start();


        // request floor call
        Optional<Elevator> elevator = elevatorController.requestElevator(1, Direction.UP);
        Optional<Elevator> elevator2 =elevatorController.requestElevator(1, Direction.UP);
        // request for floor
        elevatorController.selectFloor(elevator.get().getId(), 5);
        elevatorController.selectFloor(elevator2.get().getId(), 800);

        Thread.sleep(7000); // Elevator System working


        Optional<Elevator> elevator3 = elevatorController.requestElevator(4, Direction.DOWN);
        elevatorController.selectFloor(elevator3.get().getId(), 1);
        Thread.sleep(7000); // Elevator System working

        // shutdown
        elevatorController.shutdown();
    }
}
