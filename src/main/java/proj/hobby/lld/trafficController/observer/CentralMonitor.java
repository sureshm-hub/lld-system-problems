package proj.hobby.lld.trafficController.observer;

import proj.hobby.lld.trafficController.enums.Direction;
import proj.hobby.lld.trafficController.enums.SignalColor;

public class CentralMonitor implements  TrafficObserver {

    @Override
    public void notify(int intersectionId, Direction direction, SignalColor signalColor) {
        System.out.printf("Central Monitor Notification at intersection %s direction %s new signal color: %s\n",intersectionId, direction, signalColor);
    }
}
