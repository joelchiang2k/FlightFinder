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

import com.synergisticit.domain.Airline;
import com.synergisticit.domain.Airport;
import com.synergisticit.service.AirlineService;
import com.synergisticit.service.AirportService;

import jakarta.validation.Valid;

@Controller
public class AirlineController {
	@Autowired AirlineService airlineService;
	//@Autowired AccountValidator accountValidator;
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		binder.addValidators(accountValidator);
//	}
	
	@RequestMapping("/airlineForm")
	public ModelAndView airportForm(Airline airline, Model model, Principal principal) {
		ModelAndView mav = new ModelAndView("airlineForm");
//		getData(model);
		airlineList(model);
		if(principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}
		
		
		return mav;
	}
	
	@RequestMapping("/saveAirline")
	public ModelAndView saveAirline(@Valid @ModelAttribute Airline airline, BindingResult br, Model model) {
		//accountValidator.validate(account, br);
		ModelAndView mav = new ModelAndView("airlineForm");

		
		if(br.hasErrors()) {
			//getData(model);
			airlineList(model);
			return mav;
		}else {
			//getData(model);
			airlineService.save(airline);
			airlineList(model);
			mav.setViewName("redirect:airlineForm");
			return mav;
		}
	}	
	
	@RequestMapping("/updateAirline")
	public String updateAirline(Airline airline, Model model) {
		Airline b = airlineService.findById(airline.getAirlineId());
		model.addAttribute("airline", b);
		
		airlineList(model);
		return "airlineForm";
	}
	
	@RequestMapping("/deleteAirline")
	public String deleteAirline(Airline airline, Model model, RedirectAttributes ra) {
		airlineList(model);
		airlineService.deleteById(airline.getAirlineId());
		return "redirect:airlineForm"; 
	}
	
	public void airlineList(Model model) {
		List<Airline> airlines = airlineService.findAll();
		
		model.addAttribute("airlines", airlines);
		
	}
}
