package com.synergisticit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.synergisticit.domain.Reservation;

@Service
public interface ReservationService {
	public Reservation save(Reservation reservation);
    public Reservation findById(Long reservationId);
    public List<Reservation> findAll();
    public void deleteById(Long reservationId);
    boolean existsById(Long reservationId);
}
