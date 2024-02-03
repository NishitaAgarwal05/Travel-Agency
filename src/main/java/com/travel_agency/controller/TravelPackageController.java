package com.travel_agency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.travel_agency.Dto.TravelPackageItinearyDto;
import com.travel_agency.Dto.TravelPackagePassengerDto;
import com.travel_agency.exception.ActivityException;
import com.travel_agency.exception.ActivityExceptionCode;
import com.travel_agency.exception.ErrorResponse;
import com.travel_agency.exception.TravelPackageException;
import com.travel_agency.exception.TravelPackageExceptionCode;
import com.travel_agency.service.api.TravelPackageService;

/**
 * Controller class for travel-packages
 */
@RestController
@RequestMapping("api/v1/travel-packages")
public class TravelPackageController {
	@Autowired
	TravelPackageService travelPackageService;

	/**
	 * REST endpoint to get the itineary details that are there in the given travel package
	 * @param packageName, name of the travel package
	 * @return ResponseEntity<TravelPackageItinearyDto>
	 */
	@GetMapping("/{packageName}/itineary")
	ResponseEntity<TravelPackageItinearyDto> getItineary(@PathVariable String packageName){
		TravelPackageItinearyDto travelPackageDto= travelPackageService.getTravelPackageItinerary(packageName);
		return new ResponseEntity<TravelPackageItinearyDto>(travelPackageDto, HttpStatus.OK);
   }
	
	/**
	 * REST endpoint to get the passengers details that are there in the given travel package
	 * @param packageName, name of the travel package
	 * @return ResponseEntity<TravelPackagePassengerDto>
	 */
	@GetMapping("/{packageName}/passengers")
	ResponseEntity<TravelPackagePassengerDto> getPassengers(@PathVariable String packageName){
		TravelPackagePassengerDto travelPackagePassengersDto= travelPackageService.getTravelPackagePassengers(packageName);
		return new ResponseEntity<TravelPackagePassengerDto>(travelPackagePassengersDto, HttpStatus.OK);
   }
	
	/**
	 * Mapping exception to response entity
	 * @param e, exception that will be thrown and belongs to TravelPackageException class
	 * @return ErrorResponse
	 */
	@ExceptionHandler(TravelPackageException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUnexpectedErrors(TravelPackageException e) {
		ErrorResponse er = new ErrorResponse();
		er.setStatusCode(TravelPackageExceptionCode.TRAVEL_PACKAGE_DOESNOT_EXISTS.toString());
		er.setMessage(e.getMessage());
        return er;
    }
}
