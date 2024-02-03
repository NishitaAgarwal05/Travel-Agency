package com.travel_agency.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.travel_agency.Dto.TravelPackageItinearyDto;
import com.travel_agency.Dto.TravelPackagePassengerDto;
import com.travel_agency.exception.TravelPackageException;
import com.travel_agency.model.Activity;
import com.travel_agency.model.Destination;
import com.travel_agency.model.Passenger;
import com.travel_agency.model.PassengerType;
import com.travel_agency.model.TravelPackage;
import com.travel_agency.repository.TravelPackageRepository;
import com.travel_agency.service.api.TravelPackageService;

/**
 * Test class for TravelPackageServiceImpl
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TravelPackageServiceImplTest {
	@Autowired
	private TravelPackageService travelPackageService;
	
	@MockBean
    private TravelPackageRepository travelPackageRepository;
	
	/**
	 * Test case for getting the travel package itineary with no destinations
	 */
	@Test
	public void testGetTravelPackageItineraryWithNoItineary() {
		//Mock Data
		TravelPackage travelPackage = new TravelPackage(1L, "Test Travel Package", 10, new ArrayList<>(), new ArrayList<>());
		when(travelPackageRepository.findByName(travelPackage.getName())).thenReturn(travelPackage);
		
		TravelPackageItinearyDto travelPackageItinearyDto= travelPackageService.getTravelPackageItinerary(travelPackage.getName());
		
		assertEquals(travelPackageItinearyDto.getName(), travelPackage.getName());
		assertTrue(travelPackageItinearyDto.getItinerary().isEmpty());
	}
	
	/**
	 * Test case for getting the travel package itineary with the travel package doesn't exists
	 */
	@Test
	public void testGetTravelPackageItineraryWhenTravelPackageDoesnotExists() {
		when(travelPackageRepository.findByName("Test Travel Package")).thenReturn(null);
		
		TravelPackageException exception = assertThrows(TravelPackageException.class, ()-> travelPackageService.getTravelPackageItinerary("Test Travel Package"));
		assertEquals(exception.getMessage(), "No travel package with the given name exists");
	}
	
	/**
	 * Test case for getting the travel package itineary with destinations
	 */
	@Test
	public void testGetTravelPackageItineraryWithItineary() {
		//Mock Data
		List<Activity>activityList = new ArrayList<>();
		activityList.add(new Activity());
		Destination destination = new Destination(1L, "Destination 1", activityList);
		List<Destination> destinationList = new ArrayList<>();
		destinationList.add(destination);
		TravelPackage travelPackage = new TravelPackage(1L, "Test Travel Package", 10, destinationList , new ArrayList<>());
		
		when(travelPackageRepository.findByName(travelPackage.getName())).thenReturn(travelPackage);
		
		TravelPackageItinearyDto travelPackageItinearyDto= travelPackageService.getTravelPackageItinerary(travelPackage.getName());
		
		assertEquals(travelPackageItinearyDto.getName(), travelPackage.getName());
		assertTrue(!travelPackageItinearyDto.getItinerary().isEmpty());
		assertEquals(travelPackageItinearyDto.getItinerary().size(),1);
		assertTrue(!travelPackageItinearyDto.getItinerary().get(0).getActivities().isEmpty());
		assertEquals(travelPackageItinearyDto.getItinerary().get(0).getActivities().size(),1);
		assertEquals(travelPackageItinearyDto.getItinerary().get(0).getName(),"Destination 1");
	}
	
	/**
	 * Test case for getting the travel package passengers with no passenger present
	 */
	@Test
	public void testGetTravelPackagePassengersWithNoPassenger() {
		//Mock Data
		TravelPackage travelPackage = new TravelPackage(1L, "Test Travel Package", 10, new ArrayList<>(), new ArrayList<>());
		when(travelPackageRepository.findByName(travelPackage.getName())).thenReturn(travelPackage);
		
		TravelPackagePassengerDto travelPackagePassengerDto = travelPackageService.getTravelPackagePassengers(travelPackage.getName());
		
		assertEquals(travelPackagePassengerDto.getName(), travelPackage.getName());
		assertTrue(travelPackagePassengerDto.getPassengers().isEmpty());
	}
	
	/**
	 * Test case for getting the travel package passengers with the travel package doesn't exists
	 */
	@Test
	public void testGetTravelPackagePassengerWhenTravelPackageDoesnotExists() {
		when(travelPackageRepository.findByName("Test Travel Package")).thenReturn(null);
		
		TravelPackageException exception = assertThrows(TravelPackageException.class, ()-> travelPackageService.getTravelPackagePassengers("Test Travel Package"));
		assertEquals(exception.getMessage(), "No travel package with the given name exists");
	}
	
	/**
	 * Test case for getting the travel package passengers 
	 */
	@Test
	public void testGetTravelPackageItineraryWithPassengers() {
		//Mock Data
		List<Activity>activityList = new ArrayList<>();
		activityList.add(new Activity());
		Passenger passenger = new Passenger(1L, "Passenger 1",1, PassengerType.GOLD, 100.0);
		List<Passenger> passengerList = new ArrayList<>();
		passengerList.add(passenger);
		TravelPackage travelPackage = new TravelPackage(1L, "Test Travel Package", 10, new ArrayList<>(),passengerList);
		
		when(travelPackageRepository.findByName(travelPackage.getName())).thenReturn(travelPackage);
		
		TravelPackagePassengerDto travelPackagePassengerDto = travelPackageService.getTravelPackagePassengers(travelPackage.getName());
		
		assertEquals(travelPackagePassengerDto.getName(), travelPackage.getName());
		assertTrue(!travelPackagePassengerDto.getPassengers().isEmpty());
		assertEquals(travelPackagePassengerDto.getPassengers().size(),1);
		assertEquals(travelPackagePassengerDto.getPassengers().get(0).getName(),passenger.getName() );
		assertEquals(travelPackagePassengerDto.getPassengers().get(0).getPassengerNumber(),passenger.getPassengerNumber());
		assertEquals(travelPackagePassengerDto.getPassengerCapacity(),travelPackage.getPassengerCapacity());
		assertEquals(travelPackagePassengerDto.getNumberOfPassengersEnrolled(),1);
	}
	
	
}
