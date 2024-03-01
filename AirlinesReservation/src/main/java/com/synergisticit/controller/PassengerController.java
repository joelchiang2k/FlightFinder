package com.synergisticit.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Gender;
import com.synergisticit.domain.Passenger;
import com.synergisticit.domain.PassengersForm;
import com.synergisticit.service.FlightService;
import com.synergisticit.service.PassengerService;
import com.synergisticit.validation.PassengerValidator;

import jakarta.validation.Valid;

@Controller
public class PassengerController {
	@Autowired PassengerService passengerService;
	@Autowired PassengerValidator passengerValidator;
	@Autowired FlightService flightService;
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		binder.addValidators(passengerValidator);
//	}
	
	@RequestMapping("/passengerForm")
	public ModelAndView passengerForm(Passenger passenger, @RequestParam(name = "flightId", required = false) Long flightId, Model model, Principal principal) {
		ModelAndView mav = new ModelAndView("passengerForm");
		System.out.println("flightId" + flightId);
		model.addAttribute("flightId", flightId);
		getData(model);
		passengerList(model);
		if(principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}
		
		
		return mav;
	}
	
	
	@RequestMapping("/savePassenger")
	public ModelAndView savePassenger(@Valid @ModelAttribute PassengersForm passengersForm,@RequestParam(name = "flightId", required = true) Long flightId, BindingResult br, Model model) {
		//accountValidator.validate(account, br);
		ModelAndView mav = new  ModelAndView("passengerForm");
		
		
		//mav.addObject("numPassengers", numPassengers);
		
		if (br.hasErrors()) {
	        passengerList(model);
	        getData(model);
	        return mav;
	    } else {
	        List<Passenger> passengers = passengersForm.getPassengers();
	         Flight flight = flightService.findById(flightId);
	         int seatsBooked = flight.getFlightSeatsBook() + passengers.size();
	         flight.setFlightSeatsBook(seatsBooked);
//	         System.out.println("flgithsbooks" + flight.getFlightSeatsBook());
	        for (Passenger passenger : passengers) {
	        	System.out.println(passenger.toString());
	        	passenger.setFlight(flight);
	            passengerService.save(passenger);
	        }
	        
	        passengerList(model);
	        mav.setViewName("redirect:/passengerList");
	        return mav;
	    }
	}
	
	
	@PostMapping("/saveNumPassengers")
	public ModelAndView saveNumPassengers(@RequestParam("numPassengers") int numPassengers, @RequestParam(name = "flightId", required = true) Long flightId, Model model) {
		ModelAndView mav = new ModelAndView("passengerForm");
		System.out.println("flightId" + flightId);
		mav.addObject("flightId", flightId);
		
	    List<Passenger> passengers = new ArrayList<>();

	 
	    for (int i = 0; i < numPassengers; i++) {
	        passengers.add(new Passenger());
	    }
	    
	  
	    PassengersForm passengersForm = new PassengersForm();
	    passengersForm.setPassengers(passengers);

	    
	    System.out.println("passengersform" + passengersForm);
	    mav.addObject("passengersForm", passengersForm);
	    mav.addObject("numPassengers", numPassengers);

	  
	    getData(model);
	    passengerList(model);
	    
	   
	    return mav;
	}
	
	@RequestMapping("/updatePassenger")
	public String updateFlight(Passenger passenger, Model model) {
		Passenger b = passengerService.findById(passenger.getPassengerId());
		model.addAttribute("passenger", b);
		getData(model);
		passengerList(model);
		return "passengerForm";
	}
	
	@RequestMapping("/deletePassenger")
	public String deletePassenger(Passenger passenger, Model model, RedirectAttributes ra) {
		passengerList(model);
		passengerService.deleteById(passenger.getPassengerId());
		return "redirect:passengerForm"; 
	}
	
	public void passengerList(Model model) {
		List<Passenger> passengers = passengerService.findAll();
		
		model.addAttribute("passengers", passengers);
		
	}
	
	public void getData(Model model) {
		model.addAttribute("genders", Gender.values());
		
	}
}
