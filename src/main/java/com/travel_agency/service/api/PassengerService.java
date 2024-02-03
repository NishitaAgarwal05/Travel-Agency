package com.travel_agency.service.api;

import com.travel_agency.Dto.PassengerDestinationActivityCostDto;

/**
 * Service interface for Passenger
 */
public interface PassengerService {
	PassengerDestinationActivityCostDto getPassengerDetails(int passengerNumber);
}
