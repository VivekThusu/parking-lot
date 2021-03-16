package com.demo.parkinglot.services.instruction.impl;

import com.demo.parkinglot.exceptions.ParkingLotException;
import com.demo.parkinglot.model.ParkingLot;
import com.demo.parkinglot.model.ParkingSpace;
import com.demo.parkinglot.services.instruction.InstructionService;

import java.text.MessageFormat;

/*
This class will vacate the given parking slot
or returns error message if the parking slot is already vacant.
*/
public class LeaveInstruction implements InstructionService {

    private static final String MESSAGE = "Slot number {0} vacated, the car with vehicle registration number \"{1}\" left the space, the driver of the car was of age {2}";
    private static final String PARKING_NOT_FOUND = "ERROR : Slot already vacant {0}";

    @Override
    public String perform(ParkingLot parkingLot, ParkingSpace parkingSpace) {
        ParkingSpace parkingSpaceToRemove = parkingLot.getSlotToParkingSpaceMap().get(parkingSpace.getParkingSlot());
        if(parkingSpaceToRemove == null) {
            String error;
            if(parkingSpace.getParkingSlot() > parkingLot.getTotalNumberOfParkingSpaces()) {
                error = "ERROR: There are only " + parkingLot.getTotalNumberOfParkingSpaces() + " spaces in total." +
                        " Cannot vacate " + parkingSpace.getParkingSlot();
            } else {
                error = PARKING_NOT_FOUND;
            }
            throw new ParkingLotException(MessageFormat.format(error, parkingSpace.getParkingSlot()));
        }
        parkingLot.getSlotToParkingSpaceMap().remove(parkingSpaceToRemove.getParkingSlot());
        parkingLot.getDriverAgeToParkingSpaceMap().remove(parkingSpaceToRemove.getDriverAge(), parkingSpaceToRemove);
        parkingLot.getRegistrationNumToParkingSpaceMap().remove(parkingSpaceToRemove.getRegistrationNumber());
        parkingLot.getAvailableParkingSlots().add(parkingSpaceToRemove.getParkingSlot());

        return MessageFormat.format(MESSAGE, parkingSpaceToRemove.getParkingSlot(),
                parkingSpaceToRemove.getRegistrationNumber(), parkingSpaceToRemove.getDriverAge());
    }
}
