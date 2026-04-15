package Models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {

    private UUID ticketId;
    private Gate gate;
    private LocalDateTime entryTime;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;

    public Ticket(UUID ticketId, Gate gate, LocalDateTime entryTime, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.ticketId = ticketId;
        this.gate = gate;
        this.entryTime = entryTime;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public String toString(){
        return "\nTicket\n"+this.ticketId+"\n"+this.gate.getGateName()+ "\n"+ this.entryTime + "\n" + this.vehicle.getLicenceNumber()+ "\n"+this.parkingSpot.getSpotName()+"\n";
    }
}
