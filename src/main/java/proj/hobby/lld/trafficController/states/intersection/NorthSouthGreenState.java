package proj.hobby.lld.trafficController.states.intersection;

import proj.hobby.lld.trafficController.Intersection;
import proj.hobby.lld.trafficController.enums.Direction;
import proj.hobby.lld.trafficController.enums.SignalColor;

public class NorthSouthGreenState implements  IntersectionState {

    @Override
    public void handle(Intersection context) throws  InterruptedException {
        System.out.printf("\n--- Intersection: %d cycle start -> North-South Green ---\n",context.getId());

        context.getTrafficLight(Direction.EAST).setSignalColor(SignalColor.RED);
        context.getTrafficLight(Direction.WEST).setSignalColor(SignalColor.RED);
        context.getTrafficLight(Direction.NORTH).startGreen();
        context.getTrafficLight(Direction.SOUTH).startGreen();

        // wait for Green Light Duration
        Thread.sleep(context.getGreenDuration());

        // Transition North & South to Yellow
        context.getTrafficLight(Direction.NORTH).transition();
        context.getTrafficLight(Direction.SOUTH).transition();

        Thread.sleep(context.getYellowDuration());

        // transition Y -> R
        context.getTrafficLight(Direction.NORTH).transition();
        context.getTrafficLight(Direction.SOUTH).transition();

        context.setCurrentState(new EastWestGreenState());
    }


}
