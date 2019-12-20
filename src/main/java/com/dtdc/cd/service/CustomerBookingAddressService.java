package com.dtdc.cd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtdc.cd.model.CustomerBookingAddress;
import com.dtdc.cd.repository.CustomerBookingAddressRepository;

@Service
public class CustomerBookingAddressService {

	@Autowired
	private CustomerBookingAddressRepository custAddressRepository;
	
	public CustomerBookingAddress saveCustomerAddress(CustomerBookingAddress customerBookingAddress) {
		
		return custAddressRepository.save(customerBookingAddress);
	}
}
