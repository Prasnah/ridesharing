package com.prasanna.ridesharing.strategy;

import com.prasanna.ridesharing.entity.Driver;
import com.prasanna.ridesharing.entity.Rider;
import com.prasanna.ridesharing.entity.Trip;
import lombok.NonNull;

import java.util.List;

public interface RideSelectionStrategy {

    public Trip selectTrip(@NonNull List<Driver> driversWithVehicle, Rider riderRequest);
}
