package Services;

import Models.*;
import Strategies.Bill.FeeStrategy;
import Strategies.Parking.ParkingStrategy;
import Exceptions.NoAvailableSpotException;
import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingService {

    private FeeStrategy feeStrategy;
    private ParkingStrategy parkingStrategy;
    private final ParkingLot parkingLot;


    public ParkingService(ParkingLot parkinglot, ParkingStrategy parkingStrategy, FeeStrategy feeStrategy) {
        this.parkingLot = parkinglot;
        this.parkingStrategy = parkingStrategy;
        this.feeStrategy = feeStrategy;

    }


    public synchronized Ticket park(Vehicle vehicle){

        try {
            ParkingSpot availableSpot = parkingStrategy.assignSpot(vehicle, parkingLot.getFloors());
            System.out.println(availableSpot.getSpotName() + " is" + " assigned to " + vehicle.getLicenceNumber());
            Ticket ticket = new Ticket(UUID.randomUUID(), parkingLot.getEntryGate(), LocalDateTime.now(), vehicle, availableSpot);
            availableSpot.parkVehicle(vehicle);
            return ticket;
        }catch (NoAvailableSpotException error){
            System.out.println(error.getMessage());
            return null;
        }
    }

    public synchronized Bill unpark(Ticket ticket){
        double fee = feeStrategy.calculateFee(ticket);
        ticket.getParkingSpot().unParkVehicle(ticket.getVehicle());
        return new Bill(LocalDateTime.now(), fee, ticket, parkingLot.getExitGate());
    }



    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

}
