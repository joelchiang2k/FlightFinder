package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Airport;
import com.synergisticit.repository.AirportRepository;

@Service
public class AirportServiceImpl implements AirportService {
	
	@Autowired AirportRepository airportRepository;

	@Override
	public Airport save(Airport airport) {
		
		return airportRepository.save(airport);
	}

	@Override
	public Airport findById(Long airportId) {
		Optional<Airport> optAirport = airportRepository.findById(airportId);
		if(optAirport.isPresent()) {
			return optAirport.get();
		}
		return null;
	}

	@Override
	public List<Airport> findAll() {
		return airportRepository.findAll();
	}

	@Override
	public void deleteById(Long airportId) {
		airportRepository.deleteById(airportId);
	}

	@Override
	public boolean existsById(Long airportId) {
		return airportRepository.existsById(airportId);
	}

}
