package Services;


import Models.Bill;
import Models.ParkingFloor;
import Models.Ticket;
import java.util.List;

public class DashboardService {
    private final List<ParkingFloor> floors;


    public DashboardService(List<ParkingFloor> floors){
        this.floors = floors;
    }


    public void incrementVehicleCount(Bill bill){
        ParkingFloor floor = bill.getTicket().getParkingSpot().getFloor();
        floor.incrementAvailableSpot(bill.getTicket().getParkingSpot().getSpotType());

    }

    public void decrementVehicleCount(Ticket ticket){
        ParkingFloor floor = ticket.getParkingSpot().getFloor();
        floor.decrementAvailableSpot(ticket.getParkingSpot().getSpotType());

    }

    public void displayAvailableSpots(){
        System.out.println("\nFloor wise parking Spots Availability Count\n");
        for (ParkingFloor floor: floors){
            System.out.println(floor.getName() + " : " + floor.getAvailableSpots());
        }
        System.out.println();
    }

}
