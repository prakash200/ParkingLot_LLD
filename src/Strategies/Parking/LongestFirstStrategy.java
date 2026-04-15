package Strategies.Parking;

import Models.ParkingFloor;
import Models.ParkingSpot;
import Models.Vehicle;
import Exceptions.NoAvailableSpotException;
import java.util.Collections;
import java.util.List;

public class LongestFirstStrategy implements ParkingStrategy{


    @Override
    public ParkingSpot assignSpot(Vehicle vehicle, List<ParkingFloor> floors){
        Collections.reverse(floors);
        for (ParkingFloor floor : floors){
            for (ParkingSpot spot: floor.getParkingSpots()){
                if (spot.isOccupied() && spot.getSpotType()== vehicle.getVehicleType()){
                    return spot;
                }
            }
        }

        throw new NoAvailableSpotException("No available spots for this vehicle "+vehicle.getLicenceNumber()+" type "+ vehicle.getVehicleType());
    }
}
