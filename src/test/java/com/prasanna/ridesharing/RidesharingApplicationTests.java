package com.prasanna.ridesharing;

import com.prasanna.ridesharing.entity.*;
import com.prasanna.ridesharing.exception.NoMatchingTripFound;
import com.prasanna.ridesharing.model.SelectionStrategy;
import com.prasanna.ridesharing.repository.DriverRepository;
import com.prasanna.ridesharing.repository.VehicleRepository;
import com.prasanna.ridesharing.service.RideSharingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class RidesharingApplicationTests {
    RideSharingService rideSharingService;
    VehicleRepository vehicleRepository;
    DriverRepository driverRepository;
    Vehicle vehicle_1;
    Vehicle vehicle_2;
    Driver driver_1;
    Driver driver_2;
    Driver driver_3;

    @BeforeEach
    void contextLoads() {
        vehicleRepository = new VehicleRepository();
        driverRepository = new DriverRepository();
        rideSharingService = new RideSharingService(vehicleRepository, driverRepository);
        vehicle_1 = new Vehicle("1", "Driver_1", 10, new Location(122.2, 232.3), VehicleType.CAR, Status.IN_PROGRESS, true);
        vehicle_2 = new Vehicle("2", "Driver_2", 4, new Location(1.2, 1.3), VehicleType.AUTO_CYCLE, Status.IN_PROGRESS, true);
        driver_1 = new Driver("1", "Driver_1", 4.5, Arrays.asList("Madurai", "Trichy", "Chennai"));
        driver_2 = new Driver("2", "Driver_2", 3.7, Arrays.asList("Madurai", "Trichy", "Chennai"));
        driver_3 = new Driver("3", "Driver_3", 3.7, Arrays.asList("Madurai", "Trichy", "Chennai"));
        rideSharingService.onBoardDrivers(driver_1);
        rideSharingService.onBoardDrivers(driver_2);
        rideSharingService.onBoardDrivers(driver_3);
        rideSharingService.onBoardVehicles(vehicle_1);
        rideSharingService.onBoardVehicles(vehicle_2);
    }

    @Test
    void testOnboardVehicle() {
        Assertions.assertEquals(2, rideSharingService.fetchAllVehicles().size());
    }

    @Test
    void testDriverOffers() {
        Assertions.assertEquals(3, rideSharingService.fetchAllDrivers().size());
        Assertions.assertEquals(2, rideSharingService.fetchAllDriversWithVehicle().size());
    }

    @Test
    void testRideSelection() throws NoMatchingTripFound {
        Assertions.assertEquals("Driver_2", rideSharingService.shareRide(new Rider(SelectionStrategy.HIGH_RATING, "Madurai", "Chennai", new Location(1.2, 3.3))).getDriver().name);
    }

}
