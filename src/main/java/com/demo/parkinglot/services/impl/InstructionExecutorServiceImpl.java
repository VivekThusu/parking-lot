package com.demo.parkinglot.services.impl;

import com.demo.parkinglot.exceptions.ParkingLotException;
import com.demo.parkinglot.model.ParkingLot;
import com.demo.parkinglot.model.ParkingSpace;
import com.demo.parkinglot.services.InstructionExecutorService;
import com.demo.parkinglot.services.instruction.InstructionService;
import com.demo.parkinglot.services.parsing.InstructionParsingService;
import com.demo.parkinglot.util.InstructionParsingUtil;
import com.demo.parkinglot.util.InstructionServiceUtil;
import org.apache.commons.lang3.tuple.Pair;


public class InstructionExecutorServiceImpl implements InstructionExecutorService {

    @Override
    public void executeInstruction(ParkingLot parkingLot, ParkingSpace parkingSpace, String instruction) {
        try {
            //get instruction service based on type of instruction
            InstructionService instructionService = InstructionServiceUtil.getInstructionService(instruction);
            String message = instructionService.perform(parkingLot, parkingSpace);
            System.out.println(message);
        } catch (ParkingLotException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Pair<String, ParkingSpace> parseInstruction(String instructionText) {
        try {
            //get parsing instruction service based on type of instruction
            InstructionParsingService instructionParsingService =
                    InstructionParsingUtil.getInstructionParsingService(instructionText);
            return instructionParsingService.parseInstructionPerType(instructionText);
        }
        catch (ParkingLotException e) {
            //return dummy pair for which we will print error message on the console
            return Pair.of(instructionText, null);
        }
    }
}
