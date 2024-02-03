package com.travel_agency.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel_agency.Dto.DestinationActivityCostDto;
import com.travel_agency.Dto.PassengerDestinationActivityCostDto;
import com.travel_agency.exception.PassengerException;
import com.travel_agency.exception.PassengerExceptionCode;
import com.travel_agency.model.Activity;
import com.travel_agency.model.Passenger;
import com.travel_agency.model.PassengerType;
import com.travel_agency.repository.ActivityRepository;
import com.travel_agency.repository.DestinationRepository;
import com.travel_agency.repository.PassengerRepository;
import com.travel_agency.service.api.PassengerService;

/**
 * Service interface implementation for Passenger
 */
@Service
public class PassengerServiceImpl implements PassengerService{
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ActivityRepository activityRepository;
	
	@Autowired
	DestinationRepository destinationRepository;

	@Autowired
	ModelMapper modelMapper;
	
	private double calculatePrice(double cost, PassengerType passengerType) {
		if(passengerType.equals(PassengerType.GOLD)) {
			return 0.9*cost;
		} else if(passengerType.equals(PassengerType.STANDARD)){
			return cost;
		} else {
			return 0;
		}
	}
	
	/**
	 * Method to get the passenger details according to the passenger number, otherwise throw exception
	 * @param passengerNumber, number assigned to the passenger
	 * @return PassengerDestinationActivityCostDto
	 */
	@Override
	public PassengerDestinationActivityCostDto getPassengerDetails(int passengerNumber) {
		Passenger passenger = passengerRepository.findByPassengerNumber(passengerNumber);
		if(passenger != null) {
		List<Activity> activityList = activityRepository.findBySignedUpPassengersIdIn(passenger.getId());
		if(!activityList.isEmpty()) {
			List<DestinationActivityCostDto> destinationActivityCostDtoList = new ArrayList<DestinationActivityCostDto>();
			for(Activity activity: activityList) {
				String destinationName = destinationRepository.findByActivityId(activity.getId());
				double price = calculatePrice(activity.getCost(),passenger.getPassengerType());
				DestinationActivityCostDto destinationActivityCostDto = new DestinationActivityCostDto(destinationName, activity.getName(),price);
				destinationActivityCostDtoList.add(destinationActivityCostDto);
			}
			PassengerDestinationActivityCostDto passengerDestinationActivityCostDto = new PassengerDestinationActivityCostDto(passenger.getName(),passenger.getPassengerNumber(), passenger.getBalance(),destinationActivityCostDtoList);
			return passengerDestinationActivityCostDto;
		} else {
				throw new PassengerException(PassengerExceptionCode.NO_SIGNED_UP_ACTIVITY.codeMessage);
			}
		} else {
			throw new PassengerException(PassengerExceptionCode.PASSENGER_DOES_NOT_EXISTS.codeMessage);
		}

	}

}
