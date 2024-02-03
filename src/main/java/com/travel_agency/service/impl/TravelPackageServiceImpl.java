package com.travel_agency.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.travel_agency.Dto.TravelPackageItinearyDto;
import com.travel_agency.Dto.TravelPackagePassengerDto;
import com.travel_agency.exception.TravelPackageException;
import com.travel_agency.exception.TravelPackageExceptionCode;
import com.travel_agency.model.TravelPackage;
import com.travel_agency.repository.TravelPackageRepository;
import com.travel_agency.service.api.TravelPackageService;
import org.springframework.stereotype.Service;

/**
 * Service interface implementation for Travel Package
 */
@Service
public class TravelPackageServiceImpl implements TravelPackageService {
	@Autowired
	TravelPackageRepository travelPackageRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	/**
	 * Method to get the itineary details according to the given package name, otherwise throw exception
	 * @param packageName, name of the package whose itineary details are needed
	 * @return TravelPackageItinearyDto
	 */
	public TravelPackageItinearyDto getTravelPackageItinerary(String packageName) {
		TravelPackage travelPackage= travelPackageRepository.findByName(packageName);
		if(travelPackage != null) {
			return this.modelMapper.map(travelPackage, TravelPackageItinearyDto.class);
		} else {
			throw new TravelPackageException(TravelPackageExceptionCode.TRAVEL_PACKAGE_DOESNOT_EXISTS.codeMessage);
		}
	}

	/**
	 * Method to get the passenger details according to the given package name, otherwise throw exception
	 * @param packageName, name of the package whose passenger details are needed
	 * @return TravelPackagePassengerDto
	 */
	@Override
	public TravelPackagePassengerDto getTravelPackagePassengers(String packageName) {
		TravelPackage travelPackage= travelPackageRepository.findByName(packageName);
		if(travelPackage != null) {
			TravelPackagePassengerDto travelPackagePassengerDto =  this.modelMapper.map(travelPackage,TravelPackagePassengerDto.class);
			travelPackagePassengerDto.setNumberOfPassengersEnrolled(travelPackagePassengerDto.getPassengers().size());
			return travelPackagePassengerDto;
		} else {
			throw new TravelPackageException(TravelPackageExceptionCode.TRAVEL_PACKAGE_DOESNOT_EXISTS.codeMessage);
		}
	}
}
