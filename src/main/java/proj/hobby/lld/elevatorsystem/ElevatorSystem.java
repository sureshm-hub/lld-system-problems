package proj.hobby.lld.elevatorsystem;

import proj.hobby.lld.elevatorsystem.entities.Request;
import proj.hobby.lld.elevatorsystem.enums.Direction;
import proj.hobby.lld.elevatorsystem.enums.RequestSource;
import proj.hobby.lld.elevatorsystem.observer.ElevatorDisplay;
import proj.hobby.lld.elevatorsystem.observer.ElevatorObserver;
import proj.hobby.lld.elevatorsystem.strategy.ElevatorSchedulingStrategy;
import proj.hobby.lld.elevatorsystem.strategy.NearestElevatorScheduler;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ElevatorSystem {

    private static ElevatorSystem instance;
    private Map<Integer, Elevator> elevators;
    private ElevatorSchedulingStrategy schedulingStrategy;
    private ExecutorService executorService;

    private ElevatorSystem(int numElevators) {
        this.schedulingStrategy = new NearestElevatorScheduler();
        this.executorService = Executors.newFixedThreadPool(numElevators);

        List<Elevator> elevatorList = new ArrayList<>();
        ElevatorObserver observer = new ElevatorDisplay();

        for(int i = 0; i < numElevators; i++) {
            Elevator elevator = new Elevator(i);
            elevator.addObserver(observer);
            elevatorList.add(elevator);
        }

        this.elevators = elevatorList.stream().collect(Collectors.toMap(Elevator::getId, e -> e));
    }

    public static synchronized ElevatorSystem getInstance(int numElevators) {
        if(instance == null) {
            instance = new ElevatorSystem(numElevators);
        }
        return instance;
    }

    public void start() {
        for(Elevator e : elevators.values()) {
            executorService.submit(e);
        }
    }

    // --- Facade Methods ---

    // External Request (Floor Call)
    public Optional<Elevator> requestElevator(int floor, Direction direction) {
        System.out.printf(">> EXTERNAL Request: from floor %d direction %s%n", floor, direction);
        Request request = new Request(floor, direction, RequestSource.EXTERNAL);

        Optional<Elevator> elevator = this.schedulingStrategy.schedule(new ArrayList<>(elevators.values()), request);
        if(elevator.isPresent()) {
            elevator.get().addUserRequest(request);
        } else {
            System.out.println("System busy please wait");
        }
        return elevator;
    }

    public void selectFloor(int elevatorId, int destinationFloor) {
        System.out.printf(">> INTERNAL Request: user in elevator %d selected floor %d%n", elevatorId, destinationFloor);
        Request request = new Request(destinationFloor, Direction.IDLE, RequestSource.INTERNAL);

        Elevator elevator = elevators.get(elevatorId);
        if(elevator != null) {
            elevator.addUserRequest(request);
        } else {
            System.out.println("Invalid elevator id "+elevatorId);
        }
    }

    public void shutdown() {
        System.out.println("Shutting down Elevator System ... ");
        for(Elevator elevator : elevators.values()) {
            elevator.stop();
        }
        executorService.shutdown();
    }

}
