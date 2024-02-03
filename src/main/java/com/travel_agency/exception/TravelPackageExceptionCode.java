package com.travel_agency.exception;

/**
 * Enum for exceptions related to Travel Package
 */
public enum TravelPackageExceptionCode {
	TRAVEL_PACKAGE_DOESNOT_EXISTS("No travel package with the given name exists");
	
	public final String codeMessage;
    private TravelPackageExceptionCode(String codeMessage) {
        this.codeMessage = codeMessage;
    }

}
