package com.travel_agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel_agency.model.TravelPackage;

/**
 *Repository for Travel Package
 */
public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {
	TravelPackage findByName(String name);
}
