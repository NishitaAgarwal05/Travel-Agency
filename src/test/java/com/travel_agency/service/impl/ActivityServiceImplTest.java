package com.travel_agency.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.travel_agency.Dto.ActivityDto;
import com.travel_agency.exception.ActivityException;
import com.travel_agency.model.Activity;
import com.travel_agency.model.Passenger;
import com.travel_agency.repository.ActivityRepository;
import com.travel_agency.service.api.ActivityService;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for ActivityServiceImpl
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ActivityServiceImplTest {

		 @Autowired
		 private ActivityService activityService;

	    @MockBean
	    private ActivityRepository activityRepository;
	    
	    /*
	     * Test case when there are activities with available space present.
	     */
	    @Test
	    public void testGetAllActivitiesWithAvailableSpace() {
	        // Mock data
	        Activity activity1 = new Activity();
	        activity1.setId(1L);
	        activity1.setName("Activity 1");
	        activity1.setCapacity(10);
	        activity1.setSignedUpPassengers(new ArrayList<>()); // No signed-up passengers

	        Activity activity2 = new Activity();
	        activity2.setId(2L);
	        activity2.setName("Activity 2");
	        activity2.setCapacity(1);
	        List<Passenger> signedUpPassengers = new ArrayList<>();
	        signedUpPassengers.add(new Passenger());
	        activity2.setSignedUpPassengers(signedUpPassengers);
	        
	        List<Activity> activities = new ArrayList<>();
	        activities.add(activity1);
	        activities.add(activity2);

	        // Mock repository behavior
	        when(activityRepository.findAll()).thenReturn(activities);

	        // Call the service method
	        List<ActivityDto> result = activityService.getAllActivitiesWithAvailableSpace();

	        // Assertions
	        assertEquals(1, result.size());
	        assertEquals(activity1.getName(), result.get(0).getName());
	    }
	    
	    /*
	     * Test case when there are no activities with available space present.
	     */
	    @Test
	    public void testGetAllActivitiesWithNoAvailableSpace() {
	        // Mock data
	        Activity activity1 = new Activity();
	        activity1.setId(1L);
	        activity1.setName("Activity 1");
	        activity1.setCapacity(1);
	        List<Passenger> signedUpPassengers = new ArrayList<>();
	        signedUpPassengers.add(new Passenger());
	        activity1.setSignedUpPassengers(signedUpPassengers);
	        
	        List<Activity> activities = new ArrayList<>();
	        activities.add(activity1);

	        // Mock repository behavior
	        when(activityRepository.findAll()).thenReturn(activities);

	        ActivityException exception = assertThrows(ActivityException.class, ()-> activityService.getAllActivitiesWithAvailableSpace());
	        assertEquals(exception.getMessage(), "All activities have reached their maximum capacity");
	    }
}
