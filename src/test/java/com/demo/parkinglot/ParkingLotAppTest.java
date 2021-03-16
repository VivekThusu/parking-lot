package com.demo.parkinglot;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingLotAppTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() throws IOException {
        System.setOut(standardOut);
        outputStreamCaptor.close();
    }

    @Test
    @DisplayName("test to cover all instruction types")
    @SneakyThrows
    void mainTest() {
        String expectedOutput = "Created parking of 2 slots\n" +
                "Car with vehicle registration number \"KA-01-HH-1234\" has been parked at slot number 1\n" +
                "Car with vehicle registration number \"PB-01-HH-1234\" has been parked at slot number 2\n" +
                "ERROR : Parking Lot is full or vehicle already parked. You cannot park your vehicle\n" +
                "1,2\n" +
                "2\n" +
                "KA-01-HH-1234,PB-01-HH-1234\n" +
                "Slot number 2 vacated, the car with vehicle registration number \"PB-01-HH-1234\" left the space, the driver of the car was of age 25\n" +
                "Car with vehicle registration number \"JK-02-HH-1234\" has been parked at slot number 2";
        ParkingLotApp.main(null);
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
}