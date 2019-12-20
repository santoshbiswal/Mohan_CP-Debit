package com.dtdc.cd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/dtdc-home.htm")
	public String goToHome() {
		
		return "dtdc-home";
	}
}
