package Strategies.Bill;

import Models.Ticket;
import Enums.VehicleType;

import java.time.LocalDateTime;
import java.util.Map;

public class VehicleBasedStrategy implements FeeStrategy{

    private static final Map<VehicleType, Double> HOURLY_RATES =   Map.of(
            VehicleType.SMALL , 10.0,
            VehicleType.COMPACT , 20.0,
            VehicleType.LARGE , 30.0
    );


    @Override
    public double calculateFee(Ticket ticket) {
        double duration = LocalDateTime.now().getSecond() - ticket.getEntryTime().getSecond();
        return duration*HOURLY_RATES.get(ticket.getVehicle().getVehicleType());
    }
}
