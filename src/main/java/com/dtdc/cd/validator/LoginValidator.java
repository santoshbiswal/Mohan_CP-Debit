package com.dtdc.cd.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dtdc.cd.command.LoginCommand;

@Component
public class LoginValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {

		if(clazz.isAssignableFrom(LoginCommand.class))
			return true;
			
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {

		LoginCommand command = (LoginCommand) target;
		
		if(command.getUsername() == null || "".equals(command.getUsername()) || command.getUsername().isEmpty()) {
			
			errors.rejectValue("username", "login.username");
		}
		
		if(command.getPassword() == null || "".equals(command.getPassword()) || command.getPassword().isEmpty()) {
			
			errors.rejectValue("password", "login.password");
		}

		if("".equals(command.getUsertype())) {
			
			errors.rejectValue("usertype", "login.usertype");
		}
	}

	
}
