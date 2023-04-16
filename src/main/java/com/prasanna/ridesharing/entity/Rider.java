package com.prasanna.ridesharing.entity;

import com.prasanna.ridesharing.model.SelectionStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Rider extends User {
    private SelectionStrategy selectionStrategy;
    private String sourceLocation;
    private String Destination;
    private Location currentLocation;
}
