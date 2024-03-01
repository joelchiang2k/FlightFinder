package com.synergisticit.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Flight {
	
	@Id
	private Long flightId;
	
	private String flightNumber;
	
	@ManyToOne
	private Airline flightAirlines;
	
	private String flightDepartureCity;
	
	private String flightAirportCode;
	
	private String flightArrivalCity;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate flightDepartureDate;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime flightDepartureTime;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate flightArrivalDate;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime flightArrivalTime;
	
	private Integer flightCapacity;
	
	private Double flightPrice;
	
	private int flightSeatsBook;
	
	
}
