package com.synergisticit.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

import jakarta.validation.Valid;

@Controller
public class ReservationController {
	@Autowired AirlineService airlineService;
	@Autowired ReservationService reservationService;
	@Autowired FlightService flightService;
	@Autowired PassengerService passengerService;
	//@Autowired AccountValidator accountValidator;
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		binder.addValidators(accountValidator);
//	}
	
	@RequestMapping("/reservationForm")
	public ModelAndView reservationForm(@ModelAttribute("reservation") Reservation reservation, 
	        Model model, Principal principal) {
		

		ModelAndView mav = new ModelAndView("reservationForm");
		
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
	        @RequestParam(name = "passengerId", required = false) Long passengerId,
	        Model model,  RedirectAttributes redirectAttributes, Principal principal) {
		
	    redirectAttributes.addFlashAttribute("flightId", flightId);
	    redirectAttributes.addFlashAttribute("passengerId", passengerId);
	    System.out.println("initialFlightId" + flightId);
			
		
	    getData(model);
		reservationList(model);
		if(principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}
		
		
		return new ModelAndView("redirect:/reservationForm?flightId=" + flightId + "&passengerId=" + passengerId);
	}
	
	
	@RequestMapping("/saveReservation")
	public ModelAndView saveReservation(@Valid @ModelAttribute Reservation reservation, 
            BindingResult br, 
            @RequestParam(name = "flightId") Long flightId,
            @RequestParam(name = "passengerId") Long passengerId,
            Model model) {
	    ModelAndView mav = new ModelAndView("reservationForm");

	    if (br.hasErrors()) {
	    	System.out.println("has error");
	        getData(model);
	        reservationList(model);
	        return mav;
	    } else {
	    	 Flight flight = flightService.findById(flightId);
	         Passenger passenger = passengerService.findById(passengerId);

	         reservation.setPassenger(passenger);
	         reservation.setFlight(flight);

	         System.out.println("reservation" + reservation.getPassenger().getPassengerFirstName());
	         System.out.println("reservationNumer" + reservation.getFlight().getFlightNumber());

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
