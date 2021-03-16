package com.demo.parkinglot.services.instruction.impl;

import com.demo.parkinglot.exceptions.ParkingLotException;
import com.demo.parkinglot.model.ParkingLot;
import com.demo.parkinglot.model.ParkingSpace;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ParkInstructionTest {

    private final ParkingLot parkingLot = ParkingLot.getTestInstance(1);
    private final ParkInstruction parkInstruction = new ParkInstruction();

    @Test
    @Order(1)
    void perform_whenParkingSlotAvailable_thenAbleToPark() {
        ParkingSpace parkingSpace = ParkingSpace.builder().registrationNumber("KA-01-HH-1234")
                .driverAge(26).build();
        String message = parkInstruction.perform(parkingLot, parkingSpace);
        assertEquals("Car with vehicle registration number \"KA-01-HH-1234\" has been parked at slot number 1",
                message);
    }

    @Test
    @Order(2)
    void perform_whenParkingSlotNotAvailable_thenError() {
        ParkingSpace parkingSpace = ParkingSpace.builder().registrationNumber("KA-01-HH-1234")
                .driverAge(26).build();
        parkInstruction.perform(parkingLot, parkingSpace);

        ParkingSpace parkingSpace2 = ParkingSpace.builder().registrationNumber("KA-01-HH-1234")
                .driverAge(26).build();
        assertThrows(ParkingLotException.class,
                () -> parkInstruction.perform(parkingLot, parkingSpace2));
    }
}