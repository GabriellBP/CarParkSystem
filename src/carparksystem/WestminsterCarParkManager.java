package carparksystem;

import java.util.ArrayList;
import java.util.Scanner;
import slots.EmptySlotState;
import slots.Slot;
import vehicles.CarPrototype;
import vehicles.MotorBikePrototype;
import vehicles.VanPrototype;
import vehicles.VehiclePrototype;

public class WestminsterCarParkManager implements CarParkManager {
        private final int sizeParking = 20;
        private Slot[] vehicleParkingSlots = new Slot[sizeParking];//parking space array to store vehicle objects
	static Scanner input = new Scanner(System.in);
	private VehiclePrototype lastEntry = null;//to find the last entry of the vehicle which was entered
        DateTime entry = new DateTime(0, 0, 0, 0, 0);
        //PrototypeAplication
        private CarPrototype car = new CarPrototype(entry);
        private MotorBikePrototype motorBike = new MotorBikePrototype(entry);
        private VanPrototype van = new VanPrototype(entry);
	
        private ArrayList<Integer> vehicleOrderList = new ArrayList<Integer>();//is used to have the index of the vehicles 
	//which are currently parked in the last in First out approach
	private ArrayList<VehiclePrototype> deletedTempVehicleList = new ArrayList<VehiclePrototype>();//stores the vehicle object which had left the parking space

	public static void main(String[] args) {
		System.out.println(" ---------------------------------------------");
		System.out.println(" |********-Car Park Management System-********|");
		System.out.println(" ---------------------------------------------\n");
		displayLogin();//redirected to the displayLogin method
	}

	public static void displayLogin() {
		System.out.println("\n****** Login Menu ******");
		Scanner loginInput = new Scanner(System.in);
		System.out.print("Enter Username: ");
		String userName = loginInput.next();
		System.out.print("Enter Password: ");
		String password = loginInput.next();
		if (userName.equals("admin") && password.equals("admin123")) {// if
																		// userName
																		// and
																		// password equals
			// then display menu
			displayMainMenu();// directed to display menu method
		} else {// else display error message
			System.out.println("\n#Incorrect Username or Password, Please Try Again#");
			displayLogin();// login menu prompted again
		}
		loginInput.close();//close the scanner
	}

	public static void displayMainMenu() {
		CarParkManager carParkObject = new WestminsterCarParkManager();     
                carParkObject.parkingSlotInicialization();
		while (true) {//infinity loop
			System.out.println("");
			System.out.println("1. Add a vehicle to the parking Space");
			System.out.println("2. Delete a vehicle from the parking Space");
			System.out.println("3. Display all vehicles parked currently in the parking space");
			System.out.println("4. Display statistics of the car park");
			System.out.println("5. Display vehicles of a specific Day");
			System.out.println("Press Q to Quit the Program.");
			System.out.print("Enter Selection: ");
                        
			String userInput = input.next();// prompted for input and stored in											// the userInput variable
			System.out.println();

			switch (userInput.toLowerCase()) {
			case "1":
				carParkObject.addVehicle();
				break;
			case "2":
				carParkObject.deleteVehicle();
				break;
			case "3":
				carParkObject.displayCurrentList();
				break;
			case "4":
				carParkObject.printStatistics();
				break;
			case "5":
				carParkObject.displayPerDayList();
				break;
			case "q":
				System.exit(0);// terminates the program
				break;
			default:
				System.err.println("\n**Please, Enter a Valid Input**");
				System.out.println();
			}
		}
	}

        @Override
	public void parkingSlotInicialization(){ 
            for(int s = 0; s<sizeParking; s++){
                    vehicleParkingSlots[s] = new Slot(s);
                }
        }
        
