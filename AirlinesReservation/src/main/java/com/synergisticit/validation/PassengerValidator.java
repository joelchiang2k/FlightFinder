package com.synergisticit.validation;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.synergisticit.domain.Passenger;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

@Component
public class PassengerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Passenger.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Passenger passenger = (Passenger)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passengerId", "passengerId.empty", "Passenger Id should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passengerFirstName", "passengerFirstName.empty", "Passenger First Name should not be empty");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passengerLastName", "passengerLastName.empty", "Passenger Last Name should not be empty");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passengerEmail", "passengerEmail.empty", "Passenger Email should not be empty");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passengerPhoneNo", "passengerPhoneNo.empty", "Passenger Phone No should not be empty");	
		
		if(passenger.getPassengerGender() == null) {
			errors.rejectValue("passengerGender", "passengerGender.value", "Select Gender.");
		}
		
		if(passenger.getPassengerDOB() == null) {
			errors.rejectValue("passengerDOB", "passengerDOB.value", "Select Date.");
		}
	}

}
