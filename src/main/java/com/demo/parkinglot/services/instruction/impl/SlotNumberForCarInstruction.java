package com.demo.parkinglot.services.instruction.impl;

import com.demo.parkinglot.model.ParkingLot;
import com.demo.parkinglot.model.ParkingSpace;
import com.demo.parkinglot.services.instruction.InstructionService;

import java.util.Objects;

/*
This class will return the Slot number for car with the given registration number
or return null if no match found
*/
public class SlotNumberForCarInstruction implements InstructionService {

    @Override
    public String perform(ParkingLot parkingLot, ParkingSpace parkingSpace) {
        ParkingSpace requiredParkingSpace = parkingLot.getRegistrationNumToParkingSpaceMap()
                .get(parkingSpace.getRegistrationNumber());
        return Objects.nonNull(requiredParkingSpace) ? requiredParkingSpace.getParkingSlot().toString() : "null";
    }
}
