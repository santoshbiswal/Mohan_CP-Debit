package com.dtdc.cd.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtdc.cd.bo.ShipmentStatusBO;
import com.dtdc.cd.model.ShipmentBooking;
import com.dtdc.cd.repository.ShipmentBookingRepository;

@Service
public class ShipmentBookingService {

	@Autowired
	private ShipmentBookingRepository repository;
	
	public ShipmentBooking doBookingShipment(ShipmentBooking booking) {
		
		ShipmentBooking shipmentBooking = repository.save(booking);
		return shipmentBooking;
	}
	
	public Integer getPriceBySourceAndDestination(String source,String destination) {
		
		return repository.findPrice(source, destination);
	}
	
	public boolean isShipmentAvailable(String shipmentNumber) {
		
		Integer count = repository.getBookingCountByShipmentNumber(shipmentNumber);
		
		if(count == 0) {
			
			return false;
		}
		return true;
	}

	public ShipmentBooking findById(String id) {
		
		Optional<ShipmentBooking> optional= repository.findById(id);
		ShipmentBooking ob=null;
		if(optional.isPresent()) {
			ob=optional.get();
		}
		
		return ob;
	}
	
	public List<ShipmentBooking> getShipmentBookingByDate(Date now,Date tomorrow){
		
		return repository.findShipmentBookingByDate(now, tomorrow);
	}
	
	public List<ShipmentStatusBO> getShipmentAndStatusByShipmentNumber(String consignmentNumber){
		
		List<Object[]> resultData = repository.getShipmentAndStatusByShipmentNumber(consignmentNumber);
		
		List<ShipmentStatusBO> shipmentStatusBOs = converObjectarrayListToShipmentStatusBoList(resultData);
		
		return shipmentStatusBOs;
	}
	
	public List<ShipmentStatusBO> getShipmentAndStatusBydateRangeAndStatus(String fromDate,String toDate,String status){
		
		List<Object[]> resultData=null;
		
		if(!status.equals("All")) {
			resultData=repository.getShipmentAndStatusBydateRangeAndStatus(fromDate, toDate, status);
			
		}else {
			
			resultData=repository.getShipmentAndStatusBydateRange(fromDate, toDate);
			
		}
		
		List<ShipmentStatusBO> shipmentStatusBoList=converObjectarrayListToShipmentStatusBoList(resultData);
		
		
				
		System.out.println(shipmentStatusBoList);
		return shipmentStatusBoList;
	}
	
	public List<ShipmentStatusBO> getShipmentAndStatusBySourceAndDest(String source,String destination,String status){
		
		List<Object[]> resultData=null;
		
		if(!status.equals("All")) {
			
			resultData = repository.getShipmentAndStatusBySourceAndDestAndStatus(source, destination, status);
			
		}else {
			
			resultData = repository.getShipmentAndStatusBySourceAndDest(source, destination);
		}
		
		 
		
		List<ShipmentStatusBO> shipmentStatusBOs = converObjectarrayListToShipmentStatusBoList(resultData);
		
		return shipmentStatusBOs;
	}
	
	
	private List<ShipmentStatusBO> converObjectarrayListToShipmentStatusBoList(List<Object[]> shipmentResultList){
			
			
		//s.CONSIGNMENT_NUMBER,s.SOURCE,s.DESTINATION,s.BOOKING_DATE_TIME,d.DELIVERY_STATUS,d.REM
		List<ShipmentStatusBO> shipmentStatusBoList=new ArrayList<ShipmentStatusBO>();
		
			for(Object[] arr:shipmentResultList) {
				
				ShipmentStatusBO bo=new ShipmentStatusBO();
				
				bo.setConsignmentNumber(String.valueOf(arr[0]));
				bo.setSource(String.valueOf(arr[1]));
				bo.setDestination(String.valueOf(arr[2]));
				
				Date date=converObjectToDate(arr[3]);
				
				bo.setDob(date);
				
				bo.setDeliveryStatus(String.valueOf(arr[4]));
				
				bo.setRemarks(String.valueOf(arr[5]));
				
				shipmentStatusBoList.add(bo);
			}
			
			return shipmentStatusBoList;
	}
	
	private Date converObjectToDate(Object date){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date convertedDate=null;;
		try {
			convertedDate = sdf.parse(String.valueOf(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return convertedDate;
	}
}
