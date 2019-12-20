package com.dtdc.cd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dtdc.cd.bo.IndivisualShipmentDetailsBO;
import com.dtdc.cd.service.IndivisualShipmentDetailsService;

@Controller
public class IndivisualShipmentDetailsController {

	@Autowired
	private IndivisualShipmentDetailsService service;
	
	@GetMapping("/show-shipment-details.htm")
	public ModelAndView getShipmentDetails(@RequestParam("shipmentId") String consignmentnumber) {
		
		System.out.println("consignmentnumber : "+consignmentnumber);
		ModelAndView mav = new ModelAndView();
		
		IndivisualShipmentDetailsBO resultShipment = service.getIndivisualShipmentDetails(consignmentnumber);
		mav.addObject("shipment", resultShipment);
		mav.setViewName("show-indivisual-shipping-details");
		
		return mav;
	}

}
