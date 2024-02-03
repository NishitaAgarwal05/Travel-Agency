package com.travel_agency.exception;

/**
 * Enum for exceptions related to Passenger
 */
public enum PassengerExceptionCode {
	PASSENGER_DOES_NOT_EXISTS("No passenger with the given passenger number found"),
	NO_SIGNED_UP_ACTIVITY("Passenger has not signed up for any activity");
	
	public final String codeMessage;
    private PassengerExceptionCode(String codeMessage) {
        this.codeMessage = codeMessage;
    }
}
