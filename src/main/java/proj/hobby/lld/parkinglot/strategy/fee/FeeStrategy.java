package proj.hobby.lld.parkinglot.strategy.fee;

import proj.hobby.lld.parkinglot.entities.ParkingTicket;

public interface FeeStrategy {

    double calculateFee(ParkingTicket ticket);
}
