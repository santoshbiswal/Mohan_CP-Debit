package com.dtdc.cd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtdc.cd.model.Address;
import com.dtdc.cd.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	public Address saveAddress(Address address) {
		
		Address address2 = new Address();
		
		System.out.println("AddressID : "+address2.getAddressId());
		
		return addressRepository.save(address);
	}
}
