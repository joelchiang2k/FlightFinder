package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Flight;
import com.synergisticit.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {
	
	@Autowired FlightRepository flightRepository;
	
	@Override
	public Flight save(Flight flight) {
		return flightRepository.save(flight);
	}

	@Override
	public Flight findById(Long flightId) {
		Optional<Flight> optFlight = flightRepository.findById(flightId);
		if(optFlight.isPresent()) {
			return optFlight.get();
		}
		return null;
	}

	@Override
	public List<Flight> findAll() {
		// TODO Auto-generated method stub
		return flightRepository.findAll();
	}

	@Override
	public void deleteById(Long flightId) {
		// TODO Auto-generated method stub
		flightRepository.deleteById(flightId);
	}

	@Override
	public boolean existsById(Long flightId) {
		// TODO Auto-generated method stub
		return flightRepository.existsById(flightId);
	}
	
	@Override
	public List<Flight> findByCode(String airportCode){
		List<Flight> flights = flightRepository.findByFlightAirportCode(airportCode);
		return flights;
	}

}
