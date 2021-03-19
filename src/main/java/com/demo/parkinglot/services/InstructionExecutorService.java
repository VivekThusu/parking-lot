package com.demo.parkinglot.services;

import com.demo.parkinglot.model.ParkingLot;
import com.demo.parkinglot.model.ParkingSpace;
import org.apache.commons.lang3.tuple.Pair;

public interface InstructionExecutorService {

    void executeInstruction(ParkingLot parkingLot, ParkingSpace parkingSpace, String instruction);

    Pair<String, ParkingSpace> parseInstruction(String instructionText);

}
