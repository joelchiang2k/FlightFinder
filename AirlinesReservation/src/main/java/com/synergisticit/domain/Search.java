package com.synergisticit.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Search {
	@Id
	private Long searchFlightId;
	
	private String searchFlightDepartureCity;
	private String searchFlightArrivalCity;
	
	private LocalDateTime searchFromDateTime;
	private LocalDateTime searchToDateTime;
	
}
