package Models;

import java.util.UUID;
import Enums.VehicleType;
public class Vehicle {
    private UUID vehicleId;
    private String licenceNumber;
    private VehicleType vehicleType;


    public Vehicle(String licenceNumber, VehicleType vehicleType) {
        this.vehicleId = UUID.randomUUID();
        this.licenceNumber = licenceNumber;
        this.vehicleType = vehicleType;
    }

    public UUID getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(UUID vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
