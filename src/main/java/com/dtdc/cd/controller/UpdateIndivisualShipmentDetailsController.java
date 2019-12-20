package com.dtdc.cd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.dtdc.cd.command.UpdateIndivisualShipmentDetailsCommand;
import com.dtdc.cd.deligate.UpdateIndivisualShipmentDetailsDelegate;
import com.dtdc.cd.exception.UserNotFoundException;

@Controller
public class UpdateIndivisualShipmentDetailsController {
	
	@Autowired
	private UpdateIndivisualShipmentDetailsDelegate delegate;

	@PostMapping("/indivisual-shipment-updated.htm")
	public String updateShipmentById(UpdateIndivisualShipmentDetailsCommand command,Model model) throws UserNotFoundException {
		
		System.out.println("Command object : "+command);
		
		delegate.updateDeliveryDetails(command);
		
		model.addAttribute("message", "Shipment Updated Successfully");
		
		return "shipment-updated";
	}
	
}
