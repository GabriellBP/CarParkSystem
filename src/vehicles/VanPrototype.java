package vehicles;

import carparksystem.DateTime;

public class VanPrototype extends VehiclePrototype{
    private double cargoVolume;
   
    protected VanPrototype(VanPrototype vanPrototype){//se não pegar tem que tirar o super e colocar this
        super(vanPrototype.getPlateID(), vanPrototype.getBrandname(), vanPrototype.getEntryTimeObject());
        this.cargoVolume = vanPrototype.getCargoVolume();
    }
    
    public VanPrototype(DateTime entryTime){
        super("", "", entryTime);
        this.cargoVolume = 0.00;
    }
    
    public VanPrototype(String id, String brand, DateTime entryTime, double volume){
        super(id, brand, entryTime);
        this.cargoVolume = volume;
    }
    
    

    @Override
    public VehiclePrototype clonar() {
        return new VanPrototype(this);
    }
    
    //setter for vehicle cargoVolume
    public void setCargoVolume(double volume){ 
        cargoVolume = volume;
    }
    
    //getter for vehicle cargoVolume
    public double getCargoVolume(){ 
        return cargoVolume;
    }

    

}