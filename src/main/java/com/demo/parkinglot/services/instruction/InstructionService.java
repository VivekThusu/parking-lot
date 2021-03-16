package com.demo.parkinglot.services.instruction;

import com.demo.parkinglot.model.ParkingLot;
import com.demo.parkinglot.model.ParkingSpace;

public interface InstructionService {

    String perform(ParkingLot parkingLot, ParkingSpace parkingSpace);
}
