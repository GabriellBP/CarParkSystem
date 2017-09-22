/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles;

import carparksystem.DateTime;

public class MotorBikePrototype extends VehiclePrototype {
	protected int bikeEngineSize;

	MotorBikePrototype(String id, String brand, DateTime entryTime, int size) {
            super(id, brand, entryTime);
            this.bikeEngineSize = size;
	}
        
        public MotorBikePrototype(DateTime entryTime){
            super("", "", entryTime);
            this.bikeEngineSize = 0;
        }
        
        MotorBikePrototype(MotorBikePrototype motorBike) {
            super(motorBike.getPlateID(), motorBike.getBrandname(), motorBike.getEntryTimeObject());
            this.bikeEngineSize = motorBike.getEngineSize();
        }
        
        @Override
        public VehiclePrototype clonar() {
            return new MotorBikePrototype(this);
        }
        
	// setter for vehicle engineSize
	public void setEngineSize(int size) {
		bikeEngineSize = size;
	}

	// getter for vehicle engineSize
	public int getEngineSize() {
		return bikeEngineSize;
	}

    

}