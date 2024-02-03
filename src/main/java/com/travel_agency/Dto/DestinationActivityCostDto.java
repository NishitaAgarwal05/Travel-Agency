package com.travel_agency.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object for Destination, Activity and Cost fields
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationActivityCostDto {
	private String name;
	private String activityName;
	private double price;
}
