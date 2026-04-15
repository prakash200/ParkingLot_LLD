package Models;

import Enums.VehicleType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingFloor {


    private final String name;
    private final List<ParkingSpot> parkingSpots;
    private final Map<VehicleType, AtomicInteger> availableSpots;

    public ParkingFloor(String name) {

        this.name = name;
        this.parkingSpots = new ArrayList<>();
        this.availableSpots = new HashMap<>();
    }

    public Map<VehicleType, AtomicInteger> getAvailableSpots() {
        return availableSpots;
    }

    public String getName() {
        return name;
    }

    public void incrementAvailableSpot(VehicleType spotType){
        availableSpots.get(spotType).incrementAndGet();
    }

    public void decrementAvailableSpot(VehicleType spotType){
        availableSpots.get(spotType).decrementAndGet();
    }


    public void addParkingSpot(ParkingSpot spot){

        parkingSpots.add(spot);
        availableSpots
                .computeIfAbsent(spot.getSpotType(), k -> new AtomicInteger(0))
                .incrementAndGet();

    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

}
