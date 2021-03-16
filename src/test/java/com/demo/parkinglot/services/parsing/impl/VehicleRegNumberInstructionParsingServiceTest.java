package com.demo.parkinglot.services.parsing.impl;

import com.demo.parkinglot.exceptions.ParkingLotException;
import com.demo.parkinglot.model.ParkingSpace;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleRegNumberInstructionParsingServiceTest {

    private final VehicleRegNumberInstructionParsingService vehicleRegNumberInstructionParsingService =
            new VehicleRegNumberInstructionParsingService();

    @Test
    void parseInstructionPerTypeTest_positive() {
        Pair<String, ParkingSpace> pair = vehicleRegNumberInstructionParsingService
                .parseInstructionPerType("Vehicle_registration_number_for_driver_of_age 18");
        assertNotNull(pair);
    }

    @Test
    void parseInstructionPerTypeTest_negative() {
        assertThrows(ParkingLotException.class, () -> vehicleRegNumberInstructionParsingService
                .parseInstructionPerType("Vehicle_registration_number_for_driver_of_age anything"));
    }
}