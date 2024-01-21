package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Airline;
import com.synergisticit.repository.AirlineRepository;


@Service
public class AirlineServiceImpl implements AirlineService {
	
	@Autowired AirlineRepository airlineRepository;
	
	@Override
	public Airline save(Airline airline) {
		// TODO Auto-generated method stub
		return airlineRepository.save(airline);
	}

	@Override
	public Airline findById(Long airlineId) {
		Optional<Airline> optAirline= airlineRepository.findById(airlineId);
		if(optAirline.isPresent()) {
			return optAirline.get();
		}
		return null;
	}

	@Override
	public List<Airline> findAll() {
		// TODO Auto-generated method stub
		return airlineRepository.findAll();
	}

	@Override
	public void deleteById(Long airlineId) {
		// TODO Auto-generated method stub
		airlineRepository.deleteById(airlineId);
	}

	@Override
	public boolean existsById(Long airlineId) {
		// TODO Auto-generated method stub
		return airlineRepository.existsById(airlineId);
	}

}
