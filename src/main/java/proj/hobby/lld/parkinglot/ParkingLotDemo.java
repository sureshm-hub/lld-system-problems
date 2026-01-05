package proj.hobby.lld.parkinglot;

import proj.hobby.lld.parkinglot.entities.ParkingLevel;
import proj.hobby.lld.parkinglot.entities.ParkingSpot;
import proj.hobby.lld.parkinglot.entities.ParkingTicket;
import proj.hobby.lld.parkinglot.strategy.fee.TimeBasedStrategy;
import proj.hobby.lld.parkinglot.strategy.parking.NearestParkingStrategy;
import proj.hobby.lld.parkinglot.vehicle.*;

import java.util.Optional;

public class ParkingLotDemo {

    public static void main(String[] args) {
        // 1. Initialize the Parking Lot with Floors & Spots
        ParkingLot parkingLot = ParkingLot.getInstance();
        ParkingLevel firstLevel = new ParkingLevel(1);
        firstLevel.addSpot(new ParkingSpot("F1-S1", VehicleSize.SMALL));
        firstLevel.addSpot(new ParkingSpot("F1-M1", VehicleSize.MEDIUM));
        firstLevel.addSpot(new ParkingSpot("F1-L1", VehicleSize.LARGE));

        ParkingLevel secondLevel = new ParkingLevel(2);
        secondLevel.addSpot(new ParkingSpot("F2-S1", VehicleSize.SMALL));
        secondLevel.addSpot(new ParkingSpot("F2-S2", VehicleSize.SMALL));

        parkingLot.addParkingLevel(firstLevel);
        parkingLot.addParkingLevel(secondLevel);

        parkingLot.setFeeStrategy(new TimeBasedStrategy());
        parkingLot.setParkingStrategy(new NearestParkingStrategy());

        // 2. Simulate Vehicle entries
        System.out.println("\n --- Vehicle Entries --- \n");

        firstLevel.displayAvailability();
        secondLevel.displayAvailability();

        Vehicle bike = new Bike("B1L1");
        Vehicle car = new Car("C1L1");
        Vehicle truck = new Truck("T1L1");

        Optional<ParkingTicket> bikeTicket = parkingLot.parkVehicle(bike);
        Optional<ParkingTicket> carTicket = parkingLot.parkVehicle(car);
        Optional<ParkingTicket> truckTicket = parkingLot.parkVehicle(truck);

        // 3. Simulate more scenarios
        // park in floor 2
        Vehicle bike2 = new Bike("B2L2");
        Optional<ParkingTicket> bikeTicket2 = parkingLot.parkVehicle(bike2);

        // vehicle can't be parked
        Vehicle car2 = new Car("C2L2");
        Optional<ParkingTicket> carTicket2 = parkingLot.parkVehicle(car2);

        System.out.println("\n --- After Parking --- \n");
        firstLevel.displayAvailability();
        secondLevel.displayAvailability();

        // 4. Simulate Vehicle Exits & fee calculations
        System.out.println("\n --- Vehicle Exits --- \n");
        if(truckTicket.isPresent()) {
            Optional<Double> fee = parkingLot.releaseVehicle(truck.getLicenseNumber());
            fee.ifPresent(d -> System.out.printf("Fee for Parking Vehicle: "+truck.getLicenseNumber()+ " is: $%.2f",d));
        }

        if(bikeTicket2.isPresent()) {
            Optional<Double> fee = parkingLot.releaseVehicle(bike2.getLicenseNumber());
            fee.ifPresent( d -> System.out.printf("Fee for Parking Vehicle: "+bike2.getLicenseNumber()+" is : $.2f", d));
        }

        System.out.println("\n --- Availability after Exits --- \n");
        firstLevel.displayAvailability();
        secondLevel.displayAvailability();
    }
}
