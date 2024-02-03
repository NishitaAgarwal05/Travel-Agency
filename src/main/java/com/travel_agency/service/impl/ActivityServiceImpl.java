package com.travel_agency.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.travel_agency.Dto.ActivityDto;
import com.travel_agency.exception.ActivityException;
import com.travel_agency.exception.ActivityExceptionCode;
import com.travel_agency.model.Activity;
import com.travel_agency.repository.ActivityRepository;
import com.travel_agency.service.api.ActivityService;

/**
 * Service interface implementation for Activity
 */
@Service
public class ActivityServiceImpl implements ActivityService{
	
	@Autowired
	ActivityRepository activityRepository;
	
	@Autowired
	ModelMapper modelMapper;
	

	/**
	 * Method to get the list of the activites that have available space, otherwise throw exception
	 * @return List<ActivityDto>
	 */
	public List<ActivityDto> getAllActivitiesWithAvailableSpace() {
		 List<Activity> activityList = activityRepository.findAll();
		 boolean noActivityWithSpaceAvailable = true;
	        List<ActivityDto> activityListWithSpaceAvailable = new ArrayList<ActivityDto>();
	        for(Activity activity: activityList){
	        	int spaceAvailable = activity.getCapacity()-activity.getSignedUpPassengers().size();
	            if(activity.getCapacity()-activity.getSignedUpPassengers().size()>0){
	            	ActivityDto activityDto = this.modelMapper.map(activity,ActivityDto.class);
	            	activityDto.setSpaceAvailable(spaceAvailable);
	                activityListWithSpaceAvailable.add(activityDto);
	                noActivityWithSpaceAvailable= false;
	            	}
	        }
	        if(noActivityWithSpaceAvailable) {
	        	throw new ActivityException(ActivityExceptionCode.NO_ACTIVITIES_WITH_AVAILABLE_SPACE.codeMessage);
	        }
	        return  activityListWithSpaceAvailable; 
	}
}
