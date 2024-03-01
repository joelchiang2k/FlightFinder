package com.synergisticit.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Flight;
import com.synergisticit.service.FlightService;
import com.synergisticit.validation.FlightValidator;

import jakarta.validation.Valid;

@Controller
public class FlightController {
	@Autowired FlightService flightService;
	@Autowired FlightValidator flightValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(flightValidator);
	}
	
	@RequestMapping("/flightForm")
	public ModelAndView flightForm(Flight flight, Model model, Principal principal) {
		ModelAndView mav = new ModelAndView("flightForm");
//		getData(model);
		flightList(model);
		if(principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}
		
		
		return mav;
	}
	
	@RequestMapping("/saveFlight")
	public ModelAndView saveFlight(@Valid @ModelAttribute Flight flight, BindingResult br, Model model) {
		//accountValidator.validate(account, br);
		ModelAndView mav = new  ModelAndView("flightForm");

		
		if(br.hasErrors()) {

			flightList(model);
			return mav;
		}else {
			flightService.save(flight);
			flightList(model);
			mav.setViewName("redirect:flightForm");
			return mav;
		}
	}	
	
	@RequestMapping("/updateFlight")
	public String updateFlight(Flight flight, Model model) {
		System.out.println("flightId" + flight.getFlightId());
		Flight b = flightService.findById(flight.getFlightId());
		model.addAttribute("flight", b);
		flightList(model);
		return "flightForm";
	}
	
	@RequestMapping("/deleteAccount")
	public String deleteFlight(Flight flight, Model model, RedirectAttributes ra) {
		flightList(model);
		flightService.deleteById(flight.getFlightId());
		return "redirect:flightForm"; 
	}
	
	public void flightList(Model model) {
		List<Flight> flights = flightService.findAll();
		
		model.addAttribute("flights", flights);
		
	}
	
//	public void getData(Model model) {
//	
//		model.addAttribute("accountTypes", AccountType.values());
//		
//	}
}
