package com.travel_agency;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;

@SpringBootApplication	
public class TravelAgency {
	/**
	 * Main class to run the application
	 * @param args
	 */

	public static void main(String[] args) {
		SpringApplication.run(TravelAgency.class, args);

	}
	
	 /**
	  * method to helping in mapping Entities to Data Transfer Objects and vice-versa
	  * @return ModelMapper
	  */
	 @Bean
	 public ModelMapper getModelMapper() { 
	        return new ModelMapper(); 
	 } 
}
