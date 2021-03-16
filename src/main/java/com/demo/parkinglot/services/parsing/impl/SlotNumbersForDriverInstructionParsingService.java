package com.demo.parkinglot.services.parsing.impl;

import com.demo.parkinglot.exceptions.ParkingLotException;
import javafx.util.Pair;
import com.demo.parkinglot.model.ParkingSpace;
import com.demo.parkinglot.services.parsing.InstructionParsingService;
import com.demo.parkinglot.util.InstructionParsingUtil;

import static com.demo.parkinglot.util.InstructionParsingUtil.SLOT_NUMBERS_FOR_DRIVER_OF_AGE_REGEX;
import static com.demo.parkinglot.util.InstructionServiceUtil.SLOT_NUMBERS_FOR_DRIVER_OF_AGE;

/*
 * This class is responsible for parsing SlotNumbersForDriverAge instructions.
 * Throws ParkingLotException in case of errors
 * */
public class SlotNumbersForDriverInstructionParsingService implements InstructionParsingService {

    @Override
    public Pair<String, ParkingSpace> parseInstructionPerType(String instructionText) {

        if (!InstructionParsingUtil.containsParsingError(SLOT_NUMBERS_FOR_DRIVER_OF_AGE_REGEX, instructionText)) {
            String[] inputInstructionTokens = instructionText.split(" ");
            return new Pair<>(SLOT_NUMBERS_FOR_DRIVER_OF_AGE,
                    ParkingSpace.builder().driverAge(Integer.parseInt(inputInstructionTokens[1]))
                            .build());
        } else {
            throw new ParkingLotException("ERROR: Parsing error in line : " + instructionText);
        }
    }
}
