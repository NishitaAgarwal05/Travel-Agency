package com.travel_agency.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object for Passenger
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {
    private String name;
    private int passengerNumber;
    
}
