package com.travel_agency.Dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object for Destination
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationDto {
	private String name;
	private List<TravelPackageItinearyActivityDto> activities;
}
