package proj.hobby.lld.trafficController;

import proj.hobby.lld.trafficController.enums.Direction;
import proj.hobby.lld.trafficController.enums.SignalColor;
import proj.hobby.lld.trafficController.observer.TrafficObserver;
import proj.hobby.lld.trafficController.states.light.GreenState;
import proj.hobby.lld.trafficController.states.light.RedState;
import proj.hobby.lld.trafficController.states.light.SignalState;

import java.util.ArrayList;
import java.util.List;

public class TrafficLight {
    private SignalColor signalColor;
    private SignalState currentState;
    private SignalState nextState;
    private final Direction direction;
    private final List<TrafficObserver> observers = new ArrayList<>();
    private final int intersectionId;

    public TrafficLight(int intersectionId, Direction direction) {
        this.intersectionId = intersectionId;
        this.direction = direction;
        this.currentState = new RedState();
        this.currentState.handle(this);
    }

    // Start G -> Y -> R cycle
    public void startGreen() {
        this.currentState = new GreenState();
        this.currentState.handle(this);
    }

    // handle transition from G -> Y, Y -> R
    public void transition() {
        this.currentState = nextState;
        this.currentState.handle(this);
    }

    public void setSignalColor(SignalColor signalColor) {
        if(this.signalColor != signalColor) {
            this.signalColor = signalColor;
            notifyObservers(signalColor);
        }
    }

    public void setCurrentState(SignalState currentState) {
        this.currentState = currentState;
    }

    public void setNextState(SignalState nextState) {
        this.nextState = nextState;
    }

    private void notifyObservers(SignalColor signalColor) {
        observers.forEach(o -> o.notify(intersectionId, direction, signalColor));
    }

    public void addObserver(TrafficObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TrafficObserver observer) {
    }
}
