package com.travel_agency.service.api;

import java.util.List;

import com.travel_agency.Dto.ActivityDto;

/**
 * Service interface for Activity
 */
public interface ActivityService {
	List<ActivityDto> getAllActivitiesWithAvailableSpace();
}
