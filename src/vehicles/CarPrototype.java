
package vehicles;

import carparksystem.DateTime;

public class CarPrototype extends VehiclePrototype{
    private int noOfDoors;
    private String carColor;

    public CarPrototype(String id, String brand, DateTime entryTime, int noOfDoors, String carColor) {
            super(id, brand, entryTime);
            this.noOfDoors = noOfDoors;
            this.carColor = carColor;
    }
    
    public CarPrototype(DateTime entryTime){
        super("", "", entryTime);
        this.noOfDoors = 0;
        this.carColor = "";
    }
    
    protected CarPrototype(CarPrototype carPrototype){
        super(carPrototype.getPlateID(), carPrototype.getBrandname(), carPrototype.getEntryTimeObject());
        this.noOfDoors = carPrototype.getNoOfDoors();
        this.carColor = carPrototype.getCarColor();
    }
    
    @Override
    public VehiclePrototype clonar() {
       
        return new CarPrototype(this);
    }
    
    public int getNoOfDoors() {
            return noOfDoors;
    }
    public void setNoOfDoors(int noOfDoors) {
            this.noOfDoors = noOfDoors;
    }
    public String getCarColor() {
            return carColor;
    }
    public void setCarColor(String carColor) {
            this.carColor = carColor;
    }

    @Override
    public int vehicleSize() {
        return 1;//size of car is one
    }

    @Override
    public String toString() {
        return "Car"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
