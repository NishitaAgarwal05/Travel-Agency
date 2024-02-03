package com.travel_agency.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object for Travel Package fields
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelPackageItinearyDto {
	    private String name;
	    private List<DestinationDto> itinerary;
	   
}
