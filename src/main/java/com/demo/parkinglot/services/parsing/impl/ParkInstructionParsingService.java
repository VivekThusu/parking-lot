package com.demo.parkinglot.services.parsing.impl;

import com.demo.parkinglot.exceptions.ParkingLotException;
import javafx.util.Pair;
import com.demo.parkinglot.model.ParkingSpace;
import com.demo.parkinglot.services.parsing.InstructionParsingService;
import com.demo.parkinglot.util.InstructionParsingUtil;

import static com.demo.parkinglot.util.InstructionParsingUtil.PARK_REGEX;
import static com.demo.parkinglot.util.InstructionServiceUtil.PARK;

public class ParkInstructionParsingService implements InstructionParsingService {

    @Override
    public Pair<String, ParkingSpace> parseInstructionPerType(String instructionText) throws ParkingLotException {

        if (!InstructionParsingUtil.containsParsingError(PARK_REGEX, instructionText)) {
            String[] inputInstructionTokens = instructionText.split(" ");
            return new Pair<>(PARK,
                    ParkingSpace.builder().registrationNumber(inputInstructionTokens[1])
                            .driverAge(Integer.parseInt(inputInstructionTokens[3]))
                            .build());
        } else {
            throw new ParkingLotException("ERROR: Parsing error in line : " + instructionText);
        }
    }
}
