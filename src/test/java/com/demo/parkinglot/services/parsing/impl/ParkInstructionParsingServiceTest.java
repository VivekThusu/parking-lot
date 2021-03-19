package com.demo.parkinglot.services.parsing.impl;

import com.demo.parkinglot.exceptions.ParkingLotException;
import com.demo.parkinglot.model.ParkingSpace;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkInstructionParsingServiceTest {

    private final ParkInstructionParsingService parkInstructionParsingService =
            new ParkInstructionParsingService();

    @Test
    void parseInstructionPerTypeTest_positive() {
        Pair<String, ParkingSpace> pair =
                parkInstructionParsingService.parseInstructionPerType("Park KA-01-HH-1234 driver_age 21");
        assertNotNull(pair);
    }

    @Test
    void parseInstructionPerTypeTest_negative() {
        assertThrows(ParkingLotException.class,
                () -> parkInstructionParsingService.parseInstructionPerType("Park KA-01-HH-12347890999 driver_age 21"));
    }
}