package com.prasanna.ridesharing.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Vehicle {

    private String id;
    private String driverName;
    private int numberOfSeats;
    private Location currentLocation;
    private VehicleType vehicleType;
    private Status vehicleStatus;
    private boolean availability;

}
