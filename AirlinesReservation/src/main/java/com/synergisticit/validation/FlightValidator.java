package com.synergisticit.validation;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Airline;
import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Role;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

@Component
public class FlightValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Flight.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Flight flight = (Flight)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "flightId", "flightId.empty", "Flight Id should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "flightAirportCode", "flightAirportCode.empty", "Flight Airport Code should not be empty");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "flightArrivalCity", "flightArrivalCity.empty", "Flight Arrival City should not be empty");			
		
		if(flight.getFlightArrivalDate() == null) {
			errors.rejectValue("flightArrivalDate", "flightArrivalDate.value", "Select Date.");
		}
		
		if(flight.getFlightArrivalTime() == null) {
			errors.rejectValue("flightArrivalTime", "flightArrivalTime.value", "Select Time.");
		}
		
		if(flight.getFlightCapacity() == null) {
			errors.rejectValue("flightCapacity", "flightCapacity.value", "Enter flight capacity.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "flightDepartureCity", "flightDepartureCity.empty", "Flight Departure City should not be empty");	
		
		if(flight.getFlightDepartureDate() == null) {
			errors.rejectValue("flightDepartureDate", "flightDepartureDate.value", "Select Date.");
		}
		
		if(flight.getFlightDepartureTime() == null) {
			errors.rejectValue("flightDepartureTime", "flightDepartureTime.value", "Select Time.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "flightNumber", "flightNumber.empty", "Flight Number should not be empty");			

		if(flight.getFlightPrice() == null) {
			errors.rejectValue("flightPrice", "flightPrice.value", "Enter flight price.");
		}
		
	}

}
