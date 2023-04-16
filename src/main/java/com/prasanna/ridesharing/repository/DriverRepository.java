package com.prasanna.ridesharing.repository;

import com.prasanna.ridesharing.entity.Driver;
import com.prasanna.ridesharing.entity.Vehicle;
import lombok.NonNull;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverRepository {
    private Map<String, Driver> driverRepository;

    public DriverRepository() {
        this.driverRepository = new HashMap<>();
    }
    public void onBoardDriver(@NonNull final Driver driver) {
        this.driverRepository.put(driver.id, driver);
    }

    public List<Driver> fetchAllDrivers() {
        return new ArrayList<>(this.driverRepository.values());
    }
}

