package com.travel_agency.service.api;

import com.travel_agency.Dto.TravelPackageItinearyDto;
import com.travel_agency.Dto.TravelPackagePassengerDto;

/**
 * Service interface for Travel Package
 */
public interface TravelPackageService {
	public TravelPackageItinearyDto getTravelPackageItinerary(String packageName);

	public TravelPackagePassengerDto getTravelPackagePassengers(String packageName);
}
