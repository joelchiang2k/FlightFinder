package com.synergisticit.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Airlines {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long airlinesId;
	
	private String airlinesName;
	
	public String airlinesCode; 
	
	@OneToMany
	private List<Flight> airlinesFlights = new ArrayList<>();
	
}
