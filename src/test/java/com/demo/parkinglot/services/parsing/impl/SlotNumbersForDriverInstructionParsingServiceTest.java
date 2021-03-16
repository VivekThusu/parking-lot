package com.demo.parkinglot.services.parsing.impl;

import com.demo.parkinglot.exceptions.ParkingLotException;
import com.demo.parkinglot.model.ParkingSpace;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlotNumbersForDriverInstructionParsingServiceTest {

    private final SlotNumbersForDriverInstructionParsingService slotNumbersForDriverInstructionParsingService =
            new SlotNumbersForDriverInstructionParsingService();

    @Test
    void parseInstructionPerTypeTest_positive() {
        Pair<String, ParkingSpace> pair = slotNumbersForDriverInstructionParsingService
                .parseInstructionPerType("Slot_numbers_for_driver_of_age 21");
        assertNotNull(pair);
    }

    @Test
    void parseInstructionPerTypeTest_negative() {
        assertThrows(ParkingLotException.class, () -> slotNumbersForDriverInstructionParsingService
                .parseInstructionPerType("Slot_numbers_for_driver_of_age abcd"));
    }

}