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
import com.synergisticit.domain.Role;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

@Component
public class AirlineValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Airline.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Airline airline = (Airline)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airlineId", "airlineId.empty", "Airline Id should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airlineName", "airlineName.empty", "Airline Name should not be empty");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airlineCode", "airlineCode.empty", "Airline Code should not be empty");	

	}

}
