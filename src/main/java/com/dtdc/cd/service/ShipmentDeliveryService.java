package com.dtdc.cd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtdc.cd.model.ShipmentDelivery;
import com.dtdc.cd.repository.ShipmentDeliveryRepository;

@Service
public class ShipmentDeliveryService {

	@Autowired
	private ShipmentDeliveryRepository repository;
	
	public ShipmentDelivery saveDelivery(ShipmentDelivery delivery) {
		
		return repository.save(delivery);
	}
	
	public boolean isShipmentDelivered(String shipmentNumber) {
		
		Integer count=repository.getCount(shipmentNumber);
		
		if(count==0) {
			
			return false;
		}	
		return true;
	}
}
