package com.dtdc.cd.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtdc.cd.command.UpdateIndivisualShipmentDetailsCommand;
import com.dtdc.cd.repository.UpdateShipmentDeliveryDetailsRepository;

@Service
public class UpdateShipmentDeliveryDetailsService {

	@Autowired
	private UpdateShipmentDeliveryDetailsRepository repository;
	
	public void shipmentUpdateByConsignmentNumber(UpdateIndivisualShipmentDetailsCommand command) {
		
		String cnNumber=command.getConsignmentNo();
		System.out.println("cnNumber:"+cnNumber);
		
		String status=command.getDeliveryStatus();
		System.out.println("status:"+status);
		
		Date todayDate = currentDate();
		System.out.println("todayDate : "+todayDate);
		
		Integer userId = command.getUserId();
		System.out.println("userId :" + userId);
		
		repository.updateDeliveredShipmentStatusById(status, todayDate, userId, cnNumber);
	}
	
	private Date currentDate() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
		Date date = new Date();
		try {
			date = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date;
	}
}
