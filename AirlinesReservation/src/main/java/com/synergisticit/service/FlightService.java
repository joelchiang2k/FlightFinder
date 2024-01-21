package com.synergisticit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synergisticit.domain.Flight;

@Service
public interface FlightService {
	public Flight save(Flight flight);
    public Flight findById(Long flightId);
    public List<Flight> findByCode(String airportCode);
    public List<Flight> findAll();
    public void deleteById(Long flightId);
    boolean existsById(Long flightId);
}
