package com.synergisticit.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergisticit.domain.CheckedIn;
import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Gender;
import com.synergisticit.domain.Passenger;
import com.synergisticit.domain.Reservation;
import com.synergisticit.domain.UserInfoDTO;
import com.synergisticit.domain.PassengerRowMapper;
import com.synergisticit.service.AirlineService;
import com.synergisticit.service.FlightService;
import com.synergisticit.service.PassengerService;
import com.synergisticit.service.ReservationService;
import com.synergisticit.validation.ReservationValidator;

import jakarta.validation.Valid;

@Controller
public class UserSearchController {
	@Autowired AirlineService airlineService;
	@Autowired ReservationService reservationService;
	@Autowired FlightService flightService;
	@Autowired PassengerService passengerService;
	//@Autowired ReservationValidator reservationValidator;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		binder.addValidators(reservationValidator);
//	}
//	
	@GetMapping("/searchUserInfoForm")
    public ModelAndView searchUserInfo(@ModelAttribute("userInfo") UserInfoDTO userInfoDTO, Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("userSearch");
       
        if(principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}
        return mav;
    }
	
	@RequestMapping("/saveUserInfo")
    public ModelAndView saveUserInfo(@ModelAttribute("userInfo") UserInfoDTO userInfoDTO, Model model) {
        ModelAndView mav = new ModelAndView("userSearch");
       
        String firstName = userInfoDTO.getFirstName();
        String lastName = userInfoDTO.getLastName();
        
     
        String sql = "SELECT * FROM Passenger WHERE passengerFirstName = ? AND passengerLastName = ?";
        
        
        List<Passenger> passengers = jdbcTemplate.query(sql, new Object[]{firstName, lastName}, new PassengerRowMapper());
        
        for(Passenger passenger : passengers) {
        	System.out.println("passengers" + passenger.toString());
        }
        
        model.addAttribute("passengers", passengers);

        
        return mav;
    }
	
	
	
	
	
}
