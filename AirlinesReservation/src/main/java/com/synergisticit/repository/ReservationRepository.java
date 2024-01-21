package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergisticit.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
