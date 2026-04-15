package Models;

import java.util.UUID;
import Enums.GateType;

public class Gate {

    private UUID gateId;
    private GateType gateType;
    private String gateName;


    public Gate(GateType gateType, String gateName) {
        this.gateId = UUID.randomUUID();
        this.gateName = gateName;
        this.gateType = gateType;
    }

    public String getGateName() {
        return gateName;
    }

    public void setGateName(String gateName) {
        this.gateName = gateName;
    }

    public UUID getGateId() {
        return gateId;
    }

    public void setGateId(UUID gateId) {
        this.gateId = gateId;
    }


}
