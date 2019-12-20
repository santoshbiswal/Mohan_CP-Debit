package com.dtdc.cd.deligate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dtdc.cd.command.ShipmentBookingCommand;
import com.dtdc.cd.exception.UserNotFoundException;
import com.dtdc.cd.model.CustomerBookingAddress;
import com.dtdc.cd.model.DtdcCustomerBooking;
import com.dtdc.cd.model.ShipmentBooking;
import com.dtdc.cd.model.User;
import com.dtdc.cd.service.CustomerBookingAddressService;
import com.dtdc.cd.service.DtdcCustomerBookingService;
import com.dtdc.cd.service.ShipmentBookingService;
import com.dtdc.cd.service.UserService;

@Component
public class DTDCBookingDeligate {

	@Autowired
	private CustomerBookingAddressService customerAddressService;
	
	@Autowired
	private DtdcCustomerBookingService customerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ShipmentBookingService shipmentBookingService;
	
	public boolean doDtdcBooking(ShipmentBookingCommand command) throws UserNotFoundException {
		
		//Save Customer
		DtdcCustomerBooking custmerBooking = extractDtdcCustomerFromShipmentBookingCommand(command);
		DtdcCustomerBooking bookingCustmer = customerService.saveCustomer(custmerBooking);
		
		//Save From and To address
		CustomerBookingAddress fromBookingAddress = extractCustomerBookingAddressFromShipmentBookingCommand(command,bookingCustmer);
		CustomerBookingAddress toBookingAddress = extractCustomerBookingAddressToShipmentBookingCommand(command, bookingCustmer);
		
		customerAddressService.saveCustomerAddress(fromBookingAddress);
		customerAddressService.saveCustomerAddress(toBookingAddress);
		
		//Save booking	
		ShipmentBooking shipBooking = extractShipmentBookingFromShipmentBookingCommand(command);
		User user = userService.getUserById(command.getUserId());
		
		shipBooking.setUser(user);
		shipBooking.setDtdcCustBooking(bookingCustmer);
		
		ShipmentBooking shipmentBooking = shipmentBookingService.doBookingShipment(shipBooking);
		System.out.println("shipmentBooking id : "+shipmentBooking);
		
		return true;
	}
	
	private CustomerBookingAddress extractCustomerBookingAddressFromShipmentBookingCommand(ShipmentBookingCommand command,DtdcCustomerBooking customerBooking) {
		
		CustomerBookingAddress bookingAddress = new CustomerBookingAddress();
		
		bookingAddress.setAddressLine1(command.getFromAddressLine1());
		bookingAddress.setAddressLine2(command.getFromAddressLine2());
		bookingAddress.setCity(command.getFromCity());
		bookingAddress.setState(command.getFromState());
		bookingAddress.setCountry(command.getFromCountry());
		bookingAddress.setZip(command.getFromZip());
		bookingAddress.setCustomerBooking(customerBooking);
		bookingAddress.setAddressType("FROM");
		
		return bookingAddress;
	}
	
	private CustomerBookingAddress extractCustomerBookingAddressToShipmentBookingCommand(ShipmentBookingCommand command,DtdcCustomerBooking customerBooking) {
		
		CustomerBookingAddress bookingAddress = new CustomerBookingAddress();
		
		bookingAddress.setAddressLine1(command.getToAddressLine1());
		bookingAddress.setAddressLine2(command.getToAddressLine2());
		bookingAddress.setCity(command.getToCity());
		bookingAddress.setState(command.getToState());
		bookingAddress.setCountry(command.getToCountry());
		bookingAddress.setZip(command.getToZip());
		bookingAddress.setCustomerBooking(customerBooking);
		bookingAddress.setAddressType("TO");
		
		return bookingAddress;
	}
	
	private DtdcCustomerBooking extractDtdcCustomerFromShipmentBookingCommand(ShipmentBookingCommand command) {
		
		DtdcCustomerBooking bookingCustomer = new DtdcCustomerBooking();
		
		bookingCustomer.setName(command.getName());
		bookingCustomer.setPhone(command.getPhone());
		bookingCustomer.setEmail(command.getEmail());
		
		return bookingCustomer;
	}
	
	private ShipmentBooking extractShipmentBookingFromShipmentBookingCommand(ShipmentBookingCommand command) {
		
		ShipmentBooking booking=new ShipmentBooking();
		
		booking.setBookingDtTime(command.getBookingDtTime());
		booking.setBookingParcelType(command.getBookingParcelType());
		booking.setDestination(command.getDestination());
		booking.setSource(command.getSource());
		booking.setItemWeight(command.getItemWeight());
		booking.setPrice(command.getPrice());
		booking.setVasProdCode(command.getVasProdCode());
		booking.setQuantity(command.getQuantity());
		
		return booking;
	}
	
	
}
