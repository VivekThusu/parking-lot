package com.demo.parkinglot.model;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class ParkingLot {

    private Integer totalNumberOfParkingSpaces;

    private Map<Integer, ParkingSpace> slotToParkingSpaceMap;

    private Map<String, ParkingSpace> registrationNumToParkingSpaceMap;

    private Multimap<Integer, ParkingSpace> driverAgeToParkingSpaceMap;

    private PriorityQueue<Integer> availableParkingSlots;

    private void init(int numberOfParkingSpaces) {
        if(Objects.isNull(totalNumberOfParkingSpaces)) {
            totalNumberOfParkingSpaces = numberOfParkingSpaces;
        }
        if(Objects.isNull(slotToParkingSpaceMap)) {
            slotToParkingSpaceMap = new HashMap<>();
        }
        if(Objects.isNull(registrationNumToParkingSpaceMap)) {
            registrationNumToParkingSpaceMap = new HashMap<>();
        }
        if(Objects.isNull(driverAgeToParkingSpaceMap)) {
            driverAgeToParkingSpaceMap = ArrayListMultimap.create();
        }
        if(Objects.isNull(availableParkingSlots)) {
            availableParkingSlots = new PriorityQueue<>(numberOfParkingSpaces+1);
            for(int i = 1; i <= numberOfParkingSpaces; i++) {
                availableParkingSlots.add(i);
            }
        }
    }

    private static class InnerParkingLot {
        private static final ParkingLot parkingLot = new ParkingLot();
    }

    public static ParkingLot getInstance(int numberOfParkingSpaces) {
        ParkingLot parkingLot = InnerParkingLot.parkingLot;
        parkingLot.init(numberOfParkingSpaces);
        return parkingLot;
    }

    public static ParkingLot getTestInstance(int totalNumberOfParkingSpaces) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.init(totalNumberOfParkingSpaces);
        return parkingLot;
    }

    private ParkingLot() {
    }
}
