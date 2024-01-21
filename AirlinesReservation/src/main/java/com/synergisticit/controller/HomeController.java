package com.synergisticit.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping({"/","home"})
	public String homeABC(Principal principal, Model model) {
		if(principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}
		return "home";
	}
}
