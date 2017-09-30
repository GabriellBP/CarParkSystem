package slots;

public class EmptySlotState implements SlotState{

    @Override
    public SlotState changeState() {
        return new FullSlotState();
    }

    @Override
    public String toString() {
        return "Empty Slot";
    }
    
    
}
