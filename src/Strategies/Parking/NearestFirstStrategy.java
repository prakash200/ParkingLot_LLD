package Strategies.Parking;

import Exceptions.NoAvailableSpotException;
import Models.ParkingFloor;
import Models.ParkingSpot;
import Models.Vehicle;

import java.util.List;

public class NearestFirstStrategy implements ParkingStrategy{

    @Override
    public ParkingSpot assignSpot(Vehicle vehicle, List<ParkingFloor> floors) {
        for (ParkingFloor floor : floors){
            for (ParkingSpot spot: floor.getParkingSpots()){
                if (spot.getSpotType() == vehicle.getVehicleType() && spot.isOccupied()){
                    System.out.println(spot.getSpotName()+" spot is"+" available");
                    return spot;
                }
            }
        }

        throw new NoAvailableSpotException("No Spot Available for this vehicle "+ vehicle.getLicenceNumber());
    }
}
