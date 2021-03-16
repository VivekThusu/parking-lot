package com.demo.parkinglot.services.instruction.impl;

import com.demo.parkinglot.exceptions.ParkingLotException;
import com.demo.parkinglot.model.ParkingLot;
import com.demo.parkinglot.model.ParkingSpace;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LeaveInstructionTest {

    private final ParkingLot parkingLot = ParkingLot.getTestInstance(1);
    private final ParkInstruction parkInstruction = new ParkInstruction();
    private final LeaveInstruction leaveInstruction = new LeaveInstruction();

    @Test
    @Order(1)
    void perform_whenParkingSlotOccupied_thenAbleToVacate() {
        //add a vehicle to the parking lot first. it will be parked in slot 1
        ParkingSpace parkingSpace = ParkingSpace.builder().registrationNumber("KA-01-HH-1234")
                .driverAge(26).build();
        parkInstruction.perform(parkingLot, parkingSpace);

        String message = leaveInstruction.perform(parkingLot, ParkingSpace.builder()
                .parkingSlot(1).build());
        assertEquals("Slot number 1 vacated, the car with vehicle registration number \"KA-01-HH-1234\" left the space, the driver of the car was of age 26",
                message);
    }

    @Test
    @Order(2)
    void perform_whenVehicleNotParked_thenError() {
        ParkingSpace parkingSpace = ParkingSpace.builder().parkingSlot(10).build();
        assertThrows(ParkingLotException.class,
                () -> leaveInstruction.perform(parkingLot, parkingSpace));
    }
}