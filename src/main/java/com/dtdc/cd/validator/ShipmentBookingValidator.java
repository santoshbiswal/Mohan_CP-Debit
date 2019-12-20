package com.dtdc.cd.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dtdc.cd.command.ShipmentBookingCommand;

@Component
public class ShipmentBookingValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		if(clazz.isAssignableFrom(ShipmentBookingCommand.class))
			return true;
		
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {

		ShipmentBookingCommand command = (ShipmentBookingCommand) target;
		
		if(command.getName() == null || "".equals(command.getName()) || command.getName().isEmpty()) {
			
			errors.rejectValue("name", "booking.name");
		}
	}

	
}
