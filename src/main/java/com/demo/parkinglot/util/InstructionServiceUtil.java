package com.demo.parkinglot.util;

import com.google.common.collect.ImmutableMap;
import com.demo.parkinglot.exceptions.ParkingLotException;
import com.demo.parkinglot.services.instruction.InstructionService;
import com.demo.parkinglot.services.instruction.impl.*;

import java.util.Objects;


public final class InstructionServiceUtil {

    public static final String PARK = "Park";
    public static final String SLOT_NUMBERS_FOR_DRIVER_OF_AGE = "Slot_numbers_for_driver_of_age";
    public static final String SLOT_NUMBER_FOR_CAR_WITH_NUMBER = "Slot_number_for_car_with_number";
    public static final String LEAVE = "Leave";
    public static final String VEHICLE_REGISTRATION_NUMBER_FOR_DRIVER_OF_AGE = "Vehicle_registration_number_for_driver_of_age";

    private static final ImmutableMap<String, InstructionService> instructionServiceMap
            = ImmutableMap.<String, InstructionService>builder()
            .put(PARK, new ParkInstruction())
            .put(SLOT_NUMBERS_FOR_DRIVER_OF_AGE, new SlotNumbersForDriverInstruction())
            .put(SLOT_NUMBER_FOR_CAR_WITH_NUMBER, new SlotNumberForCarInstruction())
            .put(LEAVE, new LeaveInstruction())
            .put(VEHICLE_REGISTRATION_NUMBER_FOR_DRIVER_OF_AGE, new VehicleRegNumberInstruction())
            .build();

    public static InstructionService getInstructionService(String instruction) throws ParkingLotException {
        InstructionService instructionService = instructionServiceMap.get(instruction);
        if(Objects.nonNull(instructionService)) {
            return instructionService;
        }
        throw new ParkingLotException("ERROR: Invalid instruction found - " + instruction);
    }

    private InstructionServiceUtil() {

    }
}
