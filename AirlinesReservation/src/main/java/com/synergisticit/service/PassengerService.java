package com.synergisticit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.synergisticit.domain.Passenger;

@Service
public interface PassengerService {
	public Passenger save(Passenger passenger);
    public Passenger findById(Long passengerId);
    public List<Passenger> findAll();
    public void deleteById(Long passengerId);
    boolean existsById(Long passengerId);
}
