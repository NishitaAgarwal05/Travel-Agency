package com.travel_agency.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object for Travel Package, Itineary and Activity fields
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelPackageItinearyActivityDto {
    private String name;
    private String description;
    private double cost;
    private int capacity;
}