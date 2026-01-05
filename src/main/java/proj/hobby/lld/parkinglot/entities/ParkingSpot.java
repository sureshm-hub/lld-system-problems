package proj.hobby.lld.parkinglot.entities;

import proj.hobby.lld.parkinglot.enums.VehicleType;
import proj.hobby.lld.parkinglot.vehicle.Vehicle;
import proj.hobby.lld.parkinglot.vehicle.VehicleSize;

public class ParkingSpot {
    private final String spotId;
//    private VehicleType type;
    private final VehicleSize size;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    // Getters


    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public String getSpotId() {
        return spotId;
    }

    public ParkingSpot(String spotId, VehicleSize size) {
        this.spotId = spotId;
        this.size = size;
    }

    public boolean isAvailable() {
        return !isOccupied;
    }

    public void parkVehicle(Vehicle vehicle) {
        parkedVehicle = vehicle;
        isOccupied = true;
    }

    public Vehicle releaseSpot() {
        Vehicle parked = parkedVehicle;
        parkedVehicle = null;
        isOccupied = false;
        return parked;
    }

    public boolean canFitVehicle(Vehicle vehicle) {

        if(isOccupied) return false;

        VehicleSize sz = vehicle.getSize();
        if(sz.equals(size)) return true; //exact fit

        // smaller vehicles fit in larger spot's
        if(sz.equals(VehicleSize.SMALL)) return size.equals(VehicleSize.MEDIUM) || size.equals(VehicleSize.LARGE);
        if(sz.equals(VehicleSize.MEDIUM)) return size.equals(VehicleSize.LARGE);

        return false;
    }

}
