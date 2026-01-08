package proj.hobby.lld.trafficController.states.light;

import proj.hobby.lld.trafficController.TrafficLight;

public interface SignalState {
    void handle(TrafficLight context);
}
