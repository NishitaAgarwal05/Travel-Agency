package com.travel_agency.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object for Passenger, Destination and Activity fields
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDestinationActivityCostDto {
	 private String name;
	 private int passengerNumber;
	 private double balance;
	 List<DestinationActivityCostDto> destinationActivityCostList;
}