        @Override
	public void addVehicle() {
		boolean typeVal = false;
		String vehicleType = ""; // String variable to hold the vehicle type
		while (!typeVal) { // loop to make sure only valid type is being entered
			System.out.print("Enter vehicle type(car/bike/van): ");
			vehicleType = input.next();
			if (vehicleType.equalsIgnoreCase("car") || vehicleType.equalsIgnoreCase("van")
					|| vehicleType.equalsIgnoreCase("bike")) {
				typeVal = true;
			} else {
				System.err.println("\n**Please, Enter a Valid Input**");
				System.out.println("");
			}
		}
                VehiclePrototype vehicle;//obs.: pensar em colocar isso no lugar do carr, vann ....
                switch (vehicleType) {
		case "car":
                        //PrototypeAplication
                        vehicle = (CarPrototype) car.clonar();
			break;
		case "van":
                        //Prototype
                        vehicle = (VanPrototype) van.clonar();
			break;
		default:
                        //Prototype
                        vehicle = (MotorBikePrototype) motorBike.clonar();
			break;
		}
                
		int checkFreeSpace = checkForFreeSlot(vehicle);
		if (checkFreeSpace == -1) {
			System.out.println("**Parking Slot Full, No free slot available!**");
			return;
		}

		System.out.print("Enter Vehicle Plate ID number: ");
		String id = input.next();
		System.out.print("Enter Vehicle brand name: ");
		String brand = input.next();
		int hours, mins, date, month, year;

		do {
			System.out.print(
					"Enter entry time/date (HourHour(HH) MinsMins(MM) DayDay(DD) MonthMonth(MM) YearYearYearYear(YYYY): ");
			hours = input.nextInt();
			mins = input.nextInt();
			date = input.nextInt();
			month = input.nextInt();
			year = input.nextInt();
		} while (hours < 0 || hours > 24 || mins < 0 || mins > 60 || date < 0 || date > 31 || month < 0 || month > 12
				|| year != 2016);
		// validate the date and time
		DateTime entryTime = new DateTime(hours, mins, date, month, year);
		vehicleOrderList.add(checkFreeSpace);

		switch (vehicleType) {
		case "car":
			System.out.print("Enter no of doors: ");
			while (!input.hasNextInt()) {
				System.err.print("Please, Enter a integer value for No of Doors : ");
				input.next();// validating user input based on string value
			}
			int noOfDoors = input.nextInt();
                        input.nextLine();
			System.out.print("Enter car color: ");
			String color = input.next();
                        //PrototypeAplication
                        CarPrototype carr = (CarPrototype) car.clonar();
                        carr.setBrandName(brand);
                        carr.setEntryTime(entryTime);
                        carr.setPlateID(id);
                        carr.setNoOfDoors(noOfDoors);
                        carr.setCarColor(color);
			for(int k = 0; k<carr.vehicleSize(); k++){//Strategy
                            vehicleParkingSlots[checkFreeSpace + k].setVehicle(carr);
                            vehicleParkingSlots[checkFreeSpace + k].setSlotState(vehicleParkingSlots[checkFreeSpace + k].getSlotState().changeState());//State
                        }
			break;
		case "van":
			System.out.print("Enter cargo volume: ");
			while (!input.hasNextDouble()) {
				System.err.print("Please, Enter a integer value for Cargo Volume : ");
				input.next();// validating user input based on string value
			}
			double volume = input.nextDouble();
                        //Prototype
                        VanPrototype vann = (VanPrototype) van.clonar();
                        vann.setPlateID(id);
                        vann.setCargoVolume(volume);
                        vann.setEntryTime(entryTime);
                        vann.setBrandName(brand);
                        
                        for(int k = 0; k<vann.vehicleSize(); k++){//Strategy
                            vehicleParkingSlots[checkFreeSpace + k].setVehicle(vann);
                            vehicleParkingSlots[checkFreeSpace + k].setSlotState(vehicleParkingSlots[checkFreeSpace + k].getSlotState().changeState());//State
                            
                            
                        }
			break;
		case "bike":
			System.out.print("Enter engine size: ");
			while (!input.hasNextInt()) {
				System.err.print("Please, Enter a integer value for engine size : ");
				input.next();// validating user input based on string value
			}
			int size = input.nextInt();
                        //Prototype
                        MotorBikePrototype motorrBike = (MotorBikePrototype) motorBike.clonar();
                        motorrBike.setPlateID(id);
                        motorrBike.setEntryTime(entryTime);
                        motorrBike.setBrandName(brand);
                        motorrBike.setEngineSize(size);
                        
                        for(int k = 0; k<motorrBike.vehicleSize(); k++){//Strategy
                            vehicleParkingSlots[checkFreeSpace + k].setVehicle(motorrBike);
                            vehicleParkingSlots[checkFreeSpace + k].setSlotState(vehicleParkingSlots[checkFreeSpace + k].getSlotState().changeState());//State
                        }
			break;
		}
		lastEntry = vehicleParkingSlots[checkFreeSpace].getVehicle();
		System.out.println("");
		System.out.println("Vehicle parked Sucessfully!");
		System.out.println("No of free slots remaining is " + totalOfSlots());
	}

