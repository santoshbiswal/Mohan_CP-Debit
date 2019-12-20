package com.dtdc.cd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtdc.cd.model.DtdcCustomerBooking;
import com.dtdc.cd.repository.DtdcCustomerBookingRepository;

@Service
public class DtdcCustomerBookingService {

	@Autowired
	private DtdcCustomerBookingRepository repository;
	
	public DtdcCustomerBooking saveCustomer(DtdcCustomerBooking booking) {
		
		return repository.save(booking);
	}
}
