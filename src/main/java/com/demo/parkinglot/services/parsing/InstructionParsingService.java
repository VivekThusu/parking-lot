package com.demo.parkinglot.services.parsing;

import com.demo.parkinglot.exceptions.ParkingLotException;
import javafx.util.Pair;
import com.demo.parkinglot.model.ParkingSpace;

public interface InstructionParsingService {

    Pair<String, ParkingSpace> parseInstructionPerType(String instructionText) throws ParkingLotException;

}
