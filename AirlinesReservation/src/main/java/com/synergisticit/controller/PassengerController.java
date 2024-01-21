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
import com.synergisticit.domain.Gender;
import com.synergisticit.domain.Passenger;
import com.synergisticit.service.PassengerService;

import jakarta.validation.Valid;

@Controller
public class PassengerController {
	@Autowired PassengerService passengerService;
	//@Autowired AccountValidator accountValidator;
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		binder.addValidators(accountValidator);
//	}
	
	@RequestMapping("/passengerForm")
	public ModelAndView passengerForm(Passenger passenger, @RequestParam(name = "flightId", required = false) Long flightId, Model model, Principal principal) {
		ModelAndView mav = new ModelAndView("passengerForm");
		mav.addObject("flightId", flightId);
		getData(model);
		passengerList(model);
		if(principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}
		
		
		return mav;
	}
	
	@RequestMapping("/savePassenger")
	public ModelAndView savePassenger(@Valid @ModelAttribute Passenger passenger,@RequestParam("flightId") Long flightId,
            @RequestParam("passengerId") Long passengerId, BindingResult br, Model model) {
		//accountValidator.validate(account, br);
		ModelAndView mav = new  ModelAndView("passengerForm");

		
		if(br.hasErrors()) {
			passengerList(model);
			getData(model);
			return mav;
		}else {
			passengerService.save(passenger);
			passengerList(model);
			mav.setViewName("redirect:/reservationTemp?flightId=" + flightId + "&passengerId=" + passengerId);
			return mav;
		}
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
