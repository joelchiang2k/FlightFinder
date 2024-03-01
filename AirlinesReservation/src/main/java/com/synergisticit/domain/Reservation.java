package com.synergisticit.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
	@Id
	private Long reservationId; //ticket number
	
	@OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY)
	private List<Passenger> passengers;
	
	@OneToOne
	private Passenger passenger;
	
	@ManyToOne
	private Flight flight;
	
	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}


	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public int getCheckedBags() {
		return checkedBags;
	}

	public void setCheckedBags(int checkedBags) {
		this.checkedBags = checkedBags;
	}

	public String getCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(String checkedIn) {
		this.checkedIn = checkedIn;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	private int checkedBags;
	
	private String checkedIn;
	
	
	
	
}
