package com.prasanna.ridesharing.repository;

import com.prasanna.ridesharing.entity.Driver;
import com.prasanna.ridesharing.entity.Vehicle;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VehicleRepository {
    private Map<String, Vehicle> vehicleRepository;

    public VehicleRepository() {
        vehicleRepository = new HashMap<>();
    }

    public void onBoardVehicle(@NonNull final Vehicle vehicle) {
        this.vehicleRepository.put(vehicle.getId(), vehicle);
    }

    public List<Vehicle> fetchAllVehicles() {
        return new ArrayList<>(this.vehicleRepository.values());
    }

//    public void onBoardDriver(@NonNull final Driver driver) {
//        this.vehicleRepository.put(vehicle.getId(), vehicle);
//    }
//
//    public List<Driver> fetchAllVehicles() {
//        return new ArrayList<>(this.vehicleRepository.values());
//    }
}
