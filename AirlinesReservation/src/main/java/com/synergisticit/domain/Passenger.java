package com.synergisticit.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
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
	private Long passengerId;
	
	private String passengerFirstName;
	
	private String passengerLastName;
	
	private String passengerEmail;
	
	private String passengerPhoneNo;
	
	@Enumerated(EnumType.STRING)
	private Gender passengerGender;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate passengerDOB;
	
	@Embedded
	private Address passengerAddress;
	
	
}
