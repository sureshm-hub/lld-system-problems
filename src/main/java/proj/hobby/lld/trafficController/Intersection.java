package proj.hobby.lld.trafficController;

import proj.hobby.lld.trafficController.enums.Direction;
import proj.hobby.lld.trafficController.observer.TrafficObserver;
import proj.hobby.lld.trafficController.states.intersection.IntersectionState;
import proj.hobby.lld.trafficController.states.intersection.NorthSouthGreenState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Intersection implements  Runnable{
    private final int id;
    private final Map<Direction, TrafficLight> trafficLights;
    private IntersectionState currentState;
    private final long greenDuration;
    private final long yellowDuration;
    private volatile boolean running = true;

    public Intersection(int id, Map<Direction, TrafficLight> trafficLights, long greenDuration, long yellowDuration) {
        this.id = id;
        this.trafficLights = trafficLights;
        this.greenDuration = greenDuration;
        this.yellowDuration = yellowDuration;
        this.currentState = new NorthSouthGreenState();
    }


    public int getId() {
        return id;
    }

    public long getGreenDuration() {
        return greenDuration;
    }

    public long getYellowDuration() {
        return yellowDuration;
    }

    public TrafficLight getTrafficLight(Direction direction) {
        return trafficLights.get(direction);
    }

    public IntersectionState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(IntersectionState currentState) {
        this.currentState = currentState;
    }

    public void start() {
        new Thread(this).start();
    }

    public void stop() {
        this.running = false;
    }
    @Override
    public void run() {
        while(running) {
            try {
                currentState.handle(this);
            } catch(InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Intersection "+id+" was interrupted");
                running = false;
            }
        }
    }

    // Builder
    public static final class Builder{
        private final int id;
        private long greenDuration = 3000L;
        private long yellowDuration = 1000L;
        private final List<TrafficObserver> observers = new ArrayList<>();

        public Builder(int id) {this.id = id;}

        public Builder withDuration(long yellowDuration, long greenDuration) {
            this.yellowDuration = yellowDuration;
            this.greenDuration = greenDuration;
            return this;
        }

        public Builder addObserver(TrafficObserver observer) {
            this.observers.add(observer);
            return  this;
        }

        public Intersection build() {
            Map<Direction, TrafficLight> lights = new HashMap<>();
            for(Direction dir : Direction.values()) {
                TrafficLight light = new TrafficLight(id, dir);
                observers.forEach(light::addObserver);
                lights.put(dir, light);
            }
            return new Intersection(id, lights, greenDuration, yellowDuration);
        }

    }
}
