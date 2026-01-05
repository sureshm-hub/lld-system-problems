package proj.hobby.lld.parkinglot.strategy.fee;

import proj.hobby.lld.parkinglot.entities.ParkingTicket;

public class TimeBasedStrategy implements  FeeStrategy {
    @Override
    public double calculateFee(ParkingTicket ticket) {
        return 0;
    }
}
