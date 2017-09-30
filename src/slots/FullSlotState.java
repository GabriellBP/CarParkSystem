package slots;

public class FullSlotState implements SlotState{

    @Override
    public SlotState changeState() {
        return new EmptySlotState();
    }

    @Override
    public String toString() {
        return "Full Slot";
    }
    
    
}
