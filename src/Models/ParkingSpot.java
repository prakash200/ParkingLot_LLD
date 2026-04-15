package Models;
import java.util.UUID;
import Enums.VehicleType;

public class ParkingSpot {
    private UUID spotId;
    private final VehicleType spotType;
    private boolean isOccupied;
    private Vehicle parkedVehicle;
    private ParkingFloor floor;
    private final String spotName;

    public ParkingSpot(VehicleType spotType, String spotName, ParkingFloor floor) {
        this.spotId = UUID.randomUUID();
        this.spotType = spotType;
        this.isOccupied = false;
        this.spotName = spotName;
        this.floor = floor;
    }

    public ParkingFloor getFloor() {
        return floor;
    }

    public void parkVehicle(Vehicle vehicle){
        this.isOccupied = true;
        this.parkedVehicle = vehicle;

    }

    public void unParkVehicle(Vehicle vehicle){
        this.parkedVehicle = null;
        this.isOccupied = false;

    }

    public String getSpotName() {
        return spotName;
    }

    public VehicleType getSpotType() {
        return spotType;
    }

    public boolean isOccupied() {
        return !isOccupied;
    }

}
