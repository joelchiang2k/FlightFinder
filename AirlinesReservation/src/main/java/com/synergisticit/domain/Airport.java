package com.synergisticit.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Airport {
	@Id
	private Long airportId;
	
	private String airportCode;
	
	private String airportName;
	
	private String airportCity;
	
	@OneToMany
	private List<Flight> arrivalFlights = new ArrayList<>();
	
	@OneToMany
	private List<Flight> airportDepartureFlights = new ArrayList<>();
}
