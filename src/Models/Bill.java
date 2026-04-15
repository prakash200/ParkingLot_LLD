package Models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Bill {

    private UUID billId;
    private LocalDateTime exitTime;
    private double amount;
    private Ticket ticket;
    private final Gate exitGate;

    public Bill(LocalDateTime exitTime, double amount, Ticket ticket, Gate exitGate) {
        this.billId = UUID.randomUUID();
        this.exitTime = exitTime;
        this.amount = amount;
        this.ticket = ticket;
        this.exitGate = exitGate;
    }

    public String toString(){
        return "\nBill\n"+billId+'\n'+ticket.getVehicle().getLicenceNumber()+"\n"+exitGate.getGateName()+'\n'+amount+"n";
    }

    public UUID getBillId() {
        return billId;
    }

    public void setBillId(UUID billId) {
        this.billId = billId;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
