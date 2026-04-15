package Strategies.Parking;

import Models.ParkingFloor;
import Models.ParkingSpot;
import Models.Vehicle;

import java.util.List;

public interface ParkingStrategy {

    ParkingSpot assignSpot(Vehicle vehicle, List<ParkingFloor> floors) ;
}
