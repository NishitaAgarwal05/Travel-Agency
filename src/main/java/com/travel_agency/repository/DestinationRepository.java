package com.travel_agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel_agency.model.Destination;

/**
 *Repository for Destination
 */
public interface DestinationRepository extends JpaRepository<Destination, Long>{
	 @Query(value = "SELECT d.name FROM Destination d JOIN d.activities a WHERE a.id = :activityId")
	 String findByActivityId(Long activityId);

}
