package com.demo.parkinglot.services.parsing.impl;

import com.demo.parkinglot.exceptions.ParkingLotException;
import javafx.util.Pair;
import com.demo.parkinglot.model.ParkingSpace;
import com.demo.parkinglot.services.parsing.InstructionParsingService;
import com.demo.parkinglot.util.InstructionParsingUtil;

import static com.demo.parkinglot.util.InstructionParsingUtil.LEAVE_REGEX;
import static com.demo.parkinglot.util.InstructionServiceUtil.LEAVE;

/*
 * This class is responsible for parsing Leave instructions.
 * Throws ParkingLotException in case of errors
 * */
public class LeaveInstructionParsingService implements InstructionParsingService {

    @Override
    public Pair<String, ParkingSpace> parseInstructionPerType(String instructionText) {

        if (!InstructionParsingUtil.containsParsingError(LEAVE_REGEX, instructionText)) {
            String[] inputInstructionTokens = instructionText.split(" ");
            return new Pair<>(LEAVE,
                    ParkingSpace.builder()
                            .parkingSlot(Integer.parseInt(inputInstructionTokens[1]))
                            .build());
        } else {
            throw new ParkingLotException("ERROR: Parsing error in line : " + instructionText);
        }
    }
}
