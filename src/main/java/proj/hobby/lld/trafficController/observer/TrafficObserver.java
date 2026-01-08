package proj.hobby.lld.trafficController.observer;

import proj.hobby.lld.trafficController.enums.Direction;
import proj.hobby.lld.trafficController.enums.SignalColor;

public interface TrafficObserver {
    void notify(int intersectionId, Direction direction, SignalColor signalColor);
}
