package com.demo.parkinglot.services.instruction.impl;

import com.demo.parkinglot.exceptions.ParkingLotException;
import com.demo.parkinglot.model.ParkingLot;
import com.demo.parkinglot.model.ParkingSpace;
import com.demo.parkinglot.services.instruction.InstructionService;

import java.text.MessageFormat;
import java.util.Objects;

/*
This class will allot a parking slot to the vehicle
or returns error message if the parking lot is full.
*/
public class ParkInstruction implements InstructionService {

    private static final String MESSAGE = "Car with vehicle registration number \"{0}\" has been parked at slot number {1}";
    private static final String PARKING_FULL = "ERROR : Parking Lot is full or vehicle already parked. You cannot park your vehicle";

    @Override
    public String perform(ParkingLot parkingLot, ParkingSpace parkingSpace) {
        Integer availableSlot = parkingLot.getAvailableParkingSlots().poll();
        if(Objects.nonNull(availableSlot) && uniqueRegistrationNumber(parkingLot, parkingSpace)) {
            parkingSpace.setParkingSlot(availableSlot);
            parkingLot.getRegistrationNumToParkingSpaceMap().put(parkingSpace.getRegistrationNumber(), parkingSpace);
            parkingLot.getDriverAgeToParkingSpaceMap().put(parkingSpace.getDriverAge(), parkingSpace);
            parkingLot.getSlotToParkingSpaceMap().put(parkingSpace.getParkingSlot(), parkingSpace);
            return MessageFormat.format(MESSAGE, parkingSpace.getRegistrationNumber(), parkingSpace.getParkingSlot());
        }
        throw new ParkingLotException(PARKING_FULL);
    }

    private boolean uniqueRegistrationNumber(ParkingLot parkingLot, ParkingSpace parkingSpace) {
        return !parkingLot.getRegistrationNumToParkingSpaceMap().containsKey(parkingSpace.getRegistrationNumber());
    }
}
