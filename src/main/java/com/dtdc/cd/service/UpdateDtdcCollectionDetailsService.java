package com.dtdc.cd.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtdc.cd.command.UpdateIndivisualShipmentDetailsCommand;
import com.dtdc.cd.model.DtdcCollection;
import com.dtdc.cd.model.ShipmentBooking;
import com.dtdc.cd.model.User;
import com.dtdc.cd.repository.UpdateDtdcCollectionDetailsRepository;

@Service
public class UpdateDtdcCollectionDetailsService {

	@Autowired
	private UpdateDtdcCollectionDetailsRepository repository;
	
	public DtdcCollection saveDtdcCollection(UpdateIndivisualShipmentDetailsCommand command,User user,ShipmentBooking booking) {
		
		DtdcCollection collection = new DtdcCollection();
		collection.setPrice(command.getPrice());
		collection.setCdsDateTime(new Date());
		collection.setShipBooking(booking);
		collection.setCollectionUser(user);
		
		DtdcCollection collectionResult = repository.save(collection);
		
		return collectionResult;
		
	}
	
}
