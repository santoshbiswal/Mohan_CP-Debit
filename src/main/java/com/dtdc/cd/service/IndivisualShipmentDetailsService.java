package com.dtdc.cd.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtdc.cd.bo.IndivisualShipmentAddressBO;
import com.dtdc.cd.bo.IndivisualShipmentDetailsBO;
import com.dtdc.cd.repository.IndivisualShipmentDetailsRepository;

@Service
public class IndivisualShipmentDetailsService {

	@Autowired
	private IndivisualShipmentDetailsRepository repository;
	
	public IndivisualShipmentDetailsBO getIndivisualShipmentDetails(String consignmentnumber) {
		
			
		List<Object[]> shipmentDataList = repository.getIndivisualShipmentDetails(consignmentnumber);
		
		return prepareIndivisualShipmentDetailsBO(shipmentDataList);
			
		
	}
	
	private IndivisualShipmentDetailsBO prepareIndivisualShipmentDetailsBO(List<Object[]> list) {
		
		IndivisualShipmentDetailsBO result = new IndivisualShipmentDetailsBO();
		
		for(int i=0;i<list.size();i++) {
			
			Object[] arr = list.get(i);
			
			if(i==0) {
				
				result.setConsignmentNo(String.valueOf(arr[0]));
				result.setName(String.valueOf(arr[1]));
				result.setPhone(String.valueOf(arr[2]));
				result.setVasProdCode(String.valueOf(arr[3]));
				
				String bookingPrice = String.valueOf(arr[4]);
				result.setBookingPrice(Integer.valueOf(bookingPrice));
				
				String itemWeight = String.valueOf(arr[5]);
				result.setItemWeight(Float.valueOf(itemWeight));
				
				Date date=converObjectToDate(arr[6]);
				result.setBookingDtTime(date);
				result.setBookingParcelType(String.valueOf(arr[7]));
				
				String quantity = String.valueOf(arr[8]);
				result.setQuantity(Integer.valueOf(quantity));
				result.setDeliveryStatus(String.valueOf(arr[9]));
			}
			
			IndivisualShipmentAddressBO addressBO = new IndivisualShipmentAddressBO();
			
			addressBO.setAddressLine1(String.valueOf(arr[10]));
			addressBO.setAddressLine2(String.valueOf(arr[11]));
			addressBO.setCity(String.valueOf(arr[12]));
			addressBO.setCountry(String.valueOf(arr[14]));
			addressBO.setState(String.valueOf(arr[13]));
			
			String zip = String.valueOf(arr[15]);
			addressBO.setZip(Integer.valueOf(zip));
			String addressType = String.valueOf(arr[16]);
			
			if(addressType.equals("TO")) {
				
				result.setToAddress(addressBO);
			}else {
				
				result.setFromAddress(addressBO);
			}
		}
		
		System.out.println("Result : "+result);
		
		return result;
	}
	
	 private Date converObjectToDate(Object date){
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");
		 
		 Date convertedDate=null;
		 try { 
			 convertedDate = sdf.parse(String.valueOf(date)); 
			 
	     } catch (ParseException e) { 
	           e.printStackTrace(); 
	     }
		 return convertedDate; 
	}

}
