package com.synergisticit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synergisticit.domain.Airport;


@Service
public interface AirportService {
	public Airport save(Airport airport);
    public Airport findById(Long airportId);
    public List<Airport> findAll();
    public void deleteById(Long airportId);
    boolean existsById(Long airportId);
}
