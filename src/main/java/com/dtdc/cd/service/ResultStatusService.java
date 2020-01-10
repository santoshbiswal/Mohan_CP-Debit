package com.dtdc.cd.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtdc.cd.bo.BlockShipmentResultBO;
import com.dtdc.cd.dto.ResultLogStatusDTO;
import com.dtdc.cd.repository.ResultStatusRepository;

@Service
public class ResultStatusService {

	@Autowired
	private ResultStatusRepository repository;
	
	public void saveLogStaus(ResultLogStatusDTO dto,String excelFileRemarks,String status,String statusRemarks,String shipmentNumber,Date processDateTime,String consignmentNumber) {
		
		String downloadFileName = dto.getDownloadFileName();
		String nodeId = dto.getNodeID();
		String processType=dto.getProcessType();
		String uploadFileName = dto.getUploadFileName();
		String payslipNumber = getCDSNoBySipmentNumber(shipmentNumber);
		Integer logUserId = dto.getUserID();
		
		try {
		
		repository.saveResultStatus(downloadFileName, excelFileRemarks, nodeId, processDateTime, processType, status, statusRemarks, uploadFileName, payslipNumber, logUserId, consignmentNumber);
		
		}catch(Exception e){
			System.out.println("Invalid SQL Statement...");
		}
	}
	
	public String getCDSNoBySipmentNumber(String shipmentNumber) {
		
		String cdsNo = null;
		try {
			cdsNo = repository.findCdsByConsgNo(shipmentNumber);
		}catch(Exception e) {
			System.out.println("CDS No is not present in Collection Table");
		}
		
		return cdsNo;
	}
	
	public List<BlockShipmentResultBO> getAllCpDebitResultInfo(Date dateTime){
		
		List<Object[]> resultData = repository.getAllCPDebitInfoByDate(dateTime);
		
		List<BlockShipmentResultBO> resultBos = convertEntityListToShipmentResultList(resultData);
		
		return resultBos;
	}
	
	private List<BlockShipmentResultBO> convertEntityListToShipmentResultList(List<Object[]> shipList){
		
		List<BlockShipmentResultBO> shipmentResultBOs = new ArrayList<BlockShipmentResultBO>();
		
			for(Object[] arr : shipList) {
				
				BlockShipmentResultBO bo =new BlockShipmentResultBO();
				bo.setShipmentNumber(String.valueOf(arr[0]));
				bo.setExcelFileRemarks(String.valueOf(arr[1]));
				bo.setStatus(String.valueOf(arr[2]));
				bo.setStatusRemarks(String.valueOf(arr[3]));
				bo.setUploadFileName(String.valueOf(arr[4]));
				bo.setDownldFileName(String.valueOf(arr[5]));
				bo.setProcessType(String.valueOf(arr[6]));
				//Date date = converObjectToDate(arr[7]);
				//bo.setProcessDateTime(date);
				//String userId = String.valueOf(arr[8]);
				//bo.setUserID(Integer.valueOf(userId));
				bo.setNodeID(String.valueOf(arr[7]));
				
				shipmentResultBOs.add(bo);
				System.out.println("shipmentResultBOs :"+shipmentResultBOs);
			}
			
			return shipmentResultBOs;
	}
	
	private Date converObjectToDate(Object date){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy hh:mm:ss aa");

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
