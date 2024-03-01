package com.synergisticit.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Passenger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long passengerId;
	
	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", passengerFirstName=" + passengerFirstName
				+ ", passengerLastName=" + passengerLastName + ", passengerEmail=" + passengerEmail
				+ ", passengerPhoneNo=" + passengerPhoneNo + ", reservation=" + reservation + ", passengerGender="
				+ passengerGender + ", passengerDOB=" + passengerDOB + ", passengerAddress=" + passengerAddress + "]";
	}

	private String passengerFirstName;
	
	private String passengerLastName;
	
	private String passengerEmail;
	
	private String passengerPhoneNo;
	
	@ManyToOne
	private Flight flight; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reservation_id")
    private Reservation reservation;
	
	@Enumerated(EnumType.STRING)
	private Gender passengerGender;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate passengerDOB;
	
	@Embedded
	private Address passengerAddress;
	
	
}
