package proj.hobby.lld.elevatorsystem;

import proj.hobby.lld.elevatorsystem.entities.Request;
import proj.hobby.lld.elevatorsystem.enums.Direction;
import proj.hobby.lld.elevatorsystem.observer.ElevatorObserver;
import proj.hobby.lld.elevatorsystem.state.ElevatorState;
import proj.hobby.lld.elevatorsystem.state.IdleElevatorState;

import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

public class Elevator implements  Runnable{
    private int id;
    private int currentFloor;
    private ElevatorState currentState;
    private boolean isRunning = true;

    private final TreeSet<Integer> upRequests;
    private final TreeSet<Integer> downRequests;

    private List<ElevatorObserver> observers = new CopyOnWriteArrayList<>();

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 1;// Default on 1st floor`
        this.upRequests = new TreeSet<>();
        this.downRequests = new TreeSet<>((a, b) -> b - a);
        this.currentState = new IdleElevatorState();
    }

    // state handling methods
    public synchronized void addUserRequest(Request request) {
        System.out.printf("Elevator %d processing %s%n",id,request);
        this.currentState.addRequest(this, request);
    }

    public void move(){
        this.currentState.move(this);
    }

    public Direction getCurrentDirection() {
        return this.currentState.getDirection();
    }

    public ElevatorState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ElevatorState currentState) {
        this.currentState = currentState;
        notifyObservers();// notify observers on direction change
    }

    // Observer Methods
    public void addObserver(ElevatorObserver observer) {
        this.observers.add(observer);
        observer.handle(this); // send initial state
    }

    public void notifyObservers(){
        this.observers.forEach(o -> o.handle(this));
    }

    @Override
    public void run() {
        while(isRunning) {
            move();
//            System.out.println(this);
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                isRunning =false;
            }
        }

    }

    public  void stop() {this.isRunning = false;}

    // Getters & Setters
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
        notifyObservers(); // notify observers on floor change
    }
    public int getId() {return id;}
    public int getCurrentFloor() {return currentFloor;}
    public TreeSet<Integer> getUpRequests() {return upRequests;}
    public TreeSet<Integer> getDownRequests() {return downRequests;}
    public boolean isRunning() {return isRunning;}

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Elevator ").append(getId())
                .append(", isRunning ").append(isRunning())
                .append(", currentFloor ").append(getCurrentFloor())
                .append(", currentDirection ").append(getCurrentDirection())
                .append(", currentState ").append(getCurrentState().getDirection())
                .append(", upRequests ").append(getUpRequests().size())
                .append(", downRequests ").append(getDownRequests().size())
                .append(", observers ").append(this.observers.size());
        return sb.toString();
    }
}
