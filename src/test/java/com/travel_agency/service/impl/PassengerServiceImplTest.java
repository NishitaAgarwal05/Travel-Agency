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

import com.travel_agency.Dto.PassengerDestinationActivityCostDto;
import com.travel_agency.exception.PassengerException;
import com.travel_agency.model.Activity;
import com.travel_agency.model.Passenger;
import com.travel_agency.model.PassengerType;
import com.travel_agency.repository.ActivityRepository;
import com.travel_agency.repository.DestinationRepository;
import com.travel_agency.repository.PassengerRepository;
import com.travel_agency.service.api.PassengerService;

/**
 * Test class for PassengerServiceImpl
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PassengerServiceImplTest {
	
	@Autowired
	private PassengerService passengerService;
	
	@MockBean
    private PassengerRepository passengerRepository;
	
	@MockBean
    private ActivityRepository activityRepository;
	
	@MockBean
	private DestinationRepository destinationRepository;
	
	/**
	 * Test case to the the passenger details when passsenger exists and has signed up for some activities
	 */
	@Test
	public void testGetPassengerDetails() {
		//Mock data
		Passenger p = new Passenger(1L, "Passenger 1", 1, PassengerType.GOLD, 1000.0);
		Activity activity1 = new Activity();
        activity1.setId(1L);
        activity1.setName("Activity 1");
        activity1.setCapacity(10);
        activity1.setSignedUpPassengers(List.of(p));
        
		List<Activity> activityList = new ArrayList<>();
		activityList.add(activity1);
        
		when(passengerRepository.findByPassengerNumber(1)).thenReturn(p);
		when(activityRepository.findBySignedUpPassengersIdIn(1L)).thenReturn(activityList);
		when(destinationRepository.findByActivityId(activity1.getId())).thenReturn("Destination 1");
		
		PassengerDestinationActivityCostDto passengerDestinationActivityCostDto = passengerService.getPassengerDetails(p.getPassengerNumber());
		
		assertEquals(passengerDestinationActivityCostDto.getName(), p.getName());
		assertEquals(passengerDestinationActivityCostDto.getPassengerNumber(), p.getPassengerNumber());
		assertEquals(passengerDestinationActivityCostDto.getBalance(),p.getBalance(),1.00);
		assertTrue(!passengerDestinationActivityCostDto.getDestinationActivityCostList().isEmpty());
		assertEquals(passengerDestinationActivityCostDto.getDestinationActivityCostList().size(),1);
		assertEquals(passengerDestinationActivityCostDto.getDestinationActivityCostList().get(0).getActivityName(),activity1.getName());
		assertEquals(passengerDestinationActivityCostDto.getDestinationActivityCostList().get(0).getName(),"Destination 1");
		assertEquals(passengerDestinationActivityCostDto.getDestinationActivityCostList().get(0).getPrice(),0.9*activity1.getCost(),1.00);
	}
	
	/**
	 * Test class to the the passenger details when passsenger doesn't exists
	 */
	@Test
	public void testGetPassengerDetailsWhenPassengerDoesnotExists() {
		when(passengerRepository.findByPassengerNumber(1)).thenReturn(null);
		PassengerException exception = assertThrows(PassengerException.class,()-> passengerService.getPassengerDetails(1));
		assertEquals(exception.getMessage(), "No passenger with the given passenger number found");
	}
	
	/**
	 * Test class to the the passenger details when passsenger exists but has not isgned up for any activities
	 */
	@Test
	public void testGetPassengerDetailsWithNoSignedUpActivities() {
		Passenger p = new Passenger(1L, "Passenger 1", 1, PassengerType.GOLD, 1000.0);
		
		when(passengerRepository.findByPassengerNumber(1)).thenReturn(p);
		when(activityRepository.findBySignedUpPassengersIdIn(1L)).thenReturn(new ArrayList<>());
		PassengerException exception = assertThrows(PassengerException.class,()-> passengerService.getPassengerDetails(1));
		assertEquals(exception.getMessage(), "Passenger has not signed up for any activity");
	}

}
