package com.travel_agency.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object for Activity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDto {
    private String name;
    private String description;
    private double cost;
    private int capacity;
    private int spaceAvailable;
}
