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


import com.synergisticit.domain.Flight;
import com.synergisticit.service.FlightService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("flights")
public class FlightRestController {
	@Autowired FlightService flightService;
	
	@GetMapping(value = "all")
	public ResponseEntity<List<Flight>> findAll(){
		List<Flight> flights = flightService.findAll();

		if(flights.isEmpty()) {
			return new ResponseEntity<List<Flight>>(flights, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Flight>>(flights, HttpStatus.FOUND);
		}
	}
	
	@GetMapping(value = "findById")
	public ResponseEntity<Flight> findById(@RequestParam long flightId){
		Flight flight = flightService.findById(flightId);
	
		if(flight == null) {
			return new ResponseEntity<Flight>(flight, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Flight>(flight, HttpStatus.FOUND);
		}
	}
	
	@RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
	public ResponseEntity<Flight> deleteFlight(@RequestParam Long flightId) {
		HttpHeaders headers = new HttpHeaders();
		Flight flight = flightService.findById(flightId);
	    if (flight == null) {
	        return new ResponseEntity<Flight>(flight, HttpStatus.NOT_FOUND);
	    } else {
	    	flightService.deleteById(flightId);
	    	headers.add("Flight deleted", String.valueOf(flightId));
	        return new ResponseEntity<Flight>(flight, headers, HttpStatus.NO_CONTENT);
	    }
	}
	
	@PostMapping(value = "save", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveFlight(@Valid @RequestBody Flight flight, BindingResult br){
		StringBuilder sb = new StringBuilder("");
		if(flightService.existsById(flight.getFlightId()) || br.hasFieldErrors()) {
			if(br.hasFieldErrors()) {
				List<FieldError> fieldErrors = br.getFieldErrors();
				for(FieldError fieldError : fieldErrors) {
					sb = sb.append("\"" + fieldError.getField()+"\":"+fieldError.getDefaultMessage()+"\n");
				}
				System.out.println("sb: " + sb);
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.ACCEPTED);
			}
			else {
				sb.append("Flight with id" + flight.getFlightId() + "\"already there");
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.NOT_ACCEPTABLE);
			}
		}else {
			Flight u = flightService.save(flight);
			return new ResponseEntity<Flight>(u, HttpStatus.CREATED);
		}
	
	}
	
	@PutMapping("update")
	public ResponseEntity<?> updateFlight(@RequestBody Flight u){
		Flight flight = flightService.findById(u.getFlightId());
		if(flight == null) {
			return new ResponseEntity<Flight>(HttpStatus.NOT_FOUND);
		}else {
			flightService.save(flight);
			return new ResponseEntity<Flight>(u, HttpStatus.OK);
		}
		
	}
}
