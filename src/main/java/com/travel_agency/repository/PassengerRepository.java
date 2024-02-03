package com.travel_agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel_agency.model.Passenger;

/**
 *Repository for Passenger
 */
public interface PassengerRepository extends JpaRepository<Passenger, Long>{
	Passenger findByPassengerNumber(int passengerNumber);
}
