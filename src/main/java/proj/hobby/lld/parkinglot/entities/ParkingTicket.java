package proj.hobby.lld.parkinglot.entities;

import proj.hobby.lld.parkinglot.vehicle.Vehicle;

import java.util.Date;
import java.util.UUID;

public class ParkingTicket {

    // long/int or bad choice for interview's as you have to talk about sequence's. Use String and Random
    private String ticketId;
    private ParkingSpot spot;
    private Vehicle vehicle;
    private long entryTimestamp;
    private long exitTimestamp;

    public ParkingTicket(ParkingSpot spot, Vehicle vehicle) {
        this.ticketId = UUID.randomUUID().toString();
        this.spot = spot;
        this.vehicle = vehicle;
        this.entryTimestamp = new Date().getTime();
    }

    // Getters


    public void setExitTimestamp() {
        this.exitTimestamp = new Date().getTime();
    }

}
