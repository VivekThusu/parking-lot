package com.demo.parkinglot.services.instruction.impl;

import com.demo.parkinglot.model.ParkingLot;
import com.demo.parkinglot.model.ParkingSpace;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VehicleRegNumberInstructionTest {

    private final ParkingLot parkingLot = ParkingLot.getTestInstance(2);
    private final ParkInstruction parkInstruction = new ParkInstruction();
    private final VehicleRegNumberInstruction vehicleRegNumberInstruction =
            new VehicleRegNumberInstruction();

    @Test
    @Order(1)
    void perform_whenVehiclePresent_thenReturnParkingSlots() {
        //add a vehicle to the parking lot first. it will be parked in slot 1
        ParkingSpace parkingSpace = ParkingSpace.builder().registrationNumber("KA-01-HH-1234")
                .driverAge(26).build();
        ParkingSpace parkingSpace2 = ParkingSpace.builder().registrationNumber("JK-01-HH-1234")
                .driverAge(26).build();
        parkInstruction.perform(parkingLot, parkingSpace);
        parkInstruction.perform(parkingLot, parkingSpace2);

        String message = vehicleRegNumberInstruction.perform(parkingLot, ParkingSpace.builder()
                .driverAge(26).build());
        assertEquals("KA-01-HH-1234,JK-01-HH-1234", message);
    }

    @Test
    @Order(2)
    void perform_whenVehicleNotPresent_thenReturnNull() {
        ParkingSpace parkingSpace = ParkingSpace.builder().driverAge(11).build();
        assertEquals("null", vehicleRegNumberInstruction.perform(parkingLot, parkingSpace));
    }
}