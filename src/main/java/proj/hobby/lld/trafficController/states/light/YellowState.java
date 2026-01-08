package proj.hobby.lld.trafficController.states.light;

import proj.hobby.lld.trafficController.TrafficLight;
import proj.hobby.lld.trafficController.enums.SignalColor;

public class YellowState implements SignalState {

    @Override
    public void handle(TrafficLight context) {
        context.setSignalColor(SignalColor.YELLOW);
        context.setNextState(new RedState());
    }
}
