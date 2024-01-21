package com.synergisticit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synergisticit.domain.Airline;
import com.synergisticit.domain.Airport;


@Service
public interface AirlineService {
	public Airline save(Airline airline);
    public Airline findById(Long airlineId);
    public List<Airline> findAll();
    public void deleteById(Long airlineId);
    boolean existsById(Long airlineId);
}
