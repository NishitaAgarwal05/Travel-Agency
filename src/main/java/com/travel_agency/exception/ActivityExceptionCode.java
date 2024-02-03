package com.travel_agency.exception;

/**
 * Enum for exceptions related to Activity
 */
public enum ActivityExceptionCode {
		
	NO_ACTIVITIES_WITH_AVAILABLE_SPACE("All activities have reached their maximum capacity");
	
	public final String codeMessage;
    private ActivityExceptionCode(String codeMessage) {
        this.codeMessage = codeMessage;
    }
}
