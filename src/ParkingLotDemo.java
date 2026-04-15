import Models.*;
import Services.DashboardService;
import Services.ParkingService;
import Strategies.Bill.FeeStrategy;
import Strategies.Bill.FlatRateStrategy;
import Strategies.Bill.VehicleBasedStrategy;
import Strategies.Parking.*;
import Strategies.Parking.ParkingStrategy;
import Enums.VehicleType;
import Enums.GateType;


public class ParkingLotDemo {
    public static void main(String[] args) throws InterruptedException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("\nParking LOT\n");

        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.setEntryGate(new Gate(GateType.ENTRY, "Entry Gate"));
        parkingLot.setExitGate(new Gate(GateType.EXIT, "Exit Gate"));

        ParkingFloor floor1 = new ParkingFloor("Floor 1");
        floor1.addParkingSpot(new ParkingSpot(VehicleType.SMALL, "Floor 1 small vehicle spot", floor1));
        floor1.addParkingSpot(new ParkingSpot(VehicleType.COMPACT, "Floor 1 compact vehicle spot", floor1));
        floor1.addParkingSpot(new ParkingSpot(VehicleType.LARGE, "Floor 1 large vehicle spot", floor1));

        ParkingFloor floor2 = new ParkingFloor("Floor 2");
        floor2.addParkingSpot(new ParkingSpot(VehicleType.SMALL, "Floor 2 small vehicle spot", floor2));
        floor2.addParkingSpot(new ParkingSpot(VehicleType.COMPACT, "Floor 2 compact vehicle spot", floor2));
        floor2.addParkingSpot(new ParkingSpot(VehicleType.LARGE, "Floor 2 compact Vehicle spot", floor2));

        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);

        DashboardService dashboardService = new DashboardService(parkingLot.getFloors());
        dashboardService.displayAvailableSpots();

        Vehicle vehicle1 = new Vehicle("UP 123456", VehicleType.COMPACT);

        ParkingStrategy parkingStrategy = new NearestFirstStrategy();
        FeeStrategy feeStrategy = new FlatRateStrategy();

        ParkingService parkingService = new ParkingService(parkingLot, parkingStrategy, feeStrategy);
        Ticket ticket1 = parkingService.park(vehicle1);
        if (ticket1!=null) {
            dashboardService.decrementVehicleCount(ticket1);
            System.out.println(ticket1);
        }

        dashboardService.displayAvailableSpots();

        System.out.println("\n-----------------------------------------------------\n");

        Vehicle vehicle2 = new Vehicle("TN 123456", VehicleType.COMPACT);
        Ticket ticket2 = parkingService.park(vehicle2);
        if (ticket2!=null){
            dashboardService.decrementVehicleCount(ticket2);
            System.out.println(ticket2);
        }
        dashboardService.displayAvailableSpots();
        System.out.println("\n-----------------------------------------------------\n");


        Vehicle vehicle3 = new Vehicle("MH 123456", VehicleType.COMPACT);
        Ticket ticket3 = parkingService.park(vehicle3);
        dashboardService.displayAvailableSpots();
        System.out.println("\n-----------------------------------------------------\n");

        Thread.sleep(2000);
        Bill bill1 = parkingService.unpark(ticket1);
        dashboardService.incrementVehicleCount(bill1);
        System.out.println(bill1);

        dashboardService.displayAvailableSpots();
        System.out.println("\n-----------------------------------------------------\n");

        ticket3 = parkingService.park(vehicle3);
        if (ticket3!=null){
            dashboardService.decrementVehicleCount(ticket3);
            System.out.println(ticket3);
        }

        dashboardService.displayAvailableSpots();
        System.out.println("\n-----------------------------------------------------\n");

        parkingService.setFeeStrategy(new VehicleBasedStrategy());
        Bill bill2 = parkingService.unpark(ticket2);
        dashboardService.incrementVehicleCount(bill2);
        System.out.println(bill2);

        dashboardService.displayAvailableSpots();










    }
}