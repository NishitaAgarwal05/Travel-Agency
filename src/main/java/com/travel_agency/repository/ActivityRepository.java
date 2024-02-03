package com.travel_agency.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel_agency.model.Activity;

/**
 *Repository for Activity
 */
public interface ActivityRepository extends JpaRepository<Activity,Long> {
	@Query("SELECT a FROM Activity a JOIN a.signedUpPassengers p WHERE p.id = :id")
    List<Activity> findBySignedUpPassengersIdIn(Long id);
}
