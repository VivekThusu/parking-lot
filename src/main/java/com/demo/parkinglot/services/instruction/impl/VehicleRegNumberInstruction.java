package com.demo.parkinglot.services.instruction.impl;

import com.demo.parkinglot.model.ParkingLot;
import com.demo.parkinglot.model.ParkingSpace;
import com.demo.parkinglot.services.instruction.InstructionService;

import java.util.Collection;
import java.util.stream.Collectors;

public class VehicleRegNumberInstruction implements InstructionService {

    @Override
    public String perform(ParkingLot parkingLot, ParkingSpace parkingSpace) {
        Collection<ParkingSpace> requiredParkingSpaces = parkingLot.getDriverAgeToParkingSpaceMap()
                .get(parkingSpace.getDriverAge());

        return requiredParkingSpaces.isEmpty() ? "null" : requiredParkingSpaces.stream()
                .map(ParkingSpace::getRegistrationNumber).collect(Collectors.joining(","));
    }
}
