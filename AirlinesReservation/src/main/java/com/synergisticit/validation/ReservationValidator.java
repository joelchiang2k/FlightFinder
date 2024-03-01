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
import com.synergisticit.domain.Reservation;
import com.synergisticit.domain.Role;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

@Component
public class ReservationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Reservation.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Reservation reservation = (Reservation)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reservationId", "reservationId.empty", "Reservation Id should not be empty");

		if(reservation.getCheckedIn() == null) {
			errors.rejectValue("checkedIn", "checkedIn.value", "Select Checked In.");
		}
	}

}
