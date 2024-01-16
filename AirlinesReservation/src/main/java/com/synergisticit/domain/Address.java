package com.synergisticit.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	private String addressLine1;
	
	private String addressLine2;
	
	private String city;
	
	private String state;
	
	private String Country;
	
	private String zipcode;
}
