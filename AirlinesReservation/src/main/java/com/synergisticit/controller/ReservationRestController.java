package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.synergisticit.domain.Reservation;
import com.synergisticit.service.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("reservations")
public class ReservationRestController {
	@Autowired ReservationService reservationService;
	
	@GetMapping(value = "all")
	public ResponseEntity<List<Reservation>> findAll(){
		List<Reservation> reservations = reservationService.findAll();

		if(reservations.isEmpty()) {
			return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.FOUND);
		}
	}
	
	@GetMapping(value = "findById")
	public ResponseEntity<Reservation> findById(@RequestParam long reservationId){
		Reservation reservation = reservationService.findById(reservationId);
	
		if(reservation == null) {
			return new ResponseEntity<Reservation>(reservation, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Reservation>(reservation, HttpStatus.FOUND);
		}
	}
	
	@RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
	public ResponseEntity<Reservation> deleteReservation(@RequestParam Long reservationId) {
		HttpHeaders headers = new HttpHeaders();
		Reservation reservation = reservationService.findById(reservationId);
	    if (reservation == null) {
	        return new ResponseEntity<Reservation>(reservation, HttpStatus.NOT_FOUND);
	    } else {
	    	reservationService.deleteById(reservationId);
	    	headers.add("Reservation deleted", String.valueOf(reservationId));
	        return new ResponseEntity<Reservation>(reservation, headers, HttpStatus.NO_CONTENT);
	    }
	}
	
	@PostMapping(value = "save", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveReservation(@Valid @RequestBody Reservation reservation, BindingResult br){
		StringBuilder sb = new StringBuilder("");
		if(reservationService.existsById(reservation.getReservationId()) || br.hasFieldErrors()) {
			if(br.hasFieldErrors()) {
				List<FieldError> fieldErrors = br.getFieldErrors();
				for(FieldError fieldError : fieldErrors) {
					sb = sb.append("\"" + fieldError.getField()+"\":"+fieldError.getDefaultMessage()+"\n");
				}
				System.out.println("sb: " + sb);
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.ACCEPTED);
			}
			else {
				sb.append("Reservation with id" + reservation.getReservationId() + "\"already there");
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.NOT_ACCEPTABLE);
			}
		}else {
			Reservation u = reservationService.save(reservation);
			return new ResponseEntity<Reservation>(u, HttpStatus.CREATED);
		}
	
	}
	
	@PutMapping("update")
	public ResponseEntity<?> updateReservation(@RequestBody Reservation u){
		Reservation reservation = reservationService.findById(u.getReservationId());
		if(reservation == null) {
			return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
		}else {
			reservationService.save(reservation);
			return new ResponseEntity<Reservation>(u, HttpStatus.OK);
		}
		
	}
}
