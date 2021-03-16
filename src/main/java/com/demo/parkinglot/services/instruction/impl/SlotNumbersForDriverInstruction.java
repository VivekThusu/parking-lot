package com.demo.parkinglot.services.instruction.impl;

import com.demo.parkinglot.model.ParkingLot;
import com.demo.parkinglot.model.ParkingSpace;
import com.demo.parkinglot.services.instruction.InstructionService;

import java.util.Collection;
import java.util.stream.Collectors;

/*
This class will return all slot numbers(comma separated) for all vehicles which have the given driver age.
or return null if no match found
*/
public class SlotNumbersForDriverInstruction implements InstructionService {

    @Override
    public String perform(ParkingLot parkingLot, ParkingSpace parkingSpace) {
        Collection<ParkingSpace> requiredParkingSpaces = parkingLot.getDriverAgeToParkingSpaceMap()
                .get(parkingSpace.getDriverAge());

        return requiredParkingSpaces.isEmpty() ? "null" : requiredParkingSpaces.stream()
                .map(space -> space.getParkingSlot().toString()).collect(Collectors.joining(","));
    }
}
