package proj.hobby.lld.trafficController.states.light;

import proj.hobby.lld.trafficController.TrafficLight;
import proj.hobby.lld.trafficController.enums.SignalColor;

public class RedState implements SignalState{

    @Override
    public void handle(TrafficLight context) {
        context.setSignalColor(SignalColor.RED);
        // requires Intersection Controller to move to Green State
        context.setNextState(new RedState());
    }

}
