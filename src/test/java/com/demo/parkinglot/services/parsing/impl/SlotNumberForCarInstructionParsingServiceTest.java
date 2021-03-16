package com.demo.parkinglot.services.parsing.impl;

import com.demo.parkinglot.exceptions.ParkingLotException;
import com.demo.parkinglot.model.ParkingSpace;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlotNumberForCarInstructionParsingServiceTest {

    private final SlotNumberForCarInstructionParsingService slotNumberForCarInstructionParsingService =
            new SlotNumberForCarInstructionParsingService();

    @Test
    void parseInstructionPerTypeTest_positive() {
        Pair<String, ParkingSpace> pair = slotNumberForCarInstructionParsingService
                .parseInstructionPerType("Slot_number_for_car_with_number PB-01-HH-1234");
        assertNotNull(pair);
    }

    @Test
    void parseInstructionPerTypeTest_negative() {
        assertThrows(ParkingLotException.class, () -> slotNumberForCarInstructionParsingService
                .parseInstructionPerType("vivek thusu Slot_number_for_car_with_number PB-01-HH-1234"));
    }
}