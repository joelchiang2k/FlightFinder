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

import com.synergisticit.domain.Airport;

import com.synergisticit.service.AirportService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("airports")
public class AirportRestController {
	@Autowired AirportService airportService;
	
	@GetMapping(value = "all")
	public ResponseEntity<List<Airport>> findAll(){
		List<Airport> airports = airportService.findAll();

		if(airports.isEmpty()) {
			return new ResponseEntity<List<Airport>>(airports, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Airport>>(airports, HttpStatus.FOUND);
		}
	}
	
	@GetMapping(value = "findById")
	public ResponseEntity<Airport> findById(@RequestParam long airportId){
		Airport airport = airportService.findById(airportId);
	
		if(airport == null) {
			return new ResponseEntity<Airport>(airport, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Airport>(airport, HttpStatus.FOUND);
		}
	}
	
	@RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
	public ResponseEntity<Airport> deleteAirport(@RequestParam Long airportId) {
		HttpHeaders headers = new HttpHeaders();
		Airport airport = airportService.findById(airportId);
	    if (airport == null) {
	        return new ResponseEntity<Airport>(airport, HttpStatus.NOT_FOUND);
	    } else {
	    	airportService.deleteById(airportId);
	    	headers.add("Airport deleted", String.valueOf(airportId));
	        return new ResponseEntity<Airport>(airport, headers, HttpStatus.NO_CONTENT);
	    }
	}
	
	@PostMapping(value = "save", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveAirport(@Valid @RequestBody Airport airport, BindingResult br){
		StringBuilder sb = new StringBuilder("");
		if(airportService.existsById(airport.getAirportId()) || br.hasFieldErrors()) {
			if(br.hasFieldErrors()) {
				List<FieldError> fieldErrors = br.getFieldErrors();
				for(FieldError fieldError : fieldErrors) {
					sb = sb.append("\"" + fieldError.getField()+"\":"+fieldError.getDefaultMessage()+"\n");
				}
				System.out.println("sb: " + sb);
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.ACCEPTED);
			}
			else {
				sb.append("Airport with id" + airport.getAirportId() + "\"already there");
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.NOT_ACCEPTABLE);
			}
		}else {
			Airport u = airportService.save(airport);
			return new ResponseEntity<Airport>(u, HttpStatus.CREATED);
		}
	
	}
	
	@PutMapping("update")
	public ResponseEntity<?> updateAirport(@RequestBody Airport u){
		Airport airport = airportService.findById(u.getAirportId());
		if(airport == null) {
			return new ResponseEntity<Airport>(HttpStatus.NOT_FOUND);
		}else {
			airportService.save(airport);
			return new ResponseEntity<Airport>(u, HttpStatus.OK);
		}
		
	}
}
