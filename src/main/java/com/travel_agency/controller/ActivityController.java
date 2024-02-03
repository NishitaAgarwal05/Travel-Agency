package com.travel_agency.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.travel_agency.Dto.ActivityDto;
import com.travel_agency.exception.ActivityException;
import com.travel_agency.exception.ActivityExceptionCode;
import com.travel_agency.exception.ErrorResponse;
import com.travel_agency.service.api.ActivityService;

/**
 * Controller class for activity
 */
@RestController
@RequestMapping("api/v1/activity")
public class ActivityController {
	
	@Autowired
	ActivityService activityService;
	
	/**
	 * REST endpoint to get the activities that have space available
	 * @return ResponseEntity<List<ActivityDto>>
	 */
	@GetMapping("/spaceAvailableActivities")
	ResponseEntity<List<ActivityDto>> getAllActivitiesWithAvailableSpace(){
		List<ActivityDto> activityList;
			activityList = activityService.getAllActivitiesWithAvailableSpace();
			return new ResponseEntity<List<ActivityDto>>(activityList, HttpStatus.OK);
   }
	
	/**
	 * Mapping exception to response entity
	 * @param e, exception that will be thrown and belongs to ActivityException class
	 * @return ErrorResponse
	 */
	@ExceptionHandler(ActivityException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@SuppressWarnings("rawtypes")
    public ErrorResponse handleUnexpectedErrors(ActivityException e) {
		ErrorResponse er = new ErrorResponse();
		er.setStatusCode(ActivityExceptionCode.NO_ACTIVITIES_WITH_AVAILABLE_SPACE.toString());
		er.setMessage(e.getMessage());
        return er;
    }
}
