package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.synergisticit.domain.Passenger;
import com.synergisticit.repository.PassengerRepository;

@Service
public class PassengerServiceImpl implements PassengerService {
	
	@Autowired PassengerRepository passengerRepository;
	
	@Override
	public Passenger save(Passenger passenger) {
		return passengerRepository.save(passenger);
	}

	@Override
	public Passenger findById(Long passengerId) {
		Optional<Passenger> optPassenger = passengerRepository.findById(passengerId);
		if(optPassenger.isPresent()) {
			return optPassenger.get();
		}
		return null;
	}

	@Override
	public List<Passenger> findAll() {
		return passengerRepository.findAll();
	}

	@Override
	public void deleteById(Long passengerId) {
		passengerRepository.deleteById(passengerId);
	}

	@Override
	public boolean existsById(Long passengerId) {
		return passengerRepository.existsById(passengerId);
	}

}
