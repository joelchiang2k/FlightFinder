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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergisticit.domain.CheckedIn;
import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Passenger;
import com.synergisticit.domain.Reservation;
import com.synergisticit.service.AirlineService;
import com.synergisticit.service.FlightService;
import com.synergisticit.service.PassengerService;
import com.synergisticit.service.ReservationService;
import com.synergisticit.validation.ReservationValidator;

import jakarta.validation.Valid;

@Controller
public class ReservationController {
	@Autowired AirlineService airlineService;
	@Autowired ReservationService reservationService;
	@Autowired FlightService flightService;
	@Autowired PassengerService passengerService;
	@Autowired ReservationValidator reservationValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(reservationValidator);
	}
	
	@RequestMapping("/reservationForm")
	public ModelAndView reservationForm(@ModelAttribute("reservation") Reservation reservation,
			@RequestParam(name = "passengerId", required = false) Long passengerId,
	        Model model, Principal principal) {
		ModelAndView mav = new ModelAndView("reservationForm");
		
		if(passengerId != null) {
			System.out.println("passengerId" + passengerId);
			model.addAttribute("passengerId", passengerId);
			Passenger passenger = passengerService.findById(passengerId);
			System.out.println("passengerFlight" + passenger.getFlight().getFlightId());
	    	//mav.addObject(passenger.getFlight().getFlightId());
		}
		
	    
		getData(model);
		reservationList(model);
		if(principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}
		
		
		return mav;
	}
	
	@RequestMapping("/reservationList")
	public ModelAndView reservationList(@ModelAttribute("reservation") Reservation reservation, 
	        Model model, Principal principal) {
		

		ModelAndView mav = new ModelAndView("reservationList");
		
	    getData(model);
		reservationList(model);
		if(principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}
		
		
		return mav;
	}
	
	@RequestMapping("/reservationTemp")
	public ModelAndView reservationTemp(Reservation reservation, 
			@RequestParam(name = "flightId", required = false) Long flightId,
	        Model model,  RedirectAttributes redirectAttributes, Principal principal) {
		
	    redirectAttributes.addFlashAttribute("flightId", flightId);
	    //redirectAttributes.addFlashAttribute("passengerId", passengerId);
	    System.out.println("initialFlightId" + flightId);
			
		
	    getData(model);
		reservationList(model);
		if(principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}
		
		
		return new ModelAndView("redirect:/reservationForm?flightId=" + flightId);
	}
	
	
	@RequestMapping("/saveReservation")
	public ModelAndView saveReservation(@Valid @ModelAttribute Reservation reservation, 
            BindingResult br, 
            @RequestParam(name = "passengerId", required = true) Long passengerId,
            Model model) {
	    ModelAndView mav = new ModelAndView("reservationForm");

	    if (br.hasErrors()) {
	    	System.out.println("has error");
	        getData(model);
	        reservationList(model);
	        return mav;
	    } else {
	    	 Passenger passenger = passengerService.findById(passengerId);
	    	 Flight flight = flightService.findById(passenger.getFlight().getFlightId());
	    	 List<Passenger> passengers = new ArrayList<Passenger>();
	    	 passengers.add(passenger);
	    	 reservation.setPassengers(passengers);
	    	 for(Passenger p : passengers) {
	    		 reservation.setPassenger(p);
	    	 }
	         reservation.setFlight(flight);
	         //reservation.setPassenger(passenger);
	         //System.out.println("reservation" + reservation.getPassenger().getPassengerFirstName());
	         System.out.println("reservationNumer" + reservation.getFlight().getFlightNumber());
	         System.out.println(reservation.getPassengers().get(0).getPassengerFirstName());
//	         int seatsBooked = flight.getFlightSeatsBook() + 1;
//	         flight.setFlightSeatsBook(seatsBooked);
//	         System.out.println("flgithsbooks" + flight.getFlightSeatsBook());
	         
	         // Save the reservation
	         reservationService.save(reservation);
	         
	         getData(model);
	         reservationList(model);
	         mav.setViewName("redirect:reservationForm");
	         return mav;
	    }
	}

	
	@RequestMapping("/updateReservation")
	public String updateReservation(Reservation reservation, Model model) {
		Reservation b = reservationService.findById(reservation.getReservationId());
		model.addAttribute("reservation", b);
		getData(model);
		reservationList(model);
		return "reservationForm";
	}
	
	@RequestMapping("/deleteReservation")
	public String deleteReservation(Reservation reservation, Model model, RedirectAttributes ra) {
		getData(model);
		reservationList(model);
		reservationService.deleteById(reservation.getReservationId());
		return "reservationForm"; 
	}
	
	public void reservationList(Model model) {
		List<Reservation> reservations = reservationService.findAll();
		
		model.addAttribute("reservations", reservations);
		
	}
	
	public void getData(Model model) {
		model.addAttribute("checkedIns", CheckedIn.values());
		
	}
}
