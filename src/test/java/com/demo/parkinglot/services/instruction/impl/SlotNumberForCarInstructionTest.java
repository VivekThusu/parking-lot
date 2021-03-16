package com.demo.parkinglot.services.instruction.impl;

import com.demo.parkinglot.model.ParkingLot;
import com.demo.parkinglot.model.ParkingSpace;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SlotNumberForCarInstructionTest {

    private final ParkingLot parkingLot = ParkingLot.getTestInstance(1);
    private final ParkInstruction parkInstruction = new ParkInstruction();
    private final SlotNumberForCarInstruction slotNumberForCarInstruction = new SlotNumberForCarInstruction();

    @Test
    @Order(1)
    void perform_whenVehiclePresent_thenReturnParkingSlot() {
        //add a vehicle to the parking lot first. it will be parked in slot 1
        ParkingSpace parkingSpace = ParkingSpace.builder().registrationNumber("KA-01-HH-1234")
                .driverAge(26).build();
        parkInstruction.perform(parkingLot, parkingSpace);

        String message = slotNumberForCarInstruction.perform(parkingLot, ParkingSpace.builder()
                .registrationNumber("KA-01-HH-1234").build());
        assertEquals("1", message);
    }

    @Test
    @Order(2)
    void perform_whenVehicleNotPresent_thenReturnNull() {
        ParkingSpace parkingSpace = ParkingSpace.builder().registrationNumber("MH-01-HH-1234").build();
        assertEquals("null", slotNumberForCarInstruction.perform(parkingLot, parkingSpace));
    }
}