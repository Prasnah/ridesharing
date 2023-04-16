package com.prasanna.ridesharing.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
public class Driver extends User {
    @Setter
    private Vehicle vehicle;
    private List<String> routes;
    private Double ratings;

    public Driver(String id,String _name, Double _ratings, List<String> _routes) {
        this.id = id;
        this.name = _name;
        this.ratings = _ratings;
        this.routes = _routes;
    }
}
