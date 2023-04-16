package com.prasanna.ridesharing.service;

import com.prasanna.ridesharing.entity.Driver;
import com.prasanna.ridesharing.entity.Rider;
import com.prasanna.ridesharing.entity.Trip;
import com.prasanna.ridesharing.entity.Vehicle;
import com.prasanna.ridesharing.exception.NoMatchingTripFound;
import com.prasanna.ridesharing.model.SelectionStrategy;
import com.prasanna.ridesharing.repository.DriverRepository;
import com.prasanna.ridesharing.repository.VehicleRepository;
import com.prasanna.ridesharing.strategy.HighestRatingStrategy;
import com.prasanna.ridesharing.strategy.RideSelectionStrategy;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RideSharingService {
    private VehicleRepository vehicleRepository;
    private DriverRepository driverRepository;
    private RideSelectionStrategy rideSelectionStrategy;

    public RideSharingService(@NonNull VehicleRepository _vehicleRepository, @NonNull DriverRepository _driverRepository) {
        this.vehicleRepository = _vehicleRepository;
        this.driverRepository = _driverRepository;
    }

    public void onBoardVehicles(@NonNull final Vehicle vehicle) {
        this.vehicleRepository.onBoardVehicle(vehicle);
    }

    public List<Vehicle> fetchAllVehicles() {
        return this.vehicleRepository.fetchAllVehicles();
    }

    public void onBoardDrivers(@NonNull final Driver driver) {
        this.driverRepository.onBoardDriver(driver);
    }

    public List<Driver> fetchAllDrivers() {
        return this.driverRepository.fetchAllDrivers();
    }

    public List<Driver> fetchAllDriversWithVehicle() {
        List<Driver> driversWithVehicle = new ArrayList<>();
        List<Driver> drivers = fetchAllDrivers();
        List<Vehicle> vehicles = fetchAllVehicles();
        for (Vehicle vehicle : vehicles) {
            Optional<Driver> driver = drivers.stream().filter(_driver -> Objects.isNull(_driver.getVehicle()) && _driver.getName().equals(vehicle.getDriverName())).findAny();
            driver.ifPresent(value -> value.setVehicle(vehicle));
            driver.ifPresent(driversWithVehicle::add);
        }
        return driversWithVehicle;
    }

    public Trip shareRide(@NonNull final Rider riderRequest) throws NoMatchingTripFound {
        if (riderRequest.getSelectionStrategy().equals(SelectionStrategy.HIGH_RATING)) {
            rideSelectionStrategy = new HighestRatingStrategy();
            Trip tripDetails = rideSelectionStrategy.selectTrip(fetchAllDriversWithVehicle(), riderRequest);
            if (tripDetails == null) {
                throw new NoMatchingTripFound();
            }
            return tripDetails;
        }
        return null;
    }
}
