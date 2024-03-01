package com.synergisticit.controller;

import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Search;

import com.synergisticit.service.FlightService;
import com.synergisticit.service.SearchService;

import jakarta.validation.Valid;

@Controller
public class SearchController {
	@Autowired FlightService flightService;
	@Autowired SearchService searchService;
	
	@Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;
    
    
	@RequestMapping("/searchForm")
	public ModelAndView searchForm(Search search, Model model, Principal principal) {
		ModelAndView mav = new ModelAndView("searchForm");
		//List<BankTransaction> bankTransactions = bankTransactionService.findAll();
		//model.addAttribute("bankTransactions", bankTransactions);
		if(principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}
		
		
		return mav;
	}
	
	@RequestMapping("/saveSearch")
	public ModelAndView saveSearch(@Valid @ModelAttribute Search search, BindingResult br, Model model) {
		ModelAndView mav = new  ModelAndView("searchForm");
		
		if (br.hasErrors()) {
			System.out.println("errors" + br);
	        List<Flight> flights = flightService.findAll();
	        model.addAttribute("flights", flights);
	        return mav;
	    } else {
	        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
	        	String query = "SELECT flightId FROM flight " +
	                    "WHERE CONVERT(CONCAT(flightDepartureDate, ' ', flightDepartureTime), DATETIME) BETWEEN ? AND ? " +
	                    "AND flightDepartureCity = ? AND flightArrivalCity = ?";

	            System.out.println("query" + query);
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	               
	                Timestamp fromTimestamp = Timestamp.valueOf(search.getSearchFromDateTime());
	                Timestamp toTimestamp = Timestamp.valueOf(search.getSearchToDateTime());

	        
	                preparedStatement.setTimestamp(1, fromTimestamp);
	                preparedStatement.setTimestamp(2, toTimestamp);
	                preparedStatement.setString(3, search.getSearchFlightDepartureCity());
	                preparedStatement.setString(4, search.getSearchFlightArrivalCity());

	                
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                 
	                    List<Flight> flightList = new ArrayList<>();
	                    while (resultSet.next()) {
	                        Flight flight = new Flight();
	                      
	                        Long flightId = resultSet.getLong("flightId");
	                        
	                        
	                        flightList.add(flightService.findById(flightId));
	                    }
	                    System.out.println(flightList);
	                   
	                    model.addAttribute("flights", flightList);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return mav;
	    }
	}
	


}
