package com.dtdc.cd.deligate;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dtdc.cd.model.ShipmentBooking;
import com.dtdc.cd.model.ShipmentDelivery;
import com.dtdc.cd.service.ShipmentBookingService;
import com.dtdc.cd.service.ShipmentDeliveryService;

@Component
public class PopulatePendingShipmentDeligate {

	@Autowired
	private ShipmentBookingService bookingService;
	
	@Autowired
	private ShipmentDeliveryService deliveryService;
	
	public void doPopulateIntoDelivery(Date now,Date tomorrow) {
		
		List<ShipmentBooking> shipmentBookings = bookingService.getShipmentBookingByDate(now, tomorrow);
		
		populateInToDelivery(shipmentBookings);
		System.out.println(shipmentBookings);
	}
	
	public void populateInToDelivery(List<ShipmentBooking> array) {
		
		for(ShipmentBooking booking : array) {
			
			ShipmentDelivery delivery = new ShipmentDelivery();
			delivery.setDeliveryConsgNo(booking.getConsg_number());//ConsignmentNumber
			delivery.setDeliveryStatus("pending");//pending,delivered,in-process,failure
			
			ShipmentDelivery shipmentDelivery = deliveryService.saveDelivery(delivery);
			System.out.println("Delivery ID : "+shipmentDelivery.getDeliveryId());
		}
	}
}
