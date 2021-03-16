package com.demo.parkinglot;

import com.demo.parkinglot.services.impl.InstructionExecutorServiceImpl;

import java.io.IOException;

public class ParkingLotApp {

    public static void main(String[] args) throws IOException {
        ParkingLotManager parkingLotManager = new ParkingLotManager(new InstructionExecutorServiceImpl());
        String filePathFromArgs = null;
        if(args != null && args.length > 0) {
            filePathFromArgs = args[0];
        }
        parkingLotManager.manageParkingLot(filePathFromArgs);
    }
}
