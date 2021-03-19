package com.demo.parkinglot.services.parsing;

import com.demo.parkinglot.exceptions.ParkingLotException;
import com.demo.parkinglot.model.ParkingSpace;
import org.apache.commons.lang3.tuple.Pair;

public interface InstructionParsingService {

    Pair<String, ParkingSpace> parseInstructionPerType(String instructionText) throws ParkingLotException;

}
