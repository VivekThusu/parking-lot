package com.demo.parkinglot.services.parsing.impl;

import com.demo.parkinglot.exceptions.ParkingLotException;
import javafx.util.Pair;
import com.demo.parkinglot.model.ParkingSpace;
import com.demo.parkinglot.services.parsing.InstructionParsingService;
import com.demo.parkinglot.util.InstructionParsingUtil;

import static com.demo.parkinglot.util.InstructionParsingUtil.SLOT_NUMBER_FOR_CAR_WITH_NUMBER_REGEX;
import static com.demo.parkinglot.util.InstructionServiceUtil.SLOT_NUMBER_FOR_CAR_WITH_NUMBER;

public class SlotNumberForCarInstructionParsingService implements InstructionParsingService {

    @Override
    public Pair<String, ParkingSpace> parseInstructionPerType(String instructionText) {

        if (!InstructionParsingUtil.containsParsingError(SLOT_NUMBER_FOR_CAR_WITH_NUMBER_REGEX, instructionText)) {
            String[] inputInstructionTokens = instructionText.split(" ");
            return new Pair<>(SLOT_NUMBER_FOR_CAR_WITH_NUMBER,
                    ParkingSpace.builder().registrationNumber(inputInstructionTokens[1])
                            .build());
        } else {
            throw new ParkingLotException("ERROR: Parsing error in line : " + instructionText);
        }
    }
}
