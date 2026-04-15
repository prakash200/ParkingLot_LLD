package Models;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private static ParkingLot instance;
    private final List<ParkingFloor> floors;
    private Gate entryGate;
    private Gate exitGate;


    private ParkingLot() {
        this.floors = new ArrayList<>();
    }

    public static synchronized ParkingLot getInstance(){
        if (instance==null){
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addFloor(ParkingFloor floor){
        floors.add(floor);
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }

    public Gate getEntryGate() {
        return entryGate;
    }

    public void setEntryGate(Gate entryGate) {
        this.entryGate = entryGate;
    }

    public Gate getExitGate() {
        return exitGate;
    }

    public void setExitGate(Gate exitGate) {
        this.exitGate = exitGate;
    }
}
