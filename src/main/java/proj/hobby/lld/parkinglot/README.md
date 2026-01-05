Requirements:

------ Design Notes

Vehicle: is abstract and not part of Entities
VehicleSize: loosely couples Vehicle & Spot
ParkingTicket: long/int bad choice for ticketId
hidden entities: VehicleSize, ParkingTicket, FeeStrategy, ParkingStrategy
hidden methods: ParkingLevel.displayAvailability()
VehicleType: Bad Enum
Notable Methods:
    public Optional<ParkingTicket> parkVehicle(Vehicle vehicle)
    public Optional<Double> releaseVehicle(String licenseNumber)
    Optional<ParkingSpot> ParkingLevel.findAvailableSpots(VehicleSize)


    