	public int checkForFreeSlot(VehiclePrototype vehicle) {
            first:
		for (int i = 0; i < sizeParking; i++) { // iterating through each slot to find a
										// free spot
			if (vehicleParkingSlots[i].getSlotState().toString() == "Empty Slot") {            
                            for(int j = 1; j < vehicle.vehicleSize(); j++)//Strategy											// also free
                                if (vehicleParkingSlots[i + j].getSlotState().toString() == "Full Slot") {
                                        continue first;
                                }
                            return i;
			}
		}
		return -1; // if there is no free slots
	}

	public int totalOfSlots() {
		int number = 0;
		for (int i = 0; i < sizeParking; i++) {
			if (vehicleParkingSlots[i].getSlotState().toString().equals("Empty Slot")) {
				++number;
			}
		}
		return number;
	}

	
        @Override
	public void deleteVehicle() {
		boolean foundFlag = false;
		int i;
		System.out.print("Enter Plate ID number of the vehicle to be deleted: ");
		String id = input.next();

		for (i = 0; i < sizeParking; i++) { // loop to find the element index
			if (vehicleParkingSlots[i].getSlotState().toString() == "Full Slot") { // to check whether an object
													// is there (To avoid
													// nullPointerException)
				if (vehicleParkingSlots[i].getVehicle().getPlateID().equalsIgnoreCase(id)) {
					foundFlag = true;
					break; // end for loop once the value is found
				}
			}
		}
		if (!foundFlag) {
			System.err.println("**Invalid Vehicle Plate ID, Please Try Again**"); // if
																					// entered
																					// ID
																					// is
																					// not
																					// found
																					// in
																					// the
																					// parking
																					// slot
			return;
		}
		// to display the vehicle leaving
		String VehicleType = vehicleParkingSlots[i].getVehicle().toString();
		System.out.println("A " + VehicleType + " left the parking space.");
		deletedTempVehicleList.add(vehicleParkingSlots[i].getVehicle());
		
                // to physically remove the element from the vehicle array
                int vehicleSize = vehicleParkingSlots[i].getVehicle().vehicleSize();//Strategy
                for(int k = 0; k<vehicleSize; k++)
                    vehicleParkingSlots[i + k].setSlotState(vehicleParkingSlots[i + k].getSlotState().changeState());//State
		vehicleOrderList.remove(i);

	}

	// Override
	public void printStatistics() {
		printVehiclePercentage(); // method call of the method which prints
									// vehicle percentage
		printFirstAndLastVehicle();
	}

