package proj.hobby.lld.parkinglot.entities;

import proj.hobby.lld.parkinglot.vehicle.VehicleSize;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLevel {

    private int floorNumber;
    private Map<String, ParkingSpot> spots;

    public ParkingLevel(int floorNumber) {
        this.floorNumber = floorNumber;
        this.spots = new ConcurrentHashMap<>();
    }

    public void addSpot(ParkingSpot spot) {
        this.spots.put(spot.getSpotId(), spot);
    }

    public Optional<ParkingSpot> findAvailableSpots(VehicleSize size){
        return Optional.empty();
    }


    public void displayAvailability(){
    }

}
