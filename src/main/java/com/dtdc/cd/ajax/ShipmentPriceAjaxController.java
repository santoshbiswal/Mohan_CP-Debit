package com.dtdc.cd.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dtdc.cd.service.ShipmentBookingService;

@RestController
public class ShipmentPriceAjaxController {

	@Autowired
	private ShipmentBookingService service;
	
	@PostMapping("/api/find-price")
	public ResponseEntity<?> getBookingPrice(@RequestBody ShipmentPriceSearchCriteria criteria){
		
		Integer price = service.getPriceBySourceAndDestination(criteria.getSource(), criteria.getDestination());
		System.out.println("PRICE : "+price);
		ShipmentPriceResponse response = new ShipmentPriceResponse();
		response.setPrice(price);
		
		return ResponseEntity.ok(response);
	}
}
