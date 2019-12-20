package com.dtdc.cd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dtdc.cd.command.ShipmentBookingCommand;
import com.dtdc.cd.deligate.DTDCBookingDeligate;
import com.dtdc.cd.exception.UserNotFoundException;
import com.dtdc.cd.validator.ShipmentBookingValidator;

@Controller
public class DtdcShipmentBookingController {
	
	@Autowired
	private ShipmentBookingValidator validator;
	
	@Autowired
	private DTDCBookingDeligate deligate;

	@GetMapping("/dtdc-booking.htm")
	public String goToBooking() {
		
		return "shipment-booking";
	}
	
	@ModelAttribute("bookingCommand")
	public ShipmentBookingCommand emptyBookingCommandObject() {
		
		return new ShipmentBookingCommand();
	}
	
	@PostMapping("/dtdc-booking.htm")
	public String doShipmentBooking(@ModelAttribute("bookingCommand")ShipmentBookingCommand command,BindingResult errors) throws UserNotFoundException {
		
		if(validator.supports(ShipmentBookingCommand.class)) {
			
			validator.validate(command, errors);
			
			if(errors.hasErrors()) {
				
				return "shipment-booking";
			}
		}
		System.out.println(command);
		deligate.doDtdcBooking(command);
		
		return "booking-success";
	}
}
