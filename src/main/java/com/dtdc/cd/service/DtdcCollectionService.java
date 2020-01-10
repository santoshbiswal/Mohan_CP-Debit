package com.dtdc.cd.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtdc.cd.command.UpdateIndivisualShipmentDetailsCommand;
import com.dtdc.cd.dto.ResultLogStatusDTO;
import com.dtdc.cd.model.DtdcCollection;
import com.dtdc.cd.model.ShipmentBooking;
import com.dtdc.cd.model.User;
import com.dtdc.cd.repository.DtdcCollectionRepository;

@Service
public class DtdcCollectionService {

	@Autowired
	private DtdcCollectionRepository repository;
	
	public DtdcCollection saveDtdcCollection(UpdateIndivisualShipmentDetailsCommand command,User user,ShipmentBooking booking) {
		
		DtdcCollection collection = new DtdcCollection();
		collection.setPrice(command.getPrice());
		collection.setCdsDateTime(new Date());
		collection.setShipBooking(booking);
		collection.setCollectionUser(user);
		
		DtdcCollection collectionResult = repository.save(collection);
		
		return collectionResult;
		
	}
	
	public boolean isPaymentCollected(String shipmentNumber) {
		
		Integer count=repository.getPaymentCount(shipmentNumber);
		
		if(count==0) {
			
			return false;
		}	
		return true;
	}
	
	public boolean isCDSGenerated(String shipmentNumber) {
		
		Integer count=repository.getCdsCount(shipmentNumber);
		
		if(count==0) {
			
			return false;
		}	
		return true;
	}
	
	public boolean isAlreadyBlocked(String shipmentNumber) {
		
		Integer count=repository.getBlockCount(shipmentNumber);
		
		if(count==0) {
			
			return false;
		}	
		return true;
	}
	
	public void updateBlockForCDS(String excelFileRemarks,String status,String statusRemarks,Date processDateTime,ResultLogStatusDTO dto,String consignmentNumber) {
		
			String cdsgenerated = "Y";
			String excelremarks = "CP DEBIT";
			Date blockdatetime = new Date();
			String blockremarks = "CP_DEBIT_EXCEL";
			int blockuserid = dto.getUserID();
		
		try {
			
			repository.updateBlockShipment(cdsgenerated, excelremarks, blockdatetime, blockremarks, blockuserid, consignmentNumber);
		}catch(Exception e) {
			
			System.out.println("Invalid SQL Statement....");
		}
	}
	
	
}
