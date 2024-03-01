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

import com.synergisticit.domain.Passenger;
import com.synergisticit.service.PassengerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("passengers")
public class PassengerRestController {
	@Autowired PassengerService passengerService;
	
	@GetMapping(value = "all")
	public ResponseEntity<List<Passenger>> findAll(){
		List<Passenger> passengers = passengerService.findAll();

		if(passengers.isEmpty()) {
			return new ResponseEntity<List<Passenger>>(passengers, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Passenger>>(passengers, HttpStatus.FOUND);
		}
	}
	
	@GetMapping(value = "findById")
	public ResponseEntity<Passenger> findById(@RequestParam long passengerId){
		Passenger passenger = passengerService.findById(passengerId);
	
		if(passenger == null) {
			return new ResponseEntity<Passenger>(passenger, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Passenger>(passenger, HttpStatus.FOUND);
		}
	}
	
	@RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
	public ResponseEntity<Passenger> deletePassenger(@RequestParam Long passengerId) {
		HttpHeaders headers = new HttpHeaders();
		Passenger passenger = passengerService.findById(passengerId);
	    if (passenger == null) {
	        return new ResponseEntity<Passenger>(passenger, HttpStatus.NOT_FOUND);
	    } else {
	    	passengerService.deleteById(passengerId);
	    	headers.add("Passenger deleted", String.valueOf(passengerId));
	        return new ResponseEntity<Passenger>(passenger, headers, HttpStatus.NO_CONTENT);
	    }
	}
	
	@PostMapping(value = "save", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> savePassenger(@Valid @RequestBody Passenger passenger, BindingResult br){
		StringBuilder sb = new StringBuilder("");
		if(passengerService.existsById(passenger.getPassengerId()) || br.hasFieldErrors()) {
			if(br.hasFieldErrors()) {
				List<FieldError> fieldErrors = br.getFieldErrors();
				for(FieldError fieldError : fieldErrors) {
					sb = sb.append("\"" + fieldError.getField()+"\":"+fieldError.getDefaultMessage()+"\n");
				}
				System.out.println("sb: " + sb);
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.ACCEPTED);
			}
			else {
				sb.append("Passenger with id" + passenger.getPassengerId() + "\"already there");
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.NOT_ACCEPTABLE);
			}
		}else {
			Passenger u = passengerService.save(passenger);
			return new ResponseEntity<Passenger>(u, HttpStatus.CREATED);
		}
	
	}
	
	@PutMapping("update")
	public ResponseEntity<?> updatePassenger(@RequestBody Passenger u){
		Passenger passenger = passengerService.findById(u.getPassengerId());
		if(passenger == null) {
			return new ResponseEntity<Passenger>(HttpStatus.NOT_FOUND);
		}else {
			passengerService.save(passenger);
			return new ResponseEntity<Passenger>(u, HttpStatus.OK);
		}
		
	}
}
