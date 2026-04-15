package Strategies.Bill;

import Models.Ticket;

public interface FeeStrategy {

    double calculateFee(Ticket ticket);
}
