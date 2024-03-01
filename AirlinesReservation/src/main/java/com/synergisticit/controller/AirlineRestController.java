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

import com.synergisticit.domain.Airline;
import com.synergisticit.service.AirlineService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("airlines")
public class AirlineRestController {
	@Autowired AirlineService airlineService;
	@GetMapping(value = "all")
	public ResponseEntity<List<Airline>> findAll(){
		List<Airline> airlines = airlineService.findAll();

		if(airlines.isEmpty()) {
			return new ResponseEntity<List<Airline>>(airlines, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Airline>>(airlines, HttpStatus.FOUND);
		}
	}
	
	@GetMapping(value = "findById")
	public ResponseEntity<Airline> findById(@RequestParam long airlineId){
		Airline airline = airlineService.findById(airlineId);
	
		if(airline == null) {
			return new ResponseEntity<Airline>(airline, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Airline>(airline, HttpStatus.FOUND);
		}
	}
	
	@RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
	public ResponseEntity<Airline> deleteAirline(@RequestParam Long airlineId) {
		HttpHeaders headers = new HttpHeaders();
		Airline airline = airlineService.findById(airlineId);
	    if (airline == null) {
	        return new ResponseEntity<Airline>(airline, HttpStatus.NOT_FOUND);
	    } else {
	    	airlineService.deleteById(airlineId);
	    	headers.add("Airline deleted", String.valueOf(airlineId));
	        return new ResponseEntity<Airline>(airline, headers, HttpStatus.NO_CONTENT);
	    }
	}
	
	@PostMapping(value = "save", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveAirline(@Valid @RequestBody Airline airline, BindingResult br){
		StringBuilder sb = new StringBuilder("");
		if(airlineService.existsById(airline.getAirlineId()) || br.hasFieldErrors()) {
			if(br.hasFieldErrors()) {
				List<FieldError> fieldErrors = br.getFieldErrors();
				for(FieldError fieldError : fieldErrors) {
					sb = sb.append("\"" + fieldError.getField()+"\":"+fieldError.getDefaultMessage()+"\n");
				}
				System.out.println("sb: " + sb);
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.ACCEPTED);
			}
			else {
				sb.append("Airline with id" + airline.getAirlineId() + "\"already there");
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.NOT_ACCEPTABLE);
			}
		}else {
			Airline u = airlineService.save(airline);
			return new ResponseEntity<Airline>(u, HttpStatus.CREATED);
		}
	
	}
	
	@PutMapping("update")
	public ResponseEntity<?> updateAirline(@RequestBody Airline u){
		Airline airline = airlineService.findById(u.getAirlineId());
		if(airline == null) {
			return new ResponseEntity<Airline>(HttpStatus.NOT_FOUND);
		}else {
			airlineService.save(airline);
			return new ResponseEntity<Airline>(u, HttpStatus.OK);
		}
		
	}
}
