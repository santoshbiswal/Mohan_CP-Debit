package com.dtdc.cd.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dtdc.cd.command.LoginCommand;
import com.dtdc.cd.exception.UserNotFoundException;
import com.dtdc.cd.service.LoginService;
import com.dtdc.cd.validator.LoginValidator;

@Controller
public class DtdcLoginController {
	
	@Autowired
	private LoginValidator validator;
	
	@Autowired
	private LoginService service;

	@GetMapping("/dtdc-login.htm")
	public String goToLogin() {
		
		return "dtdc-login";
	}
	
	@ModelAttribute("dtdcLogin")
	public LoginCommand emptyCommandObject() {
		
		return new LoginCommand();
	}
	
	@PostMapping("/dtdc-login.htm")
	public String doLogin(HttpSession session ,@ModelAttribute("dtdcLogin")LoginCommand command,BindingResult error) {
		
		if(validator.supports(LoginCommand.class)) {
			
			validator.validate(command, error);
			if(error.hasErrors()) {
				return "dtdc-login";
			}
		}
		
		try {
			
			Integer userId = service.validateLogin(command);
			
			session.setAttribute("userId", userId);
			session.setAttribute("username", command.getUsername());
			session.setAttribute("password", command.getPassword());
			
		}catch(UserNotFoundException e) {
			
			//errors.rejectValue("username", "login.invalid");
			error.reject("username", e.getMessage());
			
			return "dtdc-login";
		}
		
		if(command.getUsertype().equals("corporate")) {
			
			return "block-cds-generation";
			
		}else if(command.getUsertype().equals("channel-partner")) {
			
			return "dtdc-channel-partner-dashboard";
			
		}else if(command.getUsertype().equals("delivery-partner")) {
			
			return "dtdc-delivery-partner-dashboard";
		}
		
		System.out.println(command);
		
		return "registration-success";
		
		
	}
	
	@GetMapping("/dtdc-logout.htm")
	public String doLogout(HttpSession session) {
		
		Integer userId=(Integer) session.getAttribute("userId");
		System.out.println("User Id : "+userId);
		
		session.invalidate();
		
		return "dtdc-login";
	}
}
