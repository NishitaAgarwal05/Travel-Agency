package com.travel_agency.model;

import java.util.List;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *Entity class for Travel Package 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "travel_package")
public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int passengerCapacity;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Destination> itinerary;
    @OneToMany
    private List<Passenger> passengers;

}
