package com.synergisticit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergisticit.domain.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	List<Flight> findByFlightAirportCode(String flightAirportCode);
}
