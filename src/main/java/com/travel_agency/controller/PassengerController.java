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

import com.travel_agency.Dto.PassengerDestinationActivityCostDto;
import com.travel_agency.exception.ActivityException;
import com.travel_agency.exception.ActivityExceptionCode;
import com.travel_agency.exception.ErrorResponse;
import com.travel_agency.exception.PassengerException;
import com.travel_agency.exception.PassengerExceptionCode;
import com.travel_agency.service.api.PassengerService;

/**
 * Controller class for passenger
 */
@RestController
@RequestMapping("api/v1/passenger")
public class PassengerController {
	@Autowired
	PassengerService passengerService;
	
	/**
	 * REST endpoint to get the details of the passenger according to the passengerNumber
	 * @param passengerNumber, number assigned to the passenger
	 * @return ResponseEntity<PassengerDestinationActivityCostDto>
	 */
	@GetMapping("/{passengerNumber}")
	ResponseEntity<PassengerDestinationActivityCostDto> getPassengerDetails(@PathVariable int passengerNumber ){
		PassengerDestinationActivityCostDto passengerDestinationActivityCostDto = passengerService.getPassengerDetails(passengerNumber);
		return new ResponseEntity<PassengerDestinationActivityCostDto>(passengerDestinationActivityCostDto, HttpStatus.OK);
   }
	
	/**
	 * Mapping exception to response entity
	 * @param e, exception that will be thrown and belongs to PassengerException class
	 * @return ErrorResponse
	 */
	@ExceptionHandler(PassengerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUnexpectedErrors(PassengerException e) {
		ErrorResponse er = new ErrorResponse();
		if(e.getMessage().equalsIgnoreCase(PassengerExceptionCode.NO_SIGNED_UP_ACTIVITY.codeMessage)) {
			er.setStatusCode(PassengerExceptionCode.NO_SIGNED_UP_ACTIVITY.toString());
		} else {
			er.setStatusCode(PassengerExceptionCode.PASSENGER_DOES_NOT_EXISTS.toString());
		}
		er.setMessage(e.getMessage());
        return er;
    }
}
