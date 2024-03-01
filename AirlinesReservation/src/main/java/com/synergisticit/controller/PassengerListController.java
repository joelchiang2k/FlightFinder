package com.synergisticit.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.synergisticit.domain.Passenger;
import com.synergisticit.service.PassengerService;

@Controller
public class PassengerListController {
	@Autowired PassengerService passengerService;
	
	@RequestMapping("/passengerList")
	public ModelAndView passengerList(Passenger passenger, @RequestParam(name = "flightId", required = false) Long flightId, Model model, Principal principal) {
		ModelAndView mav = new ModelAndView("passengerList");
		passengerList(model);
		if(principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}
		
		
		return mav;
	}
	
	public void passengerList(Model model) {
		List<Passenger> passengers = passengerService.findAll();
		
		model.addAttribute("passengers", passengers);
		
	}
	
}
