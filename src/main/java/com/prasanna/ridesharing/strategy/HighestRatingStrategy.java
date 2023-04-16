package com.prasanna.ridesharing.strategy;

import com.prasanna.ridesharing.entity.Driver;
import com.prasanna.ridesharing.entity.Rider;
import com.prasanna.ridesharing.entity.Trip;
import com.prasanna.ridesharing.model.SelectionStrategy;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public class HighestRatingStrategy implements RideSelectionStrategy {
    private static final Double MAX_ALLOWED_PICKUP_DISTANCE = 30.00;
    private boolean isFullFillRiderRequest = true;
    private Double driverRating = 0.0;
    private Driver selectedDriverWithVehicle = null;

    @Override
    public Trip selectTrip(@NonNull List<Driver> driversWithVehicle, Rider riderRequest) {
        driversWithVehicle = driversWithVehicle.stream().filter(driver -> driver.getVehicle().isAvailability()).collect(Collectors.toList());
        for (Driver driver : driversWithVehicle) {
            isFullFillRiderRequest = true;
            if (driver.getVehicle().getCurrentLocation().distanceFrom(riderRequest.getCurrentLocation()) > 30.00) {
                isFullFillRiderRequest = false;
            }

            if (isFullFillRiderRequest && !driver.getRoutes().contains(riderRequest.getSourceLocation()) || !driver.getRoutes().contains(riderRequest.getDestination())) {
                isFullFillRiderRequest = false;
            }

            if (driverRating < driver.getRatings() && isFullFillRiderRequest) {
                driverRating = driver.getRatings();
                selectedDriverWithVehicle = driver;
            }
        }
        if (selectedDriverWithVehicle != null) {
            Trip trip = new Trip();
            trip.setDriver(selectedDriverWithVehicle);
            trip.setRider(riderRequest);
            return trip;
        }
        return null;
    }
}
