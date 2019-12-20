package com.dtdc.cd.ajax;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dtdc.cd.bo.ShipmentStatusBO;
import com.dtdc.cd.service.ShipmentBookingService;

@RestController
public class ShipmentBookingAjaxController {
	
	@Autowired
	private ShipmentBookingService service;

	@PostMapping("/api/get-shipment-booking-details")
	public ResponseEntity<?> getShipmentDeliveryDetails(@RequestBody ShipmentBookingSearchCriteria criteria){
		
		System.out.println(criteria);
		
		//ShipmentBookingResponse body = demoShipmentDisplayResponse(); //(for hard-coding stuff)
		
		String shipmentNumber = criteria.getShipNumber();
		String dateRange = criteria.getDaterange();
		String source = criteria.getSource();
		String destination = criteria.getDestination();
		String status = criteria.getStatus();
		
		List<ShipmentStatusBO> body = null;
		
		if(!shipmentNumber.isEmpty()) {
			
			System.out.println("From Shipment condition");
			
			body = service.getShipmentAndStatusByShipmentNumber(shipmentNumber);
			
		}else if(!dateRange.isEmpty()) {
			
			System.out.println("From dateRange condition");
			
			String[] dateArray=dateRange.split("-");//convertDateFromString(dateRange);
			
				String fromDate = dateArray[0].trim();
				String toDate = dateArray[1].trim();

				body = service.getShipmentAndStatusBydateRangeAndStatus(fromDate, toDate, status);
				
		}else if(!source.isEmpty() && !destination.isEmpty()) {
			
			System.out.println("From source and destination condition");
			body = service.getShipmentAndStatusBySourceAndDest(source, destination,status);
		}
		
		
		return ResponseEntity.ok(body);
	}
	
	//test data with hard-coding
	private ShipmentBookingResponse demoShipmentDisplayResponse() {
		ShipmentBookingResponse response = new ShipmentBookingResponse();

		response.setShipmentNumber("tdshhsjs");
		response.setDob("yujsnsn");
		response.setSource("yehsnsnn");
		response.setDestination("ttwgwbs");
		response.setStatus("yssnngas");

		return response;

	}
}
