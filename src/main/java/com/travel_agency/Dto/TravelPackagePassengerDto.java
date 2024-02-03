package com.travel_agency.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object for Travel Package fileds
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelPackagePassengerDto {
	    private String name;
	    private int passengerCapacity;
	    private int numberOfPassengersEnrolled;
	    private List<PassengerDto> passengers;
}
