package slots;

import vehicles.VehiclePrototype;

public class Slot {
    private final int idSlot;
    private VehiclePrototype vehicle;
    private SlotState slotState;
    
    public Slot(int idSlot){
        this.idSlot = idSlot;
        this.slotState = new EmptySlotState();
        this.vehicle = null;
    }

    public int getIdSlot() {
        return idSlot;
    }

    public VehiclePrototype getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehiclePrototype Vehicle) {
        this.vehicle = Vehicle;
    }

    public SlotState getSlotState() {
        return slotState;
    }

    public void setSlotState(SlotState slotState) {
        this.slotState = slotState;
    }
    
    
}