	private void printVehiclePercentage() {
		int car = 0, van = 0, bike = 0, total = 0;
		String vehicleType;
		for (int i = 0; i < sizeParking; i++) { // loop to find the element index
			if (vehicleParkingSlots[i].getSlotState().toString() == "Full Slot") { // if an element is not empty
				vehicleType = vehicleParkingSlots[i].getVehicle().getClass().getSimpleName();
				++total;
				switch (vehicleType) { // to increment each vehicle type counter
				case "CarPrototype":
					++car;
					break;
				case "VanPrototype":
					++van;
					break;
				case "MotorBikePrototype":
					++bike;
					break;
				}
                            i+=vehicleParkingSlots[i].getVehicle().vehicleSize();//strategy
			}
		}
		// Percentage calculation
		int carPercentage, vanPercentage, bikePercentage;
		if (total == 0) { // if carpark is empty(to avoid arithmeticException)
			carPercentage = 0;
			vanPercentage = 0;
			bikePercentage = 0;
		} else {
			carPercentage = (car * 100 / total);
			vanPercentage = (van * 100 / total);
			bikePercentage = (bike * 100 / total);
		}

		System.out.println("Currently Parked Vehicle percentage");
		System.out.println("------------------------------------");
		System.out.println("         CAR : " + carPercentage + "%");
		System.out.println("         VAN : " + vanPercentage + "%");
		System.out.println("         BIKE: " + bikePercentage + "%");
		System.out.println("");
	}

	private void printFirstAndLastVehicle() {
		// to find the vehicle that was parked first.
		if (vehicleOrderList.size() != 0) {
			System.out.println("First vehicle Which was parked");
			System.out.println("-------------------------------");
			System.out.println("ID : " + vehicleParkingSlots[vehicleOrderList.get(0)].getVehicle().getPlateID());
			System.out.println("Type : " + vehicleParkingSlots[vehicleOrderList.get(0)].getVehicle().toString());
			System.out.println("Entry time : " + vehicleParkingSlots[vehicleOrderList.get(0)].getVehicle().getEntryTime());
		} else {
			System.out.println("No vehicle in the parking currently");
		}

		// to find the last vehicle that entered the parking slot.
		if (lastEntry != null) {
			System.out.println("\nLast vehicle which was parked");
			System.out.println("------------------------------");
			System.out.println("ID : " + lastEntry.getPlateID());
			System.out.println("Type : " + lastEntry.toString());
			System.out.println("Entry time : " + lastEntry.getEntryTime());
		} else {
			System.out.println("**The Parking space is Empty no vehicles are parked currently**");
		}

	}

	// Override
	public void displayCurrentList() {
		if (vehicleOrderList.size() == 0) {
			System.out.println("No vehicles are currently available in the parking space");
		}
		for (int i = (vehicleOrderList.size() - 1); i >= 0; i--) {
			int index = vehicleOrderList.get(i);
			System.out.println("Slot " + (index + 1) + " is Occupied.");
			System.out.println("ID plate: " + vehicleParkingSlots[index].getVehicle().getPlateID());
			System.out.println("Entry time: " + vehicleParkingSlots[index].getVehicle().getEntryTime());
			System.out.println("Type: " + vehicleParkingSlots[index].getVehicle().toString());
			System.out.println("");
		}

	}

