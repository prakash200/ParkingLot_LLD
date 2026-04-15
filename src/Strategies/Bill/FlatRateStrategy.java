package Strategies.Bill;

import Models.Ticket;

import java.time.LocalDateTime;

public class FlatRateStrategy implements FeeStrategy{

    private static final Double RATE_PER_HOUR = 10.0;

    @Override
    public double calculateFee(Ticket ticket) {

        double duration = LocalDateTime.now().getSecond() - ticket.getEntryTime().getSecond();

        return duration* RATE_PER_HOUR;
    }
}
