package com.dtdc.cd.deligate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dtdc.cd.command.UpdateIndivisualShipmentDetailsCommand;
import com.dtdc.cd.exception.UserNotFoundException;
import com.dtdc.cd.model.DtdcCollection;
import com.dtdc.cd.model.ShipmentBooking;
import com.dtdc.cd.model.User;
import com.dtdc.cd.service.DtdcCollectionService;
import com.dtdc.cd.service.ShipmentBookingService;
import com.dtdc.cd.service.UpdateShipmentDeliveryDetailsService;
import com.dtdc.cd.service.UserService;

@Component
public class UpdateIndivisualShipmentDetailsDelegate {

	@Autowired
	private UpdateShipmentDeliveryDetailsService deliveryDetailsService;
	
	@Autowired
	private DtdcCollectionService collectionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ShipmentBookingService bookingService;
	
	
	public boolean updateDeliveryDetails(UpdateIndivisualShipmentDetailsCommand command) throws UserNotFoundException {
		
		//Save dtdc collection
		ShipmentBooking booking = bookingService.findById(command.getConsignmentNo());
		User user = userService.getUserById(command.getUserId());
		DtdcCollection dtdcCollection = collectionService.saveDtdcCollection(command,user,booking);
		System.out.println("DTDCCOLLECTION ID : "+dtdcCollection);
		
		//Update shipment delivery
		deliveryDetailsService.shipmentUpdateByConsignmentNumber(command);

		return true;
	}
	
}