	// Override
	public void displayPerDayList() {
		boolean isValidFlag = true;
		int date = 0, month = 0, year = 0;
		do {
			System.out.print("Enter DayDay(DD) MonthMonth(MM) YearYearYearYear(YYYY) to search for Vehicles: ");
			try {
				date = Integer.parseInt(input.next());
				month = Integer.parseInt(input.next());
				year = Integer.parseInt(input.next());
				if (date > 0 || date < 31 || month > 0 || month < 12 || year == 2016) {
					isValidFlag = true;
				} else {
					isValidFlag = false;
					System.err.println("Invalid Date input, Please Try Again");
				}
			} catch (Exception ex) {
				System.err.println("Invalid input, Please Try Again");
				isValidFlag = false;
			}
		} while (!isValidFlag);
		int count = 0;
		for (int i = 0; i < sizeParking; i++) {
			if (vehicleParkingSlots[i].getSlotState().toString() == "Full Slot") {
				int objectDate = vehicleParkingSlots[i].getVehicle().getEntryTimeObject().getDay();
				int objectMonth = vehicleParkingSlots[i].getVehicle().getEntryTimeObject().getMonth();
				int objectYear = vehicleParkingSlots[i].getVehicle().getEntryTimeObject().getYear();
				if ((objectDate == date) && (objectMonth == month) && (objectYear == year)) {
					count++;
					String type = vehicleParkingSlots[i].getVehicle().toString();
					String id = vehicleParkingSlots[i].getVehicle().getPlateID();
					System.out.println(count + " : " + type + " Plate ID No : " + id);
					if (type.equalsIgnoreCase("van")) {
						i++;
					}
				}
			}
		}
		for (int y = 0; y < deletedTempVehicleList.size(); y++) {
			System.out.println("im here 3");
			int objDate = deletedTempVehicleList.get(y).getEntryTimeObject().getDay();
			int objMonth = deletedTempVehicleList.get(y).getEntryTimeObject().getMonth();
			int objYear = deletedTempVehicleList.get(y).getEntryTimeObject().getYear();
			if ((objDate == date) && (objMonth == month) && (objYear == year)) {
				count++;
				String type = deletedTempVehicleList.get(y).toString();
				String id = deletedTempVehicleList.get(y).getPlateID();
				System.out.println(count + " : " + type + " ID No : " + id);
				if (type.equalsIgnoreCase("van")) {
					y++;
				}
			}
		}

		if (count == 0) {
			System.out.println("**No vehicles were currently parked on " + date + "-" + month + "-" + year + "**");
		}
	}

	// Override
	public void displayParkingCharges() {
		boolean isValidFlag = true;
		int currentHour = 0, curentMins = 0;
		do { // loop to repeat until correct time format is being entered
			System.out.print("Enter current time/date (HourHour(HH) MinsMins(MM):");
			try {
				currentHour = Integer.parseInt(input.next());
				curentMins = Integer.parseInt(input.next());
				if (currentHour < 0 || currentHour > 24 || curentMins < 0 || curentMins > 60) {
					isValidFlag = false;
					System.err.println("Invalid Time input, Please Try Again");
				}
			} catch (Exception e) {
				System.err.println("Invalid input, Please Try Again");
				isValidFlag = false;
			}
		} while (!isValidFlag);

		int getHour, getMin, differenceMin, differenceHour;

		for (int i = 0; i < sizeParking; i++) {
			if (vehicleParkingSlots[i].getSlotState().toString() == "Full Slot") {
				getHour = vehicleParkingSlots[i].getVehicle().getEntryTimeObject().getHours();
				getMin = vehicleParkingSlots[i].getVehicle().getEntryTimeObject().getMins();

				if (curentMins < getMin) {
					differenceMin = 60 + curentMins - getMin;
					if (currentHour == 00) {
						currentHour = 23;
					} else {
						currentHour--;
					}
				} else {
					differenceMin = curentMins - getMin;
				}

				if (currentHour < getHour) {
					differenceHour = currentHour + 24 - getHour;
				} else {
					differenceHour = currentHour - getHour;
				}

				double charge = 0;
				int x = 1;
				double hoursParked = Math.ceil(differenceHour + (differenceMin * 1.0 / 60));

				if (hoursParked > 6) {
					charge = 30;
				} else {
					if (hoursParked > 3) {
						charge = 9;
						x = 4;
					}
				}
				for (; x <= hoursParked; x++) {
					if (hoursParked > 3) {
						charge += 3;
					} else {
						charge += 3;
					}
				}
				System.out.println("Vehicle ID : " + vehicleParkingSlots[i].getVehicle().getPlateID() + "   Charge: " + charge);
				String type = vehicleParkingSlots[i].getVehicle().toString();
				if (type.equalsIgnoreCase("Van")) {
					++i;
				}
			}
		}
	}
}
