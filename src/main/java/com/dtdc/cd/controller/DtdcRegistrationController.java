package com.dtdc.cd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dtdc.cd.command.RegistrationCommand;
import com.dtdc.cd.deligate.DTDCRegistrationDeligate;
import com.dtdc.cd.validator.RegistrationValidator;

@Controller
public class DtdcRegistrationController {
	
	@Autowired
	private RegistrationValidator validator;
	
	@Autowired
	private DTDCRegistrationDeligate deligate;

	@GetMapping("/dtdc-registration.htm")
	public String goToRegistration() {
		
		return "dtdc-registration";
	}
	
	@ModelAttribute("dtdcRegistration")
	public RegistrationCommand emptyCommandObject() {
		
		return new RegistrationCommand();
	}
	
	@PostMapping("/dtdc-registration.htm")
	public String doRegister(@ModelAttribute("dtdcRegistration")RegistrationCommand command,BindingResult error) {
		
		if(validator.supports(RegistrationCommand.class)) {
			
			validator.validate(command, error);
			if(error.hasErrors()) {
				
				return "dtdc-registration";
			}
		}
		
		System.out.println(command);
		deligate.register(command);
		return "registration-success";
	}
}
