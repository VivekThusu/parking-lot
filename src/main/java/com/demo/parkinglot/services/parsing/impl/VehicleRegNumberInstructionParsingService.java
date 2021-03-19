package com.demo.parkinglot.services.parsing.impl;

import com.demo.parkinglot.exceptions.ParkingLotException;
import com.demo.parkinglot.model.ParkingSpace;
import com.demo.parkinglot.services.parsing.InstructionParsingService;
import com.demo.parkinglot.util.InstructionParsingUtil;
import org.apache.commons.lang3.tuple.Pair;

import static com.demo.parkinglot.util.InstructionParsingUtil.VEHICLE_REGISTRATION_NUMBER_FOR_DRIVER_OF_AGE_REGEX;
import static com.demo.parkinglot.util.InstructionServiceUtil.VEHICLE_REGISTRATION_NUMBER_FOR_DRIVER_OF_AGE;

/*
 * This class is responsible for parsing VehicleNumber instructions.
 * Throws ParkingLotException in case of errors
 * */
public class VehicleRegNumberInstructionParsingService implements InstructionParsingService {

    @Override
    public Pair<String, ParkingSpace> parseInstructionPerType(String instructionText) {

        if(!InstructionParsingUtil.containsParsingError(VEHICLE_REGISTRATION_NUMBER_FOR_DRIVER_OF_AGE_REGEX,
                instructionText)) {
            String[] inputInstructionTokens = instructionText.split(" ");
            return Pair.of(VEHICLE_REGISTRATION_NUMBER_FOR_DRIVER_OF_AGE,
                    ParkingSpace.builder().driverAge(Integer.parseInt(inputInstructionTokens[1]))
                            .build());
        }
        else {
            throw new ParkingLotException("ERROR: Parsing error in line : " + instructionText);
        }
    }
}
