package proj.hobby.lld.parkinglot;

import proj.hobby.lld.parkinglot.entities.ParkingLevel;
import proj.hobby.lld.parkinglot.entities.ParkingTicket;
import proj.hobby.lld.parkinglot.strategy.fee.FeeStrategy;
import proj.hobby.lld.parkinglot.strategy.fee.TimeBasedStrategy;
import proj.hobby.lld.parkinglot.strategy.parking.NearestParkingStrategy;
import proj.hobby.lld.parkinglot.strategy.parking.ParkingStrategy;
import proj.hobby.lld.parkinglot.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {

    private static ParkingLot instance;
    private FeeStrategy feeStrategy;
    private ParkingStrategy parkingStrategy;
    private Map<String, ParkingTicket> activeTickets;
    private List<ParkingLevel> parkingLevels = new ArrayList<>();

    public ParkingLot(){
        this.feeStrategy = new TimeBasedStrategy();
        this.parkingStrategy = new NearestParkingStrategy();
        this.activeTickets = new ConcurrentHashMap<>();
    }

    public static ParkingLot getInstance() {
        if(instance == null) {
            synchronized(ParkingLot.class) {
                if(instance == null) {
                    instance = new ParkingLot();
                }
            }
        }
        return instance;
    }

    // Getters, Setters
    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public void addParkingLevel(ParkingLevel level){
        this.parkingLevels.add(level);
    }

    public Optional<ParkingTicket> parkVehicle(Vehicle vehicle) {
        return Optional.empty();
    }

    public Optional<Double> releaseVehicle(String licenseNumber) {
        return Optional.of(10.00d);
    }

}
