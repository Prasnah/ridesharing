package com.prasanna.ridesharing.entity;

public class Location {
    private Double x_ordinate;
    private Double y_ordinate;

    public Location(Double x_ordinate, Double y_ordinate) {
        this.x_ordinate = x_ordinate;
        this.y_ordinate = y_ordinate;
    }

    public Double distanceFrom(Location l2) {
        return Math.sqrt(Math.pow(this.x_ordinate - l2.x_ordinate, 2) + Math.pow(this.y_ordinate - l2.y_ordinate, 2));
    }
}
