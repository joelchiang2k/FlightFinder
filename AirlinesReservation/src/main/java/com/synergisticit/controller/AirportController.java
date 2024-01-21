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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Flight;
import com.synergisticit.service.AirportService;
import com.synergisticit.service.FlightService;

import jakarta.validation.Valid;

@Controller
public class AirportController {
	@Autowired AirportService airportService;
	@Autowired FlightService flightService;
	//@Autowired AccountValidator accountValidator;
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		binder.addValidators(accountValidator);
//	}
	
	@RequestMapping("/airportForm")
	public ModelAndView airportForm(Airport airport, Model model, Principal principal) {
		ModelAndView mav = new ModelAndView("airportForm");
//		getData(model);
		airportList(model);
		if(principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}
		
		
		return mav;
	}
	
	@RequestMapping("/saveAirport")
	public ModelAndView saveAirport(@Valid @ModelAttribute Airport airport, BindingResult br, Model model) {
		//accountValidator.validate(account, br);
		ModelAndView mav = new  ModelAndView("airportForm");

		
		if(br.hasErrors()) {
			airportList(model);
			return mav;
		}else {
			airportService.save(airport);
			airportList(model);
			mav.setViewName("redirect:airportForm");
			return mav;
		}
	}	
	
	@RequestMapping("/updateAirport")
	public String updateAirport(Airport airport, Model model) {
		Airport b = airportService.findById(airport.getAirportId());
		model.addAttribute("airport", b);
		
		airportList(model);
		return "airportForm";
	}
	
	@RequestMapping("/deleteAirport")
	public String deleteAirport(Airport airport, Model model, RedirectAttributes ra) {
		airportList(model);
		airportService.deleteById(airport.getAirportId());
		return "redirect:airportForm"; 
	}
	
	 @RequestMapping("/showArrivals")
	    public String showArrivals(@RequestParam("airportCode") String airportCode, Model model) {
	        // Retrieve arrivals information based on the airportCode
	        // You can use airportService or any other service to fetch the required data

	        // For example:
	        List<Flight> flights = flightService.findByCode(airportCode);
	   
	        // Add the arrivals information to the model
	        model.addAttribute("flights", flights);

	        return "flightList"; // Change this to the name of your JSP page for arrivals
	    }
	
	public void airportList(Model model) {
		List<Airport> airports = airportService.findAll();
		
		model.addAttribute("airports", airports);
		
	}

}
