package com.demo.parkinglot.services;

import javafx.util.Pair;
import com.demo.parkinglot.model.ParkingLot;
import com.demo.parkinglot.model.ParkingSpace;

public interface InstructionExecutorService {

    void executeInstruction(ParkingLot parkingLot, ParkingSpace parkingSpace, String instruction);

    Pair<String, ParkingSpace> parseInstruction(String instructionText);

}
