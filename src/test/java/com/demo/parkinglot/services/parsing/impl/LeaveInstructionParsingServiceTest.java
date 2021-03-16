package com.demo.parkinglot.services.parsing.impl;

import com.demo.parkinglot.exceptions.ParkingLotException;
import com.demo.parkinglot.model.ParkingSpace;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaveInstructionParsingServiceTest {

    private final LeaveInstructionParsingService leaveInstructionParsingService =
            new LeaveInstructionParsingService();

    @Test
    void parseInstructionPerTypeTest_positive() {
        Pair<String, ParkingSpace> pair = leaveInstructionParsingService.parseInstructionPerType("Leave 10");
        assertNotNull(pair);
    }

    @Test
    void parseInstructionPerTypeTest_negative() {
        assertThrows(ParkingLotException.class,
                () -> leaveInstructionParsingService.parseInstructionPerType("Leave abc"));
    }
}