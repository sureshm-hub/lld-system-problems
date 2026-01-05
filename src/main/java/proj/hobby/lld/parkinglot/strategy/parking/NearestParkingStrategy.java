package proj.hobby.lld.parkinglot.strategy.parking;

import proj.hobby.lld.parkinglot.entities.ParkingLevel;
import proj.hobby.lld.parkinglot.entities.ParkingSpot;
import proj.hobby.lld.parkinglot.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public class NearestParkingStrategy implements  ParkingStrategy{

    @Override
    public Optional<ParkingSpot> findParkingSpot(List<ParkingLevel> parkingLevels, Vehicle vehicle) {
        return Optional.empty();
    }
}
