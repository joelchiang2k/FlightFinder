package com.synergisticit.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Flight;
import com.synergisticit.service.FlightService;

@Controller
public class FlightListController {
	
	@Autowired FlightService flightService;
	
	@RequestMapping("/flightList")
	public ModelAndView flightForm(Flight flight, Model model, Principal principal) {
		ModelAndView mav = new ModelAndView("flightList");
		flightList(model);
		if(principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}
		
		
		return mav;
	}
	
	public void flightList(Model model) {
		List<Flight> flights = flightService.findAll();
		
		model.addAttribute("flights", flights);
		
	}
}
