package com.demo.parkinglot.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ParkingSpace {

    private String registrationNumber;

    private Integer driverAge;

    private Integer parkingSlot;

}
