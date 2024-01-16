package com.synergisticit.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
	@Id
	private Long reservationId; //ticket number
	
	@OneToOne
	private Passenger passenger;
	
	@OneToOne
	private Flight flight;
	
	private int checkedBags;
	
	private boolean checkedIn;
	
	
	
	
}
