package com.demo.parkinglot.util;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.demo.parkinglot.exceptions.ParkingLotException;
import com.demo.parkinglot.services.parsing.InstructionParsingService;
import com.demo.parkinglot.services.parsing.impl.*;

import java.util.Objects;

import static com.demo.parkinglot.util.InstructionServiceUtil.*;

public class InstructionParsingUtil {

    public static final String CREATE_PARKING_LOT = "Create_parking_lot";
    public static final String CREATE_PARKING_LOT_REGEX = "Create_parking_lot \\d+";
    public static final String PARK_REGEX = "Park ([A-Z][A-Z]-\\d{2}-[A-Z][A-Z]-\\d{4}) driver_age \\d+";
    public static final String LEAVE_REGEX = "Leave \\d+";
    public static final String SLOT_NUMBERS_FOR_DRIVER_OF_AGE_REGEX = "Slot_numbers_for_driver_of_age \\d+";
    public static final String SLOT_NUMBER_FOR_CAR_WITH_NUMBER_REGEX = "Slot_number_for_car_with_number ([A-Z][A-Z]-\\d{2}-[A-Z][A-Z]-\\d{4})";
    public static final String VEHICLE_REGISTRATION_NUMBER_FOR_DRIVER_OF_AGE_REGEX = "Vehicle_registration_number_for_driver_of_age \\d+";

    private static final ImmutableMap<String, InstructionParsingService> instructionParsingServiceMap
            = ImmutableMap.<String, InstructionParsingService>builder()
            .put(PARK, new ParkInstructionParsingService())
            .put(SLOT_NUMBERS_FOR_DRIVER_OF_AGE, new SlotNumbersForDriverInstructionParsingService())
            .put(SLOT_NUMBER_FOR_CAR_WITH_NUMBER, new SlotNumberForCarInstructionParsingService())
            .put(LEAVE, new LeaveInstructionParsingService())
            .put(VEHICLE_REGISTRATION_NUMBER_FOR_DRIVER_OF_AGE, new VehicleRegNumberInstructionParsingService())
            .build();

    public static InstructionParsingService getInstructionParsingService(String instructionText)
            throws ParkingLotException {
        if(!Strings.isNullOrEmpty(instructionText)) {
            String[] inputInstructionTokens = instructionText.split(" ");
            InstructionParsingService instructionParsingService =
                    instructionParsingServiceMap.get(inputInstructionTokens[0]);
            if (Objects.nonNull(instructionParsingService)) {
                return instructionParsingService;
            }
        }
        throw new ParkingLotException("ERROR: Invalid instruction found - " + instructionText);
    }

    public static boolean containsParsingError(String regex, String inputLine) {
        return Strings.isNullOrEmpty(inputLine) || !inputLine.matches(regex);
    }

    private InstructionParsingUtil() {
    }
}
