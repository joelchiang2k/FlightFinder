package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.synergisticit.domain.Reservation;
import com.synergisticit.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired ReservationRepository reservationRepository;
	
	@Override
	public Reservation save(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@Override
	public Reservation findById(Long reservationId) {
		Optional<Reservation> optReservation = reservationRepository.findById(reservationId);
		if(optReservation.isPresent()) {
			return optReservation.get();
		}
		return null;
	}

	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	@Override
	public void deleteById(Long reservationId) {
		reservationRepository.deleteById(reservationId);
	}

	@Override
	public boolean existsById(Long reservationId) {
		return reservationRepository.existsById(reservationId);
	}

}
