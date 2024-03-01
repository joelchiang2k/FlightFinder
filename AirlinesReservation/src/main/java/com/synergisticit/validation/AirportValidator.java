package com.synergisticit.validation;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Role;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

@Component
public class AirportValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Airport.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Airport airport = (Airport)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airportId", "airportId.empty", "Airport Id should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airportCode", "airportCode.empty", "Airport Code should not be empty");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airportName", "airportName.empty", "Airport Name should not be empty");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airportCity", "airportCity.empty", "Airport City should not be empty");	

	}

}
